let form = document.querySelector(".candidateForm")
inputs = document.querySelectorAll(".candidateForm > div > :last-child");
const nameRegex = /^[a-zA-Z]+(?:[a-zA-Z]+)*$/,
    idRegex = /^[a-zA-Z0-9]{1,6}$/;
console.log(inputs);
form.addEventListener("submit", (event) => {
    event.preventDefault();
    let flag = true;
    inputs.forEach((element, index) => {

        if (element.value.trim() === "") {
            if (index == 5) {
                if (fileInput.files.length === 0) {
                    element.classList.add("errorinput");
                    flag = false;
                }
            }
            else {
                element.classList.add("errorinput");
                flag = false;
            }
        }
        else {
            element.classList.remove("errorinput");
        }

        if (!nameRegex.test(inputs[0].value.trim())) {
            flag = false;
            inputs[0].classList.add("errorinput");
        } else {
            inputs[0].classList.remove("errorinput");
        }
        if (!nameRegex.test(inputs[1].value.trim())) {
            flag = false;
            inputs[1].classList.add("errorinput");
        } else {
            inputs[1].classList.remove("errorinput");
        }
        if (!idRegex.test(inputs[2].value.trim())) {
            flag = false;
            inputs[2].classList.add("errorinput");
        } else {
            inputs[2].classList.remove("errorinput");
        }
    });
    if(flag){
        form.submit();
    }
});




