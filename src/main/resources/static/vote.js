const vote = document.querySelector("#votenow"),
    alreadyVoted = document.querySelector("#votedalready"),
    retrune = document.querySelector("#return"),
    tovote = document.querySelector(".tovote"),
    ok = document.querySelector(".tovote i"),
    body = document.querySelector("body"),
    votingsec = document.querySelector(".voting-section"),
    votebtn = document.querySelectorAll(".candid .vote-btn"),
    candidate = document.querySelectorAll(".candid"),
    done = document.querySelector("#done"),
    last = document.querySelector(".laststep"),
    candidateNames = document.querySelectorAll(".candid .descript h3"),
    tobe = document.querySelector("#tobe"),
    confirmm = document.querySelector("#confirm"),
    cancel = document.querySelector("#cancel"),
    finished = document.querySelector(".finished"),
    redirct = document.querySelector(".toconfrim"),
    voice = document.querySelector("#cardd"),
    confirmedVote = document.querySelector("#voting");
let flag = false;
done.disabled = true;
votebtn.forEach((button, index) => {
    button.addEventListener("click", () => {
        flag = true;
        candidate.forEach((Element, indexx) => {
            Element.classList.remove("choosed");
            votebtn[indexx].innerHTML = "vote";
        })
        button.innerHTML = "selected";
        candidate[index].classList.add("choosed");
        choicer(flag, index);
    })
});
function choicer(flag, index) {
    if (flag) {
        done.disabled = false;
    }

    confirmedVote.addEventListener("submit", (event) => {
        event.preventDefault();
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/confrimed', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    voice.innerHTML = xhr.responseText;
                    last.style.display = "none";
                    finished.style.display = "block";
                    vote.innerHTML = "VOTED";
                    vote.disabled = true;
                }
                else if (xhr.status === 302) {
                    window.location.href = xhr.getResponseHeader('Location'); // Redirect manually
                }
            }
        }
        xhr.send(candidates[index]["candId"]);
    });

    redirct.addEventListener("submit", (event) => {
        if (flag) {
            event.preventDefault();
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/done', true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.responseText !== "302") {
                        last.style.display = "block";
                        votingsec.style.display = "none";
                        tobe.innerHTML = candidates[index]["fname"] + candidates[index]["lname"];
                    } else if (xhr.responseText === "302") {
                        window.location.href = "/login"; // Redirect manually
                    }

                }
            }
            xhr.send();
        }
    });
}
cancel.addEventListener("click", () => {
    last.style.display = "none";
    votingsec.style.display = "block";
});

retrune.addEventListener("click", () => {
    tovote.style.display = 'none';
});
// alreadyVoted.disabled=true;
ok.addEventListener("click", () => {
    tovote.style.display = "none";
    body.style.overflow = "visible";
})

vote.addEventListener('click', function () {
    tovote.style.display = 'grid';
    var height = document.body.offsetHeight;
    popupHeight = votingsec.offsetHeight;
    buttonRect = vote.getBoundingClientRect();
    buttonTop = buttonRect.top + window.scrollY;
    popheight = (height / 2) - popupHeight;
    window.scrollTo({
        top: popheight,
        behavior: 'smooth'
    });
    tovote.style.display = 'grid';
});

