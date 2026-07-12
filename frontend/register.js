const form = document.getElementById("registerForm");

form.addEventListener("submit", async (event) => {

    event.preventDefault();

    const user = {
        username: document.getElementById("username_input").value,
        email: document.getElementById("email_input").value,
        password: document.getElementById("password_input").value
    };

    const response = await fetch(
        "http://localhost:8080/users",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }
    );

    if (response.ok) {
        alert("Account created!");
        window.location.href = "login.html";
    } else {
        alert("Registration failed.");
    }
});