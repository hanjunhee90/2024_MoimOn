// 이미지 미리보기 기능
$(document).ready(function () {
	$('#profileImageUpload').on('change', function() {
        if ($(this).val()) {
            $('#submitButton').removeAttr('disabled');
        } else {
            $('#submitButton').attr('disabled', true);
        }
    });

   $('#profileImageUpload').on('change', function () {
       const file = this.files[0];
       if (file) {
           const reader = new FileReader();
           reader.onload = function (e) {
               $('#profilePreview').attr('src', e.target.result);
           };
           reader.readAsDataURL(file);
       }
   });
});