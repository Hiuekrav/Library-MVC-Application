document.addEventListener('DOMContentLoaded', function () {

    const endRentModal = document.getElementById('endRentModal');

    endRentModal.addEventListener('show.bs.modal', function (event) {

        const button = event.relatedTarget;

        const rentId = button.getAttribute('data-bs-rent-id');

        const rentIdInput = endRentModal.querySelector('#rent-id');
        rentIdInput.value = rentId;
    });
});

