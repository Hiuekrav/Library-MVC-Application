
window.addEventListener('DOMContentLoaded', function () {
    console.log("Script loaded!!")
    let form = document
        .getElementsByClassName('needs-validation');

    form[0].addEventListener('submit', function (event) {

        let inputs = document.getElementsByTagName("input");
        console.log(inputs)

        for (let i=0; i <inputs.length; i++) {
            if (inputs[i].value === '') {
                event.preventDefault();
                event.stopPropagation();
                inputs[i].classList.add('is-invalid')
            }
            else if( (inputs[i].id==='inputFirstName' || inputs[i].id==='inputFirstName') &&  inputs[i].value.length<2) {
                event.preventDefault();
                event.stopPropagation();
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
            event.preventDefault();
            event.stopPropagation();
            emailInput.classList.add('is-invalid');
        }
        if (passwordInput.value.length >= 8) { //todo fix password length checking
            console.log("password valid length")
            passwordInput.classList.remove('is-invalid');
        }
        else {
            console.log("password invalid length")
            event.preventDefault();
            event.stopPropagation();
            passwordInput.classList.add('is-invalid');
        }
        //form[0].classList.add('was-validated');
    })
}, false);

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
}


function onSearchChange() {
    let searchTerm = document.getElementById('search-input').value;
    fetch('/search?term=' + searchTerm)
        .then(response => response.json())
        .then(data => {
            // handle the search results here, e.g., update the UI
            console.log(data);
        })
        .catch(error => console.error('Error:', error));
}