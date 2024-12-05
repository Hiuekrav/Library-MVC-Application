document.addEventListener('DOMContentLoaded', function () {
    // Znajdź modal
    const removeRentModal = document.getElementById('removeRentModal');

    // Dodaj event listener do momentu otwierania modala
    removeRentModal.addEventListener('show.bs.modal', function (event) {
        // Przycisk, który wywołały modal
        const button = event.relatedTarget;

        const rentId = button.getAttribute('data-bs-rent-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const rentIdInput = removeRentModal.querySelector('#removeRent-id');
        rentIdInput.value = rentId;
    });
});

