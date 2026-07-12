const modal = document.getElementById("categoriesModal");

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


const incomeCategory = document.getElementById("incomeCategory");
const expenseCategory = document.getElementById("expenseCategory");

const form = document.getElementById("categories_form");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.getElementById("Category_name").value;

    const type = document.querySelector(
        'input[name="type"]:checked'
    )?.value;

    if (!name || !type) {
        alert("Please fill all fields.");
        return;
    }

    const p = document.createElement("p");
    p.textContent = name;

    if (type === "INCOME") {
        incomeCategory.appendChild(p);
    } else {
        expenseCategory.appendChild(p);
    }

    form.reset();
    modal.style.display = "none";
});