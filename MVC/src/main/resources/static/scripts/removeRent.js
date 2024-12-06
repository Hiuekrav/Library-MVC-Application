document.addEventListener('DOMContentLoaded', function () {
    const removeRentModal = document.getElementById('removeRentModal');

    removeRentModal.addEventListener('show.bs.modal', function (event) {

        const button = event.relatedTarget;

        const rentId = button.getAttribute('data-bs-rent-id');

        const rentIdInput = removeRentModal.querySelector('#removeRent-id');
        rentIdInput.value = rentId;
    });
});

