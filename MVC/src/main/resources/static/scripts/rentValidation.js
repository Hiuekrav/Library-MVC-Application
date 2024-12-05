
document.addEventListener('DOMContentLoaded', function () {
    // Znajdź modal
    const rentModal = document.getElementById('rentModal');
    const rentForm = rentModal.querySelector('form'); // Formularz w modalu
    const emailInput = rentModal.querySelector('#email'); // Pole e-mail
    const beginTimeInput = rentModal.querySelector('#begin-time'); // Pole beginTime
    const endTimeInput = rentModal.querySelector('#end-time'); // Pole endTime

    // Dodaj event listener do momentu otwierania modala
    rentModal.addEventListener('show.bs.modal', function (event) {
        // Przyciski, które wywołały modal
        const button = event.relatedTarget;

        // Pobierz wartość bookId z atrybutu data-bs-book-id
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

        // Jeśli wszystkie walidacje się powiodły, wyślij formularz
        if (isValid) {
            rentForm.submit();
        }
    });
});

