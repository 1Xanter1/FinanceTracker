const ctx = document.getElementById("expense_chart");

new Chart(ctx, {
    type: "pie",
    data: {
        labels: ["Food", "Rent", "Transport"],
        datasets: [{
            data: [100, 200, 50],
            radius: "68%"
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                labels: {
                    color: "white",
                }
            }
        }
    }
});

const ctx2 = document.getElementById("spending_chart")
const barColors = ["red", "green", "blue", "orange", "brown"];
new Chart(ctx2, {
    type: "bar",
    data: {
        labels: ["May", "June", "July"],
        datasets: [{
            backgroundColor: barColors,
            data: [234, 231, 500]
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                labels: {
                    color: "white"
                }
            }
        }
    }
})