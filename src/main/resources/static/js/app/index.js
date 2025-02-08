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
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
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
       }).fail(function() {
            alert(JSON.stringify(error));
       });
    }

};