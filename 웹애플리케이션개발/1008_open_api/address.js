$(document).on('keyup', '.search_text', function(event) { // 키보드 이벤트
    // console.log(event.which);

    if (event.which == 13) { // 키보드 이벤트 코드! 13은 Enter 키 (8은 Backspace)
        let search_text = $(this).val();
        if (search_text.trim() === "") {
            alert("검색어를 입력해 주세요.");
            return;
        }

        $.ajax({
            url: 'https://www.juso.go.kr/addrlink/addrLinkApi.do',
            data: {
                "currentPage": 1,
                "countPerPage": 10,
                "resultType": "json",
                "confmKey": "devU01TX0FVVEgyMDI0MTAwODA5NDgyOTExNTEzOTE=",
                "keyword": search_text,
            },
            method: "GET",
        }).done(function(result) {
            console.log(result);

            $('.juso_result').html('');
            let data = result['results']['juso'];
            if (data && data.length > 0) {
                for (let i = 0; i < data.length; i++) {
                    let addr = data[i]['roadAddr'];
                    let detailAddr = data[i]['jibunAddr'];
                    $(`.juso_result`).append(
                        `<li data-detail="${detailAddr}">${addr}</li>`
                    );
                }
            } else {
                $('.juso_result').append('<li>검색 결과가 없습니다.</li>');
            }
        }).fail(function() {
            alert("주소 검색에 실패했습니다. 다시 시도해 주세요.");
        })
    }
})


$(document).on('click', 'li', function(e){
    let selectedAddr = $(this).text();
    let detailAddr = $(this).data('detail');
    $('.select_address').html(`<b>전체 도로명 주소</b>: ${selectedAddr}<br><b>지번 주소</b>: ${detailAddr}`)
    // $('.select_address').text($(this).text());
    $('.popup_background').hide();
});

$(document).on('click', '.open_popup', function(e){
    $('.popup_background').show();
});