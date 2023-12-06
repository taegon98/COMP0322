
function openMypage() {
    var sessionId = getCookie('sessionId');
    if (sessionId) {
        location.href = "/customer/" + sessionId;
    } else {
        console.log("sessionId not found");
    }
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
}


function openSignupForm() {

    location.href = "/register";
}
function openLoginForm() {

    location.href = "/login";
}
function openCart() {
    location.href = "/";
}
function goToMain() {
    location.href = "/";
}

