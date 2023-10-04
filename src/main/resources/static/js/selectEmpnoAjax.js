function getEmpnos() {
    var selectedDept = $("#dept").val();
    if (selectedDept) {
        $.ajax({
            type: "GET",
            url: "/getEmpnosByDept?dept=" + selectedDept,
            dataType: "json",
            success: function (data) {
                var empnoSelect = $("#empno");
                empnoSelect.empty(); // 기존 옵션 제거
                empnoSelect.append('<option value="">사원을 선택하세요</option>'); // 기본 옵션 추가

                // JSON 배열로 파싱하여 반복
                $.each(data, function (index, item) {
                    empnoSelect.append('<option value="' + item.empno + '">' + item.empno + '</option>');
                });

            }
        });
    } else {
        // 부서를 선택하지 않은 경우, 사원 목록을 비우거나 기본 옵션을 설정하세요.
        var empnoSelect = $("#empno");
        empnoSelect.empty();
        empnoSelect.append('<option value="">사원을 선택하세요</option>');
    }
}