function updateSelectedCount() {
    // 선택된 메일 체크박스들을 가져온다.
    const selectedMailCheckboxes = document.querySelectorAll('input[name="selectedMail"]:checked');
    // 선택된 메일 개수를 계산한다.
    const selectedCount = selectedMailCheckboxes.length;
    // 선택된 메일 개수를 표시하는 HTML 요소를 가져온다.
    const selectedCountElement = document.getElementById('selectedCount');
    // 선택된 메일 개수를 표시하는 요소의 내용을 업데이트한다.
    selectedCountElement.textContent = selectedCount.toString();
}

// 선택된 메일을 삭제하는 함수
function deleteSelectedMails() {
    // 선택된 메일 체크박스들을 가져온다.
    const selectedMailCheckboxes = document.querySelectorAll('input[name="selectedMail"]:checked');
    // 선택된 메일의 ID를 배열로 반환한다.
    const selectedMailIds = Array.from(selectedMailCheckboxes).map(checkbox => checkbox.value);
    // 선택된 메일 개수를 계산한다.
    const selectedCount = selectedMailCheckboxes.length;
    // 선택된 메일 개수를 표시하는 HTML 요소를 가져온다.
    const selectedCountElement = document.getElementById('selectedCount');

    // 선택된 메일이 없는 경우 알림을 표시하고 함수를 종료한다.
    if (selectedCount === 0) {
        alert('선택된 메일이 없습니다.');
        return;
    }

    // 사용자에게 메일 삭제 확인 메시지를 표시하고, 확인한 경우 삭제 요청을 서버로 보낸다.
    if (confirm('선택된 ' + selectedCount + '개의 메일을 삭제하시겠습니까?')) {
        // 서버로 선택된 메일 ID 목록을 전달하여 삭제 처리를 요청한다.
        fetch('/deleteMails', {
            method: 'POST',
            // 선택된 메일 ID 목록을 JSON 형식을 변환하여 전송한다.
            body: JSON.stringify(selectedMailIds),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            // 삭제 후에 필요한 동작 수행한다. (예: 리스트 리로드)
            location.reload();
        }).catch(error => {
            // 오류가 발생한 경우 콘솔에 오류 메시지를 출력한다.
            console.error('메일 삭제 중 오류 발생:', error);
        });
    }
}

// 페이지 상단의 전체 선택 체크박스 요소를 가져옵니다.
const selectAllCheckbox = document.getElementById('selectAllCheckbox');

// 페이지 상단의 전체 선택 체크박스의 상태가 변경될 때마다 호출되는 함수를 정의합니다.
selectAllCheckbox.addEventListener('change', function() {
    // 페이지 내의 모든 체크박스 요소를 가져옵니다.
    const mailCheckboxes = document.querySelectorAll('input[name="selectedMail"]');

    // 페이지 상단의 전체 선택 체크박스의 상태에 따라 모든 체크박스의 상태를 변경합니다.
    mailCheckboxes.forEach(function(checkbox) {
        checkbox.checked = selectAllCheckbox.checked;
    });

    // 선택된 메일 개수를 표시하는 HTML 요소를 가져옵니다.
    const selectedCountElement = document.getElementById('selectedCount');

    // 전체 선택 체크박스의 상태에 따라 선택된 메일 개수를 업데이트합니다.
    if (selectAllCheckbox.checked) {
        // 전체 선택 체크박스가 선택된 경우, 모든 체크박스가 선택되므로 개수를 표시합니다.
        selectedCountElement.textContent = mailCheckboxes.length.toString();
    } else {
        // 전체 선택 체크박스가 선택 해제된 경우, 선택된 메일이 없으므로 "0"을 표시합니다.
        selectedCountElement.textContent = '0';
    }
});

// 선택된 메일을 삭제하는 함수
function deleteSelectedMails() {
    // 선택된 메일 체크박스들을 가져옵니다.
    const selectedMailCheckboxes = document.querySelectorAll('input[name="selectedMail"]:checked');
    // 선택된 메일의 ID를 배열로 반환합니다.
    const selectedMailIds = Array.from(selectedMailCheckboxes).map(checkbox => checkbox.value);
    // 선택된 메일 개수를 계산합니다.
    const selectedCount = selectedMailCheckboxes.length;
    // 선택된 메일 개수를 표시하는 HTML 요소를 가져옵니다.
    const selectedCountElement = document.getElementById('selectedCount');

    // 선택된 메일이 없는 경우 알림을 표시하고 함수를 종료합니다.
    if (selectedCount === 0) {
        alert('선택된 메일이 없습니다.');
        return;
    }

    // 사용자에게 메일 삭제 확인 메시지를 표시하고, 확인한 경우 삭제 요청을 서버로 보냅니다.
    if (confirm('선택된 ' + selectedCount + '개의 메일을 삭제하시겠습니까?')) {
        // 서버로 선택된 메일 ID 목록을 전달하여 삭제 처리를 요청합니다.
        fetch('/deleteMails', {
            method: 'POST',
            // 선택된 메일 ID 목록을 JSON 형식으로 변환하여 전송합니다.
            body: JSON.stringify(selectedMailIds),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            // 삭제 후에 필요한 동작을 수행합니다. (예: 리스트 리로드)
            location.reload();
        }).catch(error => {
            // 오류가 발생한 경우 콘솔에 오류 메시지를 출력합니다.
            console.error('메일 삭제 중 오류 발생:', error);
        });
    }
}


