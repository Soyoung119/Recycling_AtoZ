function uploadImage() {
    const fileInput = document.getElementById('image-input');
    const file = fileInput.files[0];

    if (file) {
        // 파일을 FormData에 추가
        const formData = new FormData();
        formData.append('image', file);

        // Vision API에 이미지 업로드
        fetch('/api/uploadImage', {
              method: 'POST',
              body: formData,
        })
        .then(response => response.json())
        .then(data => {
        console.log('Vision API Response:', data);
              // Vision API에서 반환한 분석 결과
          document.getElementById('visionResult').innerText = JSON.stringify(data);
              // 여기에서 결과를 원하는 방식으로 처리하세요.
        })
        .catch(error => console.error('Error:', error));
    }
}
