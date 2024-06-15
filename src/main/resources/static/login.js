let welcome = document.querySelector(".welcome"),
    wrapperSignup = document.querySelector(".wrapper.signup"),
    wrapperSignin = document.querySelector(".wrapper.signin"),
    accCreate = document.querySelector(".create"),
    accCreated = document.querySelector(".created"),
    signinButton = document.querySelector("#signin"),
    signupButton = document.querySelector("#signup"),
    signUp = document.querySelector("#sign-up"),
    formContainer = document.querySelector(".form"),
    reg = document.querySelector(".Regstered"),
    errorCont = document.querySelector(".Regstered .error"),
    regCont = document.querySelector(".Regstered .regcntr"),
    alrdCont = document.querySelector(".Regstered .alreaady"),
    errorMessageHolder = document.querySelectorAll(".Regstered .error .error-text p"),
    ok = document.querySelectorAll(".Regstered button"),
    alrdSignIn = document.querySelector(".Regstered .alreaady button"),

    votkey = document.querySelector("#key");
const viewportWidth = window.innerWidth,
    nameRegex = /^[a-zA-Z]+(?:[a-zA-Z]+)*$/,
    emailRegex = /^[\w-]+(\.[\w-]+)*@[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)*(\.[a-zA-Z]{2,})$/,
    phoneRegex = /^\+?[0-9()-\s]+$/,
    passwordRegex = /^[a-zA-Z0-9]{1,6}$/,
    idRegex = /^[a-zA-Z0-9]{1,6}$/;
if (viewportWidth < 850) {
    signupButton.addEventListener("click", () => {
        wrapperSignup.style.display = "flex";
        wrapperSignin.style.display = "none";
        accCreate.style.display = "flex";
        accCreated.style.display = "none";
        welcome.style.animationName = "flipbone";
        formContainer.style.animationName = "flipbtwo";

    });
    signinButton.addEventListener("click", () => {
        wrapperSignup.style.display = "none";
        wrapperSignin.style.display = "flex";
        accCreate.style.display = "none";
        accCreated.style.display = "flex";
        welcome.style.animationName = "flipone";
        formContainer.style.animationName = "fliptwo";
    });
}
else {
    signupButton.addEventListener("click", () => {
        wrapperSignup.style.display = "flex";
        wrapperSignin.style.display = "none";
        accCreate.style.display = "flex";
        accCreated.style.display = "none";
        welcome.style.animationName = "backone";
        formContainer.style.animationName = "backtwo";

    });
    signinButton.addEventListener("click", () => {
        wrapperSignup.style.display = "none";
        wrapperSignin.style.display = "flex";
        accCreate.style.display = "none";
        accCreated.style.display = "flex";
        welcome.style.animationName = "swipeone";
        formContainer.style.animationName = "swipetwo";
    });
}
var regs = {};
var forms = {}
forms.fName = document.querySelector("#regacc input[name='fname']");
forms.lName = document.querySelector("#regacc input[name='lname']");
forms.idNo = document.querySelector("#regacc input[name='id']");
forms.idNo = document.querySelector("#regacc input[name='id']");
forms.idNo = document.querySelector("#regacc input[name='id']");
forms.idNo = document.querySelector("#regacc input[name='id']");
forms.email = document.querySelector("#regacc input[name='email']");
forms.phone = document.querySelector("#regacc input[name='phone']");
forms.password = document.querySelector("#regacc input[name='password']");
let cpass = document.querySelector("#regacc input[name='cpassword']");

accCreate.addEventListener("submit", (event) => {
    event.preventDefault();
    var regs = {};
    regs.idNo = document.querySelector("#regacc input[name='id']").value;
    regs.fName = document.querySelector("#regacc input[name='fname']").value;
    regs.lName = document.querySelector("#regacc input[name='lname']").value;
    regs.email = document.querySelector("#regacc input[name='email']").value;
    regs.phone = document.querySelector("#regacc input[name='phone']").value;
    regs.password = document.querySelector("#regacc input[name='password']").value;
    let valid = formvalidator(regs);
    if (valid) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/register', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    reg.style.display = "grid";
                    errorCont.style.display = "none";
                    if (xhr.responseText == "already regsitered") {
                        alrdCont.style.display = "grid";
                        if (viewportWidth < 850) {
                            alrdSignIn.addEventListener("click", () => {
                                wrapperSignup.style.display = "none";
                                wrapperSignin.style.display = "flex";
                                accCreate.style.display = "none";
                                accCreated.style.display = "flex";
                                welcome.style.animationName = "flipone";
                                formContainer.style.animationName = "fliptwo";
                                accCreate.reset();
                            });
                        }
                        else {
                            alrdSignIn.addEventListener("click", () => {
                                wrapperSignup.style.display = "none";
                                wrapperSignin.style.display = "flex";
                                accCreate.style.display = "none";
                                accCreated.style.display = "flex";
                                welcome.style.animationName = "swipeone";
                                formContainer.style.animationName = "swipetwo";
                                accCreate.reset();

                            });
                        }
                    }
                    else {
                        votkey.innerHTML = xhr.responseText;
                        regCont.style.display = "grid";
                    }

                }
            }
        };
        xhr.send(JSON.stringify(regs));
    }
    else {
        votkey.innerHTML = "check your form the is an error";
        reg.style.display = "grid";
    }

    ok = Array.from(ok);
    ok.forEach(element => {
        element.addEventListener("click", () => {
            reg.style.display = "none";
        })
        ok[0].addEventListener("click", () => {
            accCreate.reset();

        })
    });

});


function formvalidator(regs) {
    let cpass = document.querySelector("#regacc input[name='cpassword']");
    let flag = true;
    let flagtraccer = 0;
    for (const key in regs) {
        const value = regs[key];
        const input = forms[key];
        if (value.trim() === "") {
            input.classList.add("errorinput");
            flag = false;
        }
        else {
            input.classList.remove("errorinput");
        }
    }
    if (!nameRegex.test(regs.fName)) {
        flag = false;
        forms.fName.classList.add("errorinput");
    }
    else {
        forms.fName.classList.remove("errorinput");
    }

    if (!nameRegex.test(regs.lName)) {
        flag = false;
        forms.lName.classList.add("errorinput");

    } else {
        forms.lName.classList.remove("errorinput");
    }
    if (!emailRegex.test(regs.email)) {
        flag = false;
        forms.email.classList.add("errorinput");

    } else {
        forms.email.classList.remove("errorinput");
    }
    if (!phoneRegex.test(regs.phone)) {
        flag = false;
        forms.phone.classList.add("errorinput");

    } else {
        forms.phone.classList.remove("errorinput");
    }
    if (!passwordRegex.test(regs.password)) {
        flag = false;
        forms.password.classList.add("errorinput");

    } else {
        forms.password.classList.remove("errorinput");
    }
    if (!idRegex.test(regs.idNo)) {
        flag = false
        forms.idNo.classList.add("errorinput");

    } else {
        forms.idNo.classList.remove("errorinput");
    } if (regs.password != cpass.value) {
        flag = false;
        cpass.classList.add("errorinput");

    }
    else {
        cpass.classList.remove("errorinput");

    }

    return flag;
}










