
$(document).on('click', 'td', function(e){
    let y = $(this).attr('data-y');
    let x = $(this).attr('data-x');
    let el = $(this);
    $.ajax({
        url: 'http://localhost:8088/game1/check',
        data: {"y": y, "x": x},
        method: "GET"
    }).done(function(result){
        if (result.result == 'safe') {
            el.addClass('open');
        } else if (result.result == 'bomb') {
            // 폭발 애니메이션 적용
            el.addClass('bomb');
            
            // 폭발음 재생
            let explosionSound = document.getElementById('explosion-sound');
            explosionSound.play();

            // 1초 후에 game_over 페이지로 이동
            setTimeout(function() {
                window.location.href = 'http://localhost:8088/game1/game_over';
            }, 1500);
        }
        
        // 현재 턴의 사용자 이름을 업데이트
        if (result.currentPlayer) {
            $('h2').text('현재 턴: ' + result.currentPlayer);
        }
        console.log(result);
    });
});

$(function(){
    let tr_elements = $('tr');
    for (let i = 0; i < tr_elements.length; i++) {
        let td_els = tr_elements.eq(i).find('td');
        for (let j = 0; j < td_els.length; j++) {
            td_els.eq(j).attr('data-y', i);
            td_els.eq(j).attr('data-x', j);
        }
    }
});