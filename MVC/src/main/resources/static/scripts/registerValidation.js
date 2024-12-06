
window.addEventListener('DOMContentLoaded', function () {
    console.log("Script loaded!!")
    let form = document
        .getElementsByClassName('needs-validation');

    form[0].addEventListener('submit', function (event) {

        let valid = true;

        let inputs = document.getElementsByTagName("input");
        console.log(inputs)

        for (let i=0; i <inputs.length; i++) {
            if (inputs[i].value === '') {
                valid = false;
                inputs[i].classList.add('is-invalid')
            }
            else if( (inputs[i].id==='inputFirstName' || inputs[i].id==='inputFirstName') &&  inputs[i].value.length<2) {
                valid = false;
                inputs[i].classList.add('is-invalid')
            }
            else {
                inputs[i].classList.remove('is-invalid')
            }
        }

        let emailInput = document.getElementById('email');
        let passwordInput = document.getElementById('password');
        if (validateEmail(emailInput.value)) {
            emailInput.classList.remove('is-invalid');
        } else {
            valid = false;
            emailInput.classList.add('is-invalid');
        }
        if (passwordInput.value.length >= 8) {
            console.log("password valid length")
            passwordInput.classList.remove('is-invalid');
        }
        else {
            console.log("password invalid length")
            valid = false;
            passwordInput.classList.add('is-invalid');
        }
        event.preventDefault();
        var confirmButton = document.getElementById('confirmButton');
        if (valid) {
            var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'), {
                keyboard: false
            });
            confirmationModal.show();
        }
        confirmButton.addEventListener('click', function () {
            form[0].submit();
        });

    })
}, false);

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}
