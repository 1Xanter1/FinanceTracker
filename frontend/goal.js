const modal = document.getElementById("goalModal");

document
    .getElementById("openGoalModal")
    .addEventListener("click", () => {
        modal.style.display = "flex";
    });

document
    .getElementById("closeModal")
    .addEventListener("click", () => {
        modal.style.display = "none";
    });

const form = document.getElementById("goalForm");
const goalList = document.getElementById("goalList");

form.addEventListener("submit", function(e) {
    e.preventDefault();

    const name = document.getElementById("goal_name").value;
    const target = Number(document.getElementById("goal_cost").value);

    const current = 0;

    const percent = (current / target) * 100;

    const card = document.createElement("div");
    card.className = "goal_card";

    card.innerHTML = `
        <h3>${name}</h3>
        <p>$${current} / $${target}</p>

        <div class="progress-bar">
            <div class="progress" style="width:${percent}%"></div>
        </div>

        <span>${percent.toFixed(0)}%</span>
    `;

    goalList.appendChild(card);

    form.reset();
});