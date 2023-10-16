var notice = {
    // DTO 객체의 속성 설정
    id: "value1",
    title: "value2",
    content: "value3"
};



function getnotice(){
    $.ajax({
        url: "/getnotice",
        type: "POST",
        dataType: "json",
        contentType: "application/json", // 요청의 본문 타입을 JSON으로 설정
        data: JSON.stringify(dto), // DTO 객체를 JSON 문자열로 변환하여 본문에 넣음
    }).done(function (response) {
        // 서버에서 반환한 JSON 객체를 사용
        console.log(response.someProperty);
    }).fail(function (error) {
        // 오류 처리
    });
}
