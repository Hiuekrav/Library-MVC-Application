document.addEventListener('DOMContentLoaded', function () {
    // Znajdź modal
    const endRentModal = document.getElementById('endRentModal');

    // Dodaj event listener do momentu otwierania modala
    endRentModal.addEventListener('show.bs.modal', function (event) {
        // Przycisk, który wywołały modal
        const button = event.relatedTarget;

        const rentId = button.getAttribute('data-bs-rent-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const rentIdInput = endRentModal.querySelector('#rent-id');
        rentIdInput.value = rentId;
    });
});

