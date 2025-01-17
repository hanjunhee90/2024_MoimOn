// 참가비가 있는 경우에만 금액 입력창과 용도 선택창 표시
$(document).ready(function() {
	// 선택된 라디오 버튼의 값 가져오기

	// 히든 인풋 필드에 선택된 값 설정
	
	// 모임 유형 선택 시 카테고리 표시
    $('input[name="moimCtg"]').on('change', function() {
        if ($(this).val() === '모임') {
			$('#boardCategory').val(100);
            $('#categorySection').show();
        } else {
			$('#boardCategory').val(200);
            $('#categorySection').hide();
            $('input[name="moimInterest"]').prop('checked', false);
            $('#previewCategory').text('선택 안됨');
        }
        $('#previewEventType').text($(this).val());
    });

    // 카테고리 선택 미리보기
    $(document).on('change', 'input[type="radio"][name="moimInterest"]', function() {
        const selectedCategory = $(this).next('label').text();
        $('#previewCategory').text(selectedCategory);
    });
	
	// 날짜와 시간 선택후 합쳐서 숨겨진 input에 저장후 moimvo 의 name에 맞춰 값 세팅 moimDt
    $('#moimDate, #moimTime').on('input', function() {
        const date = $('#moimDate').val();
        const time = $('#moimTime').val();
        if (date && time) {
            $('#moimCombinedDt').val(`${date} ${time}`);
        } else {
            $('#moimCombinedDt').val('');
        }
    });
	
	// 승인 여부 선택 시 미리보기 업데이트
	$('input[name="moimConfirm"]').on('change', function() {
	    const selectedApproval = $('input[name="moimConfirm"]:checked').val();
	    $('#previewMoimConfirm').text(selectedApproval);
	});
	
    // 참가비가 있는 경우에만 금액 입력창과 용도 선택창 표시
    $('input[name="moim_fee"]').on('change', function() {
        const hasFee = $(this).val() === '1';
        $('#feeAmount').toggle(hasFee);
        $('#feePurposeSection').toggle(hasFee);
        const fee = hasFee ? '있음' : '없음';
        $('#previewFee').text(fee);
        if (!hasFee) {
            $('#previewFeePurpose').text('없음');
            $('#previewFeeAmount').text('미설정');
        }
    });

    // 참가비 금액 미리보기
    $('#feeInput').on('input', function() {
        const feeAmount = $(this).val() || '미설정';
        $('#previewFeeAmount').text(feeAmount + ' 원');
    });

	// 기타 용도 선택 시 추가 입력창 표시
	$('#otherFee').on('change', function() {
	    $('#otherTextInput').toggle($(this).is(':checked'));
	    updateFeePurposePreview(); // 미리보기 업데이트
	});

	// 기타 용도 입력 내용 미리보기 및 참가비 사용 용도 미리보기 통합
	$('#otherText').on('input', function() {
	    updateFeePurposePreview(); // 미리보기 업데이트
	});

	// 참가비 사용 용도 미리보기 통합 함수
	function updateFeePurposePreview() {
	    let purposes = [];

	    // 일반 용도 체크 여부 확인
	    if ($('input[name="moimFeedetail1"]').is(':checked')) {
	        purposes.push($('label[for="venueFee"]').text());
	    }
	    if ($('input[name="moimFeedetail2"]').is(':checked')) {
	        purposes.push($('label[for="materialsFee"]').text());
	    }
	    if ($('input[name="moimFeedetail3"]').is(':checked')) {
	        purposes.push($('label[for="snacksFee"]').text());
	    }

	    // 기타 용도 체크 및 입력 내용 확인
	    if ($('#otherFee').is(':checked') && $('#otherText').val()) {
	        purposes.push($('#otherText').val());
	    }

	    const purposesHtml = purposes.length > 0 ? purposes.map(purpose => `<p>${purpose}</p>`).join('') : '없음';
	    $('#previewFeePurpose').html(purposesHtml);
	}

	// 참가비 사용 용도 체크박스 변경 시 미리보기 업데이트
	$('input[name="moimFeedetail1"], input[name="moimFeedetail2"], input[name="moimFeedetail3"], #otherFee').on('change', function() {
	    updateFeePurposePreview(); // 미리보기 업데이트
	});

    // 참여 인원 표시 및 유효성 검사
    $('#minParticipants, #maxParticipants').on('input', function() {
        const min = parseInt($('#minParticipants').val()) || 0;
        const max = parseInt($('#maxParticipants').val()) || 0;
        if (min > max) {
            alert("최소 인원이 최대 인원보다 클 수 없습니다.");
            $(this).val(min > max ? max : min);
        }
        $('#previewParticipants').text(`${min}~${max}`);
    });

    // 나이대 설정 및 유효성 검사
    $('#minAge, #maxAge').on('input', function() {
        const minAge = parseInt($('#minAge').val()) || 10;
        const maxAge = parseInt($('#maxAge').val()) || 10;
        if (minAge > maxAge) {
            alert("최소 나이는 최대 나이보다 작거나 같아야 합니다.");
            $(this).val(minAge > maxAge ? maxAge : minAge);
        }
        $('#previewAge').text(`${minAge}~${maxAge}`);
    });

    // 유형 선택 미리보기
    $('input[name="moim_type"]').on('change', function() {
        const eventType = $('input[name="moim_type"]:checked').val();
        $('#previewEventType').text(eventType);
    });

    // 모임 장소, 날짜, 시간 미리보기
    $('#moimPlace').on('input', function() {
        $('#previewPlace').text($(this).val() || '미설정');
    });

    $('#moimDate').on('input', function() {
        $('#previewDate').text($(this).val() || '미설정');
    });

    $('#moimTime').on('input', function() {
        $('#previewTime').text($(this).val() || '미설정');
    });
	// 작성자 미리보기
    $('#author').on('input', function() {
        $('#previewAuthor').text($(this).val() || '미설정');
    });

    // 제목 미리보기
    $('#title').on('input', function() {
        $('#previewTitle').text($(this).val() || '미설정');
    });	
	
});