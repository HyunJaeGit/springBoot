/*
    1. $('#btn-save')의 달러표시는 jQuery라는 이름을 줄여서 사용 (=jQuery('#btn-save'))
    - $()로 선택한 요소는 일반 JavaScript의 DOM 객체가 아니라 jQuery 객체로 반환됩니다.
      이 객체를 통해 jQuery에서 제공하는 다양한 메서드(예: .val(), .text(), .on())를 사용할 수 있습니다.

    2. main 객체: 초기화(init)와 저장(save) 기능을 모듈화.
       init 메서드: 버튼 클릭 이벤트를 등록.
       save 메서드: 입력 값을 수집하고, 서버에 비동기 POST 요청.
       AJAX: 비동기 요청을 처리하며, 성공(done)과 실패(fail)를 분리해 처리.
*/


var main = {

    init : function () {
        var _this = this;

        // 저장 버튼 누르면 기능 호출 (버튼클릭 -> save() 함수 호출 jquery 바인딩)
        $('#btn-save').on('click', function () {
            _this.save();
        });

        // 업데이트 버튼 누르면 기능 호출 (버튼클릭 -> update() 함수 호출 jquery 바인딩)
        $('#btn-update').on('click', function() {
            _this.update();
        });

    },

    // 저장 버튼(save button)
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

       $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
       }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/'
       }).fail(function(error) {
            alert(JSON.stringify(error));
       });
    }

    update : function () {
        var data = {
                title: $('#title').val(),
                content: $('#content').val()
            };
        var id = $('#id').val();

        // HTML 입력폼에서 제목과 내용 값을 읽어서 data 객체에 담는 코드예요.
        // $('#title').val() → id가 title인 <input> 요소의 값을 가져옴
        // $('#content').val() → id가 content인 <textarea> 요소의 값을 가져옴
        // 그걸 data라는 객체에 담음

       $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' +id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
       }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
       }).fail(function() {
            alert(JSON.stringify(error));
       });
    }

};

main.init();