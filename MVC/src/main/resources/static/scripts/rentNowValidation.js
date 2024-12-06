document.addEventListener('DOMContentLoaded', function () {

    const rentNowModal = document.getElementById('rentNowModal');
    const rentNowForm = rentNowModal.querySelector('form');
    const emailInput = rentNowModal.querySelector('#rentNowEmail');

    rentNowModal.addEventListener('show.bs.modal', function (event) {

        const button = event.relatedTarget;

        const bookId = button.getAttribute('data-bs-book-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const bookIdInput = rentNowModal.querySelector('#rentNowBookId');
        bookIdInput.value = bookId;
    });

    function isValidEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    rentNowForm.addEventListener('submit', async function (event) {
        event.preventDefault(); // Zatrzymanie wysyłania formularza
        let isValid = true;

        if (!isValidEmail(emailInput.value)) {
            isValid = false;
            emailInput.classList.add('is-invalid');
        } else {
            emailInput.classList.remove('is-invalid');
        }

        const confirmButton = document.getElementById('confirmButton');
        if (isValid) {
            var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'), {
                keyboard: false
            });
            confirmationModal.show();
        }
        confirmButton.addEventListener('click', function () {
            rentNowForm.submit();
        });
    });
});

