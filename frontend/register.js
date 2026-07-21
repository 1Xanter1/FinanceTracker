const registerForm = document.getElementById("registerForm");

registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const user = {
        username: document.getElementById("username_input").value,
        email: document.getElementById("email_input").value,
        password: document.getElementById("password_input").value
    };

    try {
        const response = await fetch("http://localhost:8080/users/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if(response.ok){
            alert("Registration successful!");
            window.location.href = "login.html";
        }else{
            const message = await response.text();
            alert(message);
        }

    }catch(error){
        console.error(error);
        alert("Could not connect to server.");
    }
});