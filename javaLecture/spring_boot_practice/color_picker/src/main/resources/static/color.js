$(function() {
	let td_elements = $('td');
	// console.log(td_elements);

	for (let i = 0; i < td_elements.length; i++) {
		let red = Math.floor(Math.random() * 256);
		let green = Math.floor(Math.random() * 256);
		let blue = Math.floor(Math.random() * 256);
		td_elements.eq(i).css({ 'background': 'rgb(' + red + ',' + green + ',' + blue + ')' })
		td_elements.eq(i).attr('data-red', red);
		td_elements.eq(i).attr('data-green', green);
		td_elements.eq(i).attr('data-blue', blue);
	}
});


$(document).on('click', 'td', function(event) {
	let red = $(this).attr('data-red');
	let green = $(this).attr('data-green');
	let blue = $(this).attr('data-blue');
	// console.log("clicked rgb: ", red, green, blue);

	$.ajax({
		type: 'post',
		url: '/select_action',
		dataType: 'json',
		data: JSON.stringify({
			"red": red,
			"green": green,
			"blue": blue
		}),
		success: function(result) { // 결과 성공 콜백함수
			console.log(result);
		},
		error: function(request, status, error) { // 결과 에러 콜백함수
			console.log(error)
		}
	})
});