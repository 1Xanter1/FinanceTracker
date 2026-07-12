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