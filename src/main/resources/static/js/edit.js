/*
사용법 !!
<div id="boardContent"></div>

@PostMapping("/testEdit")
public ResponseEntity<String> test(@RequestPart(value = "images", required = false) MultipartFile[] images, @RequestParam("boardContent") String boardContent) {
	System.out.println("일단옴");
	System.out.println(boardContent);
	if (images != null && images.length > 0) {
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String originalFilename = image.getOriginalFilename();
                System.out.println("파일 이름: " + originalFilename);
            }
        }
    }
    return ResponseEntity.ok("이미지 업로드 성공");
}

*/
document.addEventListener("DOMContentLoaded", function() {
    const editorElement = document.querySelector('#boardContent');
    if (editorElement) { // #edit 요소가 존재하는 경우에만 에디터 초기화
        let imagesToUpload = [];  // 전송할 이미지를 저장할 배열

        const editor = new toastui.Editor({
            el: editorElement,
            height: '500px',
            initialEditType: 'wysiwyg',
            initialValue: '',
            previewStyle: 'vertical',
            language: 'ko',
            hooks: {
                addImageBlobHook: (blob, callback) => { 
                    const imageUrl = URL.createObjectURL(blob);
                    imagesToUpload.push(blob);  // 전송할 이미지 저장
                    callback(imageUrl, 'image alt attribute'); // 에디터에 미리 보기 추가
                }
            }
        });

        // "저장" 버튼 클릭 시 전체 폼 데이터와 이미지 업로드
        document.getElementById("postForm").addEventListener("submit", async function(event) {
            event.preventDefault();  // 기본 폼 제출 방지

            const formData = new FormData(this);  // 폼 데이터 전체를 포함한 FormData 객체 생성
            formData.append("boardContent", editor.getHTML()); // 에디터 내용 추가
			
            // 각 이미지 파일을 FormData에 추가
            imagesToUpload.forEach((blob, index) => {
                formData.append("images", blob);  // 여러 파일을 'images'라는 동일한 이름으로 추가
				console.log(formData);
            });

            try {
                const actionUrl = this.getAttribute("action");  // 폼의 action 속성에서 URL 가져오기
				console.log("보내는 요청 url : "+actionUrl);
                const response = await fetch(actionUrl, {
                    method: 'POST',
                    body: formData,
                });

                if (!response.ok) {
                    throw new Error('저장 실패');
                }

                alert("게시물이 성공적으로 저장되었습니다.");
                window.location.reload();  // 저장 후 페이지 새로고침
            } catch (error) {
                console.error('저장 실패 : ', error);
                alert("게시물 저장 중 오류가 발생했습니다.");
            }
        });
    }
});