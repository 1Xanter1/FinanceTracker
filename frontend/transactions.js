const user = JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}

document.querySelector(".profile_name").textContent = user.username;

const modal = document.getElementById("transactionModal");

document
    .getElementById("openTransactionModal")
    .addEventListener("click", () => {
        modal.style.display = "flex";
    });

document
    .getElementById("closeModal")
    .addEventListener("click", () => {
        modal.style.display = "none";
    });

let allCategories = [];

loadTransactions();
loadCategoryOptions();

async function loadTransactions() {

    try {

        const response = await fetch(
            `http://localhost:8080/transactions/user/${user.userId}`
        );

        const transactions = await response.json();

        const tbody = document.querySelector("tbody");

        tbody.innerHTML = "";

        transactions.forEach(t => {

            tbody.innerHTML += `
                <tr>
                    <td>${t.description}</td>
                    <td>${t.category.categoryName}</td>
                    <td>$${t.amount}</td>
                    <td>${t.transactionType}</td>
                </tr>
            `;

        });

    }
    catch (error) {

        console.error(error);

    }

}

async function loadCategoryOptions() {

    try {

        const response = await fetch(
            `http://localhost:8080/categories/user/${user.userId}`
        );

        allCategories = await response.json();

        updateCategoryOptions();

    }
    catch (error) {

        console.error(error);

    }

}

function updateCategoryOptions() {

    const selectedType = document.querySelector(
        "input[name='type']:checked"
    )?.value;

    const select = document.getElementById("category");

    select.innerHTML = "";

    if (!selectedType) return;

    const filtered = allCategories.filter(
        c => c.categoryType === selectedType
    );

    filtered.forEach(category => {

        const option = document.createElement("option");

        option.value = category.categoryId;

        option.textContent = category.categoryName;

        select.appendChild(option);

    });

}

document
    .querySelectorAll("input[name='type']")
    .forEach(radio => {

        radio.addEventListener("change", updateCategoryOptions);

    });

document
    .getElementById("transactionForm")
    .addEventListener("submit", async (e) => {

        e.preventDefault();

        const selectedType = document.querySelector(
            "input[name='type']:checked"
        );

        if (!selectedType) {
            alert("Select transaction type.");
            return;
        }

        const transaction = {

            amount: Number(document.getElementById("amount").value),

            description:
                document.getElementById("description").value,

            transactionType:
                selectedType.value,

            user: {
                userId: user.userId
            },

            category: {
                categoryId: Number(
                    document.getElementById("category").value
                )
            }

        };

        try {

            const response = await fetch(
                "http://localhost:8080/transactions",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(transaction)
                }
            );

            if (!response.ok) {

                alert("Failed to save transaction.");

                return;

            }

            modal.style.display = "none";

            document
                .getElementById("transactionForm")
                .reset();

            await loadTransactions();

            updateCategoryOptions();

        }
        catch (error) {

            console.error(error);

            alert("Server error.");

        }

    });