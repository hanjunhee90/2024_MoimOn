$(document).ready(function () {
    // 아이디 중복 체크 함수 전역으로 선언
    window.checkDuplicateId = function () {
        var memId = $('#memId').val();

        if (memId) {
            $.ajax({
                type: 'GET',
                url: '/api/checkid',
                data: { memId: memId },
                success: function(response) {
                    if (response === 'available') {
                        $('#idCheckResult').text('사용 가능한 아이디입니다.').css('color', 'green');
                    } else {
                        $('#idCheckResult').text('이미 사용 중인 아이디입니다.').css('color', 'red');
                    }
                },
                error: function() {
                    $('#idCheckResult').text('아이디 확인 중 오류가 발생했습니다.').css('color', 'red');
                }
            });
        }
    };

    // 비밀번호 일치 확인
    $('#memPass, #passwordConfirm').on('input', function () {
        const password = $('#memPass').val();
        const passwordConfirm = $('#passwordConfirm').val();
        const feedback = $('#passwordFeedback');

        if (password === passwordConfirm) {
            feedback.text('비밀번호가 일치합니다.').css('color', 'green');
        } else {
            feedback.text('비밀번호가 일치하지 않습니다.').css('color', 'red');
        }
    });

});