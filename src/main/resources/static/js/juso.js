$(document).ready(function () {
		
    var clickCount = 0;
    const maxSelection = 3;
    const url_addr = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=";

    // 페이지가 로드되면 첫 번째 데이터를 바로 요청
    searchAddr();
    updateSelectionCount();

    function searchAddr() {
        $.getJSON(url_addr + '*00000000', function (data) {
            displayData(data, 'first');
        });
    }

    function nextAddr(pattern) {
        var url = url_addr + pattern;
        $.getJSON(url, function (data) {
            displayData(data, 'second');
        });
    }

    function displayData(data, stage) {
        var tableContent = '';
        $.each(data.regcodes, function (index, item) {
            if (index % 4 === 0) tableContent += '<tr>';
            tableContent += `<td data-value="${item.code}" data-stage="${stage}">${item.name}</td>`;
            if ((index + 1) % 4 === 0) tableContent += '</tr>';
        });

        if (data.regcodes.length % 4 !== 0) tableContent += '</tr>';
        $('#juso tbody').html(tableContent);

        $('#juso td').off('click').on('click', function () {
            if (clickCount >= maxSelection) {
                alert(`최대 ${maxSelection}번까지만 선택할 수 있습니다.`);
                return;
            }

            var codeValue = $(this).data('value').toString();
            var nameValue = $(this).text();
            var stage = $(this).data('stage');

            if (stage === 'first') {
                const nextPattern = codeValue.substring(0, 2) + '*000000';
                nextAddr(nextPattern);
            } else if (stage === 'second') {
                clickCount++;
                addSelectedAddress(nameValue, codeValue);
                updateSelectionCount();

                $('#juso td').removeClass('selected');
                $(this).addClass('selected');
                resetTable();
            }
        });
    }

    function resetTable() {
        $('#juso tbody').empty();
        searchAddr();
    }

    function addSelectedAddress(name, code) {
        const itemName = `memAddr${clickCount}`;
        const newItem = `<div class="selected-item" data-name="${itemName}" data-code="${code}">${name} <span class="remove-btn" style="color:red; margin-left: 5px;">&times;</span></div>`;
        $('#selected-items').append(newItem);

        const input = `<input type="hidden" name="${itemName}" value="${name}" data-code="${code}">`;
        $('#hidden-inputs').append(input);

        $('.remove-btn').off('click').on('click', function () {
            const codeToRemove = $(this).parent().data('code');
            $(this).parent().remove();

            // 해당 code와 일치하는 hidden input 제거
            $(`#hidden-inputs input[data-code="${codeToRemove}"]`).remove();
            clickCount--;
            updateSelectionCount();
        });
    }

    function updateSelectionCount() {
        $('#selection-count').text(`${clickCount}/${maxSelection}`);
    }
});