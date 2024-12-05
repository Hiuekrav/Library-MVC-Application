document.addEventListener('DOMContentLoaded', function() {
    var modalTrigger = document.getElementById('modalRentTrigger');
    var showModal = modalTrigger.getAttribute('data-show-modal');
    if (showModal === 'true') {
        var rentModal = new bootstrap.Modal(document.getElementById('rentModal'), {});
        rentModal.show();
    }
});

document.addEventListener('DOMContentLoaded', function() {
    var modalTrigger = document.getElementById('modalRentNowTrigger');
    var showModal = modalTrigger.getAttribute('data-show-modal');
    if (showModal === 'true') {
        var rentModal = new bootstrap.Modal(document.getElementById('rentNowModal'), {});
        rentModal.show();
    }
});