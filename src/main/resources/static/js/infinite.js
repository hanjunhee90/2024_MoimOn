$(document).ready(function () {
    // 몇 번째 번호로 요청할지
    var page = 2;
    // 요청 중인지 여부
    var isLoading = false;
    // 처음 시간
    var firstScrollTime = null;
    // 이전에 스크롤 위치
    var lastScrollTop = 0;
    // debounce 함수에서 사용되는 타이머
    var debounceTimer;

    // 빠르게 발생하는 이벤트를 제어하기 위한 함수
    const debounce = (func, delay) => {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(func, delay);
    };

    function loadPosts() {
        if (isLoading) return; // 이미 로딩중이면 요청하지 않음
        isLoading = true;

        // 최소 스크롤시간
        if (!firstScrollTime) {
            firstScrollTime = new Date().toISOString();
        }
        
        console.log("요청!");
        $.ajax({
            url: "/get-posts",
            method: "POST",
            data: {
                curPage: page,
                currentTime: firstScrollTime
            },
            success: function (data) {
                console.log(data);
                if (data.length == 0) {
                    console.log("끝!");
                    $(window).off('scroll', scrollHandler);
                    isLoading = false;
                    return;
                }
                data.forEach(post => {
                    $('.profile-feed').append(
                        `<div class="d-flex align-items-start profile-feed-item"> 
                            <img src="/jjanggu/${post.authorImg}" alt="profile" class="img-sm rounded-circle"> 
                            <div class="ml-4">
                                <h5>${post.author} (${post.rnum}) 
                                    <small class="ml-4 text-muted"><i 
                                        class="mdi mdi-clock mr-1"></i>${post.postTime}</small> 
                                </h5> 
                                <h6 class="mdi mdi-clock mr-1">${post.relativeTime}</h6>
                                <p>${post.content}</p> 
                                <p class="small text-muted mt-2 mb-0"> 
                                    <span> <i class="mdi mdi-star mr-1"></i>4 
                                    </span> <span class="ml-2"> <i class="mdi mdi-comment mr-1"></i>11
                                    </span> <span class="ml-2"> <i class="mdi mdi-reply"></i>
                                    </span> 
                                </p> 
                            </div>
                        </div>`
                    );
                });
                page++;
            },
            complete: function () {
                isLoading = false; // 요청 완료 후 로딩 상태 업데이트
            }
        });
    }

    const scrollHandler = function () {
        debounce(function () {
            var currentScrollTop = $(window).scrollTop();
            // 이전보다 아래로 이동
            if (currentScrollTop > lastScrollTop) {
                // 사용자가 페이지의 하단 약 100px 이내 있을때 동작
                if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
                    loadPosts();
                }
            // 위로 이동
            } else {
                if ($(window).scrollTop() == 0) {
                    // 완전 위에 왔을때
                    if (confirm("새로고침 할까요?!!")) {
                        firstScrollTime = new Date().toISOString();
                        $('.profile-feed').empty();
                        page = 1;
                        console.log("새로고침");
                        loadPosts();
                    }
                }
            }
            lastScrollTop = currentScrollTop;
        }, 250); // 250 밀리초 지연을 적용
    };

    // 스크롤 이벤트 등록
    $(window).on('scroll', scrollHandler);
});
