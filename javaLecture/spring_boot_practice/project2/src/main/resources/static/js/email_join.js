document.addEventListener('DOMContentLoaded', function() {
    const verifyEmailButton = document.getElementById('verify-email-button');
    const verifyCodeButton = document.getElementById('verify-code-button');
    const emailCodeInput = document.getElementById('email-code');
    const emailVerifiedCheck = document.getElementById('email-verified-check');
    const verificationMessage = document.getElementById('verification-message');
    const signupSubmit = document.getElementById('signup-submit');

    verifyEmailButton.addEventListener('click', async function() {
        const email = document.getElementById('email').value;
        if (!email) {
            alert('이메일을 입력해주세요.');
            return;
        }

        try {
            // 이메일 인증 코드 요청 - 서버에 AJAX 요청
            const response = await fetch('/send_verification_code', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email: email })
            });

            const data = await response.json();
            if (data.success) {
                alert('인증 코드가 이메일로 전송되었습니다.');
                emailCodeInput.classList.remove('hidden');
                verifyCodeButton.classList.remove('hidden');
            } else {
                alert('이메일 인증에 실패했습니다. 다시 시도해주세요.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('인증 코드 요청 중 오류가 발생했습니다.');
        }
    });

    verifyCodeButton.addEventListener('click', async function() {
        const email = document.getElementById('email').value;
        const code = emailCodeInput.value;
        if (!code) {
            alert('인증 코드를 입력해주세요.');
            return;
        }

        try {
            // 인증 코드 확인 - 서버에 AJAX 요청
            const response = await fetch('/verify_email_code', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email: email, code: code })
            });

            const data = await response.json();
            if (data.success) {
                alert('이메일 인증이 완료되었습니다.');
                emailVerifiedCheck.classList.remove('hidden');

                // 인증 성공 메시지를 표시하고 스타일 적용
                verificationMessage.textContent = "인증이 완료되었습니다.";
                verificationMessage.classList.add('verified');
                verificationMessage.classList.remove('hidden');

                // 회원가입 버튼 활성화
                signupSubmit.disabled = false;
            } else {
                alert('인증 코드가 올바르지 않습니다. 다시 확인해주세요.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('인증 코드 확인 중 오류가 발생했습니다.');
        }
    });

    // 메세지 자동 숨김
    setTimeout(() => {
        const message = document.querySelector('.message');
        if (message) {
            message.style.display = 'none';
        }
    }, 3000);
});
