const loginBtn = document.querySelector(".login_button");

loginBtn.addEventListener("click", async () => {

    const email = document.getElementById("email_finance").value;
    const password = document.getElementById("password_input").value;

    try{

        const response = await fetch("http://localhost:8080/users/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                email,
                password
            })
        });

        if(response.ok){

            const user = await response.json();

            localStorage.setItem("user", JSON.stringify(user));

            window.location.href="dashboard.html";

        }else{

            alert("Invalid email or password");

        }

    }catch(error){
        console.error(error);
        alert("Server is not running.");
    }

});