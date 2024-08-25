
$(document).on('click', 'td', function(e){
    $(this).addClass('open');
    let y = $(this).attr('data-y');
    let x = $(this).attr('data-x');
    let el = $(this);

    $.ajax({
        url: 'http://localhost:8080/game1/check',
        data: {"y": y, "x": x},
        method: "GET",
        dataType: "json"
    }).done(function(result){
        if (result.status == 'safe') {
            el.addClass('open');
        } else if (result.status == 'bomb') {
            el.addClass('bomb');
            alert("Bomb exploded! " + result.player + " loses!");
            window.location.href = 'http://localhost:8080/game1/gameover?player=' + result.player;
        }

        // Update the current player
        $("#currentPlayer").text("Current Player: " + result.player);
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
