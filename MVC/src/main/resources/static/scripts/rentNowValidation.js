document.addEventListener('DOMContentLoaded', function () {
    // Znajdź modal
    const rentNowModal = document.getElementById('rentNowModal');
    const rentNowForm = rentNowModal.querySelector('form'); // Formularz w modalu
    const emailInput = rentNowModal.querySelector('#rentNowEmail'); // Pole e-mail

    // Dodaj event listener do momentu otwierania modala
    rentNowModal.addEventListener('show.bs.modal', function (event) {
        // Przyciski, które wywołały modal
        const button = event.relatedTarget;

        // Pobierz wartość bookId z atrybutu data-bs-book-id
        const bookId = button.getAttribute('data-bs-book-id');

        // Znajdź ukryte pole i ustaw jego wartość
        const bookIdInput = rentNowModal.querySelector('#rentNowBookId');
        bookIdInput.value = bookId;
    });

    // Funkcja do walidacji e-maila
    function isValidEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    // Obsługa zdarzenia submit formularza
    rentNowForm.addEventListener('submit', async function (event) {
        event.preventDefault(); // Zatrzymanie wysyłania formularza
        let isValid = true;

        // Walidacja e-maila
        if (!isValidEmail(emailInput.value)) {
            isValid = false;
            emailInput.classList.add('is-invalid');
        } else {
            emailInput.classList.remove('is-invalid');
        }

        // Jeśli wszystkie walidacje się powiodły, wyślij formularz
        if (isValid) {
            rentNowForm.submit();
        }
    });
});

