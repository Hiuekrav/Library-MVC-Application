
document.addEventListener('DOMContentLoaded', function () {
    // Znajdź modal
    const rentModal = document.getElementById('rentModal');
    const rentForm = rentModal.querySelector('form');
    const emailInput = rentModal.querySelector('#email');
    const beginTimeInput = rentModal.querySelector('#begin-time');
    const endTimeInput = rentModal.querySelector('#end-time');

    rentModal.addEventListener('show.bs.modal', function (event) {
        // Przycisk, który wywołał modal
        const button = event.relatedTarget;

        const bookId = button.getAttribute('data-bs-book-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const bookIdInput = rentModal.querySelector('#book-id');
        bookIdInput.value = bookId;
    });

    // Funkcja do walidacji e-maila
    function isValidEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    // Obsługa zdarzenia submit formularza
    rentForm.addEventListener('submit', async function (event) {
        event.preventDefault(); // Zatrzymanie wysyłania formularza
        let isValid = true;

        // Walidacja e-maila
        if (!isValidEmail(emailInput.value)) {
            isValid = false;
            emailInput.classList.add('is-invalid');
        } else {
            emailInput.classList.remove('is-invalid');
        }

        // Walidacja dat
        const beginTime = new Date(beginTimeInput.value);
        const endTime = new Date(endTimeInput.value);

        if (beginTime >= endTime) {
            isValid = false;
            beginTimeInput.classList.add('is-invalid');
            endTimeInput.classList.add('is-invalid');
        } else {
            beginTimeInput.classList.remove('is-invalid');
            endTimeInput.classList.remove('is-invalid');
        }

        const confirmButton = document.getElementById('confirmButton');
        if (isValid) {
            var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'), {
                keyboard: false
            });
            confirmationModal.show();
        }
        confirmButton.addEventListener('click', function () {
            rentForm.submit();
        });
    });
});

