
const time = document.getElementById("countdown");

times =[];

changed = false;

function timedCount() {

    something();
    if (times[0] < 1 && changed === false) {

        changed = true;
    }



    postMessage("s");
    setTimeout("timedCount()",500);


}

timedCount();

function something() {


    jQuery.ajax({
        url: "/get-time",
        data: 'form3Example1cg=' + $("#form3Example1cg").val(),
        type: "GET",
        success: function (data) {

            times[0] = data[0];
            times[1] = data[1];



            if (data[0] ==0) {

            }

            if (data[1] >= 10) {
                time.innerHTML = "0"+ data[0].toString() + ":" + data[1].toString() ;
            } else {
                time.innerHTML = "0"+ data[0].toString() + ":0" + data[1].toString() ;
            }

            if (data[0] == 0 && data[1] == 0) {

            }






        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
    });


}








function checkPassword() {

    jQuery.ajax({
        url: "/check-code",
        data: 'form3Example1cg=' + $("#password").val(),
        type: "GET",
        success: function (data) {

            if (data) {

            }

        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
    });

    $("#password").val() = '';

}





let validUsername = false;
let validEmail = false;
let validPassword = false;
let validRepPassword = false;


function ajax() {

    jQuery.ajax({
        url: "/is-finished",
        type: "GET",
        success: function (data) {


            if (data == true) {
                shakeAll();
            }

            if (data == false) {
                unshakeAll();
            }


        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
    });

}


function shakeAll() {
    const all = document.getElementsByTagName("*");

    for (let i = 0; i < all.length; ++i) {
        all[i].classList.add("all");
    }
}

function unshakeAll() {
    const all = document.getElementsByTagName("*");
    for (const all of element) {
        all.classList.remove("all");
    }

}

const username = document.getElementById("form3Example1cg");
const email = document.getElementById("form3Example3cg");
const password = document.getElementById("form3Example4cg");
const repPassword = document.getElementById("form3Example4cdg");

const button = document.getElementById("submitButton");


const usernameHint = document.getElementById("username-hint");
const emailHint = document.getElementById("email-hint");
const passwordHint = document.getElementById("password-hint");
const repPasswordHint = document.getElementById("rep-password-hint");

const upperCaseHint = document.getElementById("upperCase");
const lowerCaseHint = document.getElementById("lowerCase");
const oneNumberHint = document.getElementById("oneNumber");
const specialCharacterHint = document.getElementById("specialCharacter");
const rangeHint = document.getElementById("range");


const usernameLogin = document.getElementById("form1Example13");
const passwordLogin = document.getElementById("form1Example23");

const tim = document.getElementById("par");

window.addEventListener("load", myInit, true);

function myInit() {
    disableSubmitButton();

}



function usernameValidation() {
    validUsername = true;

    if (username.value === "") { //nac posielat cez ajax prazdne heslo ze
        validUsername = false;
        canSubmit();
        return;
    }



    jQuery.ajax({
        url: "/check-username",
        data: 'form3Example1cg=' + $("#form3Example1cg").val(),
        type: "GET",
        success: function (data) {
            if (data == true) {
                usernameHint.innerHTML = "The username already exists!";
                validUsername = false;
                canSubmit();
            } else {
                if ( validUsername || (username.value === "")) {
                    usernameHint.innerHTML = "&#8203";
                    canSubmit();
                }

            }

        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
    });


}

function passwordValidation() {
    const regexUpperCase = /[A-Z]/g;
    const regexLowerCase = /[a-z]/g;
    const regexNumber = /[0-9]/g;
    const regexSpecialCharacter = /[?.:!@#$%^&*()_-]/g;
    validPassword = true;



    if (password.value.match(regexUpperCase)) {
        upperCaseHint.style.color = "green";
    } else {
        upperCaseHint.style.color = "black";
        validPassword = false;
    }

    if (password.value.match(regexLowerCase)) {
        lowerCaseHint.style.color = "green";
    } else {
        lowerCaseHint.style.color = "black";
        validPassword = false;
    }

    if (password.value.match(regexNumber)) {
        oneNumberHint.style.color = "green";
    } else {
        oneNumberHint.style.color = "black";
        validPassword = false;
    }

    if (password.value.match(regexSpecialCharacter)) {
        specialCharacterHint.style.color = "green";
    } else {
        specialCharacterHint.style.color = "black";
        validPassword = false;
    }


    if (password.value.length >= 12 ) {
        rangeHint.style.color = "green";
    } else {
        rangeHint.style.color = "black";
        validPassword = false;
    }


    this.repPasswordValidation();

    canSubmit();

}

function emailValidation() {

    validEmail = true;

    const regex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;

    if (String(email.value).toLowerCase().match(regex)) {
        emailHint.innerHTML = "&#8203";
    } else {
        if (email.value === "") {
            emailHint.innerHTML = "&#8203";
        } else {
            emailHint.innerHTML = "Invalid email format!";
            validEmail = false;
            canSubmit();
            return;
        }

    }


    jQuery.ajax({

        url: "/check-email",
        data: 'form3Example3cg=' + $("#form3Example3cg").val(),
        type: "GET",
        success: function (data) {
            if (data == true) {

                emailHint.innerHTML = "The email already exists!";
                validEmail = false;
                canSubmit();

            } else {

                if ( (validEmail) || (email.value === "")) {
                    emailHint.innerHTML = "&#8203";
                    canSubmit();
                }

            }

        },
        error: function (xhr, ajaxOptions, thrownError) {

        },
    });




}


function repPasswordValidation() {

    validRepPassword = true;

    if ( (password.value === repPassword.value) || (repPassword.value === "" && password.value() !== "")) {
        repPasswordHint.innerHTML = "&#8203";
    } else {
        repPasswordHint.innerHTML = "Passwords do not matches!";
        validRepPassword = false;
    }

    canSubmit();
}

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








