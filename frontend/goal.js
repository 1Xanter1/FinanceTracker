const user = JSON.parse(localStorage.getItem("user"));

if (!user) {
    window.location.href = "login.html";
}


document.querySelector(".profile_name").textContent =
    user.username;

const modal =
    document.getElementById("goalModal");


document
    .getElementById("openGoalModal")
    .onclick = () => {

        modal.style.display = "flex";

    };

document
    .getElementById("closeModal")
    .onclick = () => {

        modal.style.display = "none";

    };

const goalList =
    document.getElementById("goalList");



async function loadGoals() {
    const response = await fetch(
        `http://localhost:8080/goals/user/${user.userId}`
    );

    const goals = await response.json();

    goalList.innerHTML = "";

    goals.forEach(goal => {
        let percent =
            (goal.currentAmount / goal.targetAmount) * 100;

        if (percent > 100)
            percent = 100;

        goalList.innerHTML += `

        <div class="goal_card">

            <h3>${goal.title}</h3>
            <p>
            $${goal.currentAmount}
            /
            $${goal.targetAmount}
            </p>

            <div class="progress-bar">
                <div class="progress"
                style="--progress:${percent}%">
                </div>
            </div>

            <span>
            ${percent.toFixed(0)}%
            </span>

            <button onclick="addMoney(${goal.goalId})">
            Add money
            </button>

        </div>

        `;

    });

}

async function addMoney(id) {

    let amount =
        prompt("How much money to add?");

    if (!amount)
        return;

    await fetch(
        `http://localhost:8080/goals/${id}/add?amount=${amount}`,
        {
            method: "PUT"
        }
    );

    loadGoals();

}

loadGoals();

document
    .getElementById("goal_form")
    .addEventListener("submit", async (e) => {

        e.preventDefault();

        const goal = {

            title:
                document.getElementById("goal_name").value,

            targetAmount:
                Number(
                    document.getElementById("goal_cost").value
                ),

            currentAmount: 0,

            user: {
                userId: user.userId
            }

        };

        await fetch(
            "http://localhost:8080/goals",
            {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(goal)

            }
        );

        modal.style.display = "none";

        e.target.reset();

        loadGoals();
    });