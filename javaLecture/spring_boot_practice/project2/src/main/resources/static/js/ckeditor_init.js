
document.addEventListener('DOMContentLoaded', function() {
	ClassicEditor
		// create() : ClassicEditor 클래스의 정적 메서드로, 실제로 CKEditor 인스턴스를 생성하는 역할
		.create(document.querySelector('#editor'), {
			toolbar: [
				'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote', '|',
				'insertImage', 'insertVideo', 'insertFile', 'undo', 'redo'
			],
			ckfinder: {
				// 기본 업로드 경로를 설정하지 않고 커스텀 어댑터에서 처리
			}
		})
		.then(editor => {
			editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
				return new CustomUploadAdapter(loader);
			};
		})
		.catch(error => {
			console.error(error);
		});
});

// Custom Upload Adapter 정의
class CustomUploadAdapter {
	constructor(loader) {
		this.loader = loader;
	}

	upload() {
		return this.loader.file
			.then(file => new Promise((resolve, reject) => {
				const data = new FormData();
				data.append('upload', file);

				let uploadUrl = '/upload-file'; // 기본 파일 업로드 경로

				// 파일 유형에 따라 업로드 경로 변경
				if (file.type.startsWith('image/')) {
					uploadUrl = '/upload-image';
				} else if (file.type.startsWith('video/')) {
					uploadUrl = '/upload-video';
				}

				fetch(uploadUrl, {
					method: 'POST',
					body: data
				})
					.then(response => response.json())
					.then(result => {
						if (result && result.url) {
							resolve({
								default: result.url
							});
						} else {
							reject(result.message || 'Upload failed');
						}
					})
					.catch(error => {
						reject('Upload failed: ' + error.message);
					});
			}));
	}

	abort() {
		// 업로드가 중단된 경우 처리를 위한 로직
	}
}
