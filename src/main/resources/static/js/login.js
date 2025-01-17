// 로그인 성공시 나타나는 메세지
$(document).ready(function () {
    setTimeout(function () {
        $('#successMessage').fadeOut();
        $('#errorMessage').fadeOut();
    }, 5000);
});