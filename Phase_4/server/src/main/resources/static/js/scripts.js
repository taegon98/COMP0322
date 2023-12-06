
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

function openCart() {
    location.href = "/customer/1/cartList";
}

// addToCart 함수 구현 (scripts.js 등에 위치)
function addToCart(productId, count) {
    // productId와 count를 사용하여 서버에 POST 요청을 보내는 로직 작성
    // AJAX 또는 fetch를 사용하여 서버에 데이터 전송
    // 예: jQuery를 사용하는 경우

    $.ajax({
        type: 'POST',
        url: '/your-controller-path/' + productId + '/cart',
        contentType: 'application/json',
        data: JSON.stringify({ id: productId, count: count }),
        success: function(response) {
            // 성공 시 동작
            console.log(response);
        },
        error: function(error) {
            // 실패 시 동작
            console.error(error);
        }
    });
}

