
function connect() {
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe('/topic/finished', function (message) {
            const data = JSON.parse(message.body);
            if (data === true) {
                shakeAll();
            }
        });
    });

    return stompClient;
}

let client = connect();


const shakeAll = () => document.querySelectorAll("*").forEach(el => el.classList.add("all"));
const unshakeAll = () => document.querySelectorAll("*").forEach(el => el.classList.remove("all"));





const button = document.getElementById("submitButton");
const usernameHint = document.getElementById("username-hint");
const emailHint = document.getElementById("email-hint");

const usernameLogin = document.getElementById("form1Example13");
const passwordLogin = document.getElementById("form1Example23");




const tim = document.getElementById("par");

window.addEventListener("load", myInit, true);

function myInit() {
    disableSubmitButton();

}


/************************************Username******************************************/

const usernameInput = document.getElementById("username");

usernameInput.addEventListener("input", () => {
    usernameValidation();
});

function usernameValidation() {
    validUsername = true;

    if (username.value.trim() === "") {
        validUsername = false;
        canSubmit();
        return;
    }

    const url = "/check-username?username=" + encodeURIComponent(usernameInput.value);

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok");
            return response.json(); // predpokladáme, že backend vracia boolean true/false
        })
        .then(data => {
            if (data === true) {
                usernameHint.innerHTML = "The username already exists!";
                validUsername = false;
            } else {
                usernameHint.innerHTML = "&#8203;";
            }
            canSubmit();
        })
        .catch(error => {
            console.error("Error checking username:", error);
        });
}

/************************************Username******************************************/





/************************************Password******************************************/

const passwordInput = document.getElementById("password");
const rePasswordInput = document.getElementById("re-password");
const passwordHint = document.getElementById("password-hint");
const repPasswordHint = document.getElementById("rep-password-hint");
const upperCaseHint = document.getElementById("upperCase");
const lowerCaseHint = document.getElementById("lowerCase");
const oneNumberHint = document.getElementById("oneNumber");
const specialCharacterHint = document.getElementById("specialCharacter");
const rangeHint = document.getElementById("range");


passwordInput.addEventListener("input", () => {
    passwordValidation();
});


rePasswordInput.addEventListener("input", () => {
    rePasswordValidation();
});

function passwordValidation() {
    const regexUpperCase = /[A-Z]/g;
    const regexLowerCase = /[a-z]/g;
    const regexNumber = /[0-9]/g;
    const regexSpecialCharacter = /[?.:!@#$%^&*()_-]/g;
    validPassword = true;


    const value = passwordInput.value;

    if (value.match(regexUpperCase)) {
        upperCaseHint.style.color = "green";
    } else {
        upperCaseHint.style.color = "black";
        validPassword = false;
    }

    if (value.match(regexLowerCase)) {
        lowerCaseHint.style.color = "green";
    } else {
        lowerCaseHint.style.color = "black";
        validPassword = false;
    }

    if (value.match(regexNumber)) {
        oneNumberHint.style.color = "green";
    } else {
        oneNumberHint.style.color = "black";
        validPassword = false;
    }

    if (value.match(regexSpecialCharacter)) {
        specialCharacterHint.style.color = "green";
    } else {
        specialCharacterHint.style.color = "black";
        validPassword = false;
    }


    if (value.length >= 12 ) {
        rangeHint.style.color = "green";
    } else {
        rangeHint.style.color = "black";
        validPassword = false;
    }


    repPasswordValidation();

    canSubmit();

}


function rePasswordValidation() {

    validRepPassword = true;

    const valueRe = rePasswordInput.value;
    const valuePa = passwordInput.value;

    if ( (valuePa === valueRe) || ( (valueRe === "") && (valuePa !== "") )) {
        repPasswordHint.innerHTML = "&#8203";
    } else {
        repPasswordHint.innerHTML = "Passwords do not matches!";
        validRepPassword = false;
    }

    canSubmit();
}

/************************************Password******************************************/






/************************************Email******************************************/
const emailInput = document.getElementById("email");

emailInput.addEventListener("input", () => {
    emailValidation();
});

const emailRegex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;

function checkEmail() {
        const emailValue = emailInput.value;

        fetch("/check-email?email=" + encodeURIComponent(emailValue))
            .then(response => {
                if (!response.ok) throw new Error("Network response was not ok");
                return response.json();
            })
            .then(data => {
                if (data === true) {
                    emailHint.innerHTML = "The email already exists!";
                    validEmail = false;
                    canSubmit();
                } else {
                    if (validEmail || emailValue === "") {
                        emailHint.innerHTML = "&#8203;";
                        canSubmit();
                    }
                }
            })
            .catch(error => {
                console.error("Error checking email:", error);
            });
}
function emailValidation()
{
    validEmail = true;
    if (String(email.value).toLowerCase().match(emailRegex)) {
        emailHint.innerHTML = "&#8203";
    } else {
        if (email.value === "") {
            emailHint.innerHTML = "&#8203";
            return;
        } else {
            emailHint.innerHTML = "Invalid email format!";
            validEmail = false;
            canSubmit();
            return;
        }

    }

    checkEmail();
}
/************************************Email******************************************/




function canSubmit() {
    button.disabled = !(validUsername && validEmail && validPassword && validRepPassword);
}

function disableSubmitButton() {
    button.disabled = true;
}

function isFieldEmpty()
{
    if (usernameLogin.value === "" || passwordLogin.value === "") {
        button.disabled = true;
    } else {
        button.disabled = false;
    }
}