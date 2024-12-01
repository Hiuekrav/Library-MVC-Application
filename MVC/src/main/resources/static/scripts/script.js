
window.addEventListener('DOMContentLoaded', function () {
    console.log("Script loaded!!")
    let form = document
        .getElementsByClassName('needs-validation');

    form[0].addEventListener('submit', function (event) {
        let emailInput = document.getElementById('email');
        let passwordInput = document.getElementById('password');
        console.log(">>>Email:" + emailInput)
        if (validateEmail(emailInput.value)) {
            emailInput.classList.remove('is-invalid');
        } else {
            event.preventDefault();
            event.stopPropagation();
            emailInput.classList.add('is-invalid');
        }
        if (passwordInput.value.length < 8) { //todo fix password length checking
            passwordInput.classList.add('is-invalid');
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    })
}, false);

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}