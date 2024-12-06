
document.addEventListener('DOMContentLoaded', function () {

    const rentModal = document.getElementById('rentModal');
    const rentForm = rentModal.querySelector('form');
    const emailInput = rentModal.querySelector('#email');
    const beginTimeInput = rentModal.querySelector('#begin-time');
    const endTimeInput = rentModal.querySelector('#end-time');

    rentModal.addEventListener('show.bs.modal', function (event) {

        const button = event.relatedTarget;

        const bookId = button.getAttribute('data-bs-book-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const bookIdInput = rentModal.querySelector('#book-id');
        bookIdInput.value = bookId;
    });


    function isValidEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    rentForm.addEventListener('submit', async function (event) {
        event.preventDefault(); // Zatrzymanie wysyłania formularza
        let isValid = true;


        if (!isValidEmail(emailInput.value)) {
            isValid = false;
            emailInput.classList.add('is-invalid');
        } else {
            emailInput.classList.remove('is-invalid');
        }


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

