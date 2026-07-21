const ctx = document.getElementById("expense_chart");

const expenseChart = new Chart(ctx, {
    type: "pie",
    data: {
        labels: [],
        datasets: [{
            data: [],
            radius: "68%"
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false
    }
});
const ctx2 = document.getElementById("spending_chart")

const spendingChart = new Chart(ctx2, {
    type: "bar",
    data: {
        labels: [],
        datasets: [{
            data: []
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false
    }
});

const user = JSON.parse(localStorage.getItem("user"));

if(!user){
    window.location.href = "login.html";
}
document.querySelector(".profile_name").textContent = user.username;

let transactions = [];

async function loadTransactions(){

    const response = await fetch(
        `http://localhost:8080/transactions/user/${user.userId}`
    );

    transactions = await response.json();

    updateDashboard();

}

loadTransactions();

function updateDashboard(){

    let income = 0;
    let expenses = 0;

    transactions.forEach(t=>{

        if(t.transactionType==="INCOME"){
            income += t.amount;
        }else{
            expenses += t.amount;
        }

    });

    document.getElementById("income_num").textContent =
        "$" + income.toFixed(2);

    document.getElementById("expenses_num").textContent =
        "$" + expenses.toFixed(2);

    document.getElementById("balance_num").textContent =
        "$" + (income-expenses).toFixed(2);

    updateRecentTransactions();
    updateExpenseChart();
    updateMonthlyChart();

}

function updateRecentTransactions(){

    const container =
        document.querySelector(".transaction_bottom");

    container.innerHTML = "";

    transactions
        .slice(0,5)
        .forEach(t=>{

        container.innerHTML += `

        <div class="transaction_row">

            <span>${t.description}</span>

            <span class="${
                t.transactionType==="INCOME"
                    ? "income_amount"
                    : "expense_amount"
            }">

                ${
                    t.transactionType==="INCOME"
                    ? "+"
                    : "-"
                }

                $${t.amount}

            </span>

        </div>

        `;

    });

}
function updateExpenseChart(){

    const totals = {};

    transactions.forEach(t=>{

        if(t.transactionType==="expense"){

            const category = t.category.categoryName;

            totals[category] =
                (totals[category] || 0) + t.amount;

        }

    });

    expenseChart.data.labels = Object.keys(totals);

    expenseChart.data.datasets[0].data =
        Object.values(totals);

    expenseChart.update();

}
function updateMonthlyChart(){

    const monthly = {};

    transactions.forEach(t=>{

        if(t.transactionType==="expense"){

            const month = new Date(
                t.transactionDate
            ).toLocaleString("default",{month:"short"});

            monthly[month] =
                (monthly[month] || 0) + t.amount;

        }

    });

    spendingChart.data.labels =
        Object.keys(monthly);

    spendingChart.data.datasets[0].data =
        Object.values(monthly);

    spendingChart.update();

}