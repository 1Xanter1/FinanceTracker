const user = JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}


document.querySelector(".profile_name").textContent = user.username;


const modal = document.getElementById("categoriesModal");

const incomeCategory = document.getElementById("incomeCategory");
const expenseCategory = document.getElementById("ExpenseCategory");

const form = document.getElementById("categories_form");

document
    .getElementById("openCategoriesModal")
    .addEventListener("click", () => {
        modal.style.display = "flex";
    });


document
    .getElementById("closeModal")
    .addEventListener("click", () => {
        modal.style.display = "none";
    });


loadCategories();



async function loadCategories() {

    try {

        const response = await fetch(
            `http://localhost:8080/categories/user/${user.userId}`
        );


        if (!response.ok) {
            throw new Error("Failed to load categories");
        }


        const categories = await response.json();

        console.log(categories);

        incomeCategory.innerHTML = `
            <h3>Income:</h3>
        `;


        expenseCategory.innerHTML = `
            <h3>Expenses:</h3>
        `;



        categories.forEach(category => {


            const p = document.createElement("p");

            p.textContent = category.categoryName;



            if(category.categoryType === "INCOME") {

                incomeCategory.appendChild(p);

            } 
            else if(category.categoryType === "EXPENSE") {

                expenseCategory.appendChild(p);

            }


        });



    } catch(error) {

        console.error(error);

    }

}


form.addEventListener("submit", async (e) => {

    e.preventDefault();


    const name =
        document.getElementById("Category_name").value.trim();


    const type =
        document.querySelector(
            'input[name="type"]:checked'
        )?.value;



    if(!name || !type){

        alert("Fill all fields");

        return;

    }



    const category = {

        categoryName: name,

        categoryType: type,

        user: {
            userId: user.userId
        }

    };



    try {


        const response = await fetch(
            "http://localhost:8080/categories",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body: JSON.stringify(category)

            }
        );



        if(response.ok){


            const savedCategory = await response.json();

            console.log("Saved:", savedCategory);


            alert("Category added");


            form.reset();


            modal.style.display = "none";

            await loadCategories();



        } 
        else {


            const errorText = await response.text();

            console.log(errorText);

            alert("Failed to add category");


        }



    } catch(error){

        console.error(error);

        alert("Server error");

    }


});