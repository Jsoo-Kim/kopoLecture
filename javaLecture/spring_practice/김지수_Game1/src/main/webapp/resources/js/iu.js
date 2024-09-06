$(document).ready(function() {
    
    const $playerCountSelect = $('#playerCount');
    const $userInputsDiv = $('#userInputs');

    function generateUserInputs(count) {
        $userInputsDiv.empty(); // 기존 입력 필드 제거
        for (let i = 1; i <= count; i++) {
            $userInputsDiv.append(`
                <label for="user${i}">사용자 ${i} 이름:</label>
                <input type="text" id="user${i}" name="user${i}" required />
                <br>
            `);
        }
    }
    
    // 페이지가 로드될 때 기본 선택된 값으로 입력 필드 생성
    generateUserInputs($playerCountSelect.val());

    // 드롭다운 선택이 변경될 때마다 입력 필드 생성
    $playerCountSelect.on('change', function() {
        generateUserInputs($playerCountSelect.val());
    });

    $('#userForm').on('submit', function(e) {
        e.preventDefault(); // 폼 제출 방지        
 
        // 데이터를 명시적으로 객체로 구성
        const formData = {
            playerCount: $('#playerCount').val(),
            mapSize: $('#mapSize').val()
        };

        // 각 사용자 입력 필드를 순회하여 데이터를 추가
        const userInputs = $('#userInputs input[type="text"]');
        for (let i = 0; i < userInputs.length; i++) {
            const inputElement = userInputs[i];
            formData['user' + (i + 1)] = $(inputElement).val();
        }      
        
        $.ajax({
            url: 'http://localhost:8088/game1/input_action',
            method: "POST", 
            data:formData, 
            success: function(response) {
                $('#message').show(); // 메시지 표시
                setTimeout(function() {
                window.location.href = 'http://localhost:8088/game1/';
            }, 500);
            },
            error: function() {
                alert('제출 실패');
            }
        });
    });

    $('#homeButton').on('click', function() {
        location.href = contextPath + '/';
    });
});

