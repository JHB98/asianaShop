/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
function getProductByCategory (category) {

    $.ajax({
        url: "/ShopMiniMall/product/category/" + category,
        type: "GET",
    }).done(function (result) {
        $("#resultProduct").replaceWith(result);
    });
}

function idCheck () {
    id = document.getElementById('id').value;
    $.ajax({
        url: "/ShopMiniMall/member/" + id,
        type: "GET",
    }).done(function (result) {
        document.getElementById("idAlert").innerHTML = result;
    });
}

function execDaumPostcode () {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('address').value = data.zonecode + ' ';
            document.getElementById("address").value += addr + ' ';

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("address").value += extraAddr;

            } else {
                document.getElementById("address").value += '';
            }
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address").focus();
        }
    }).open();
}

function passwordCheck () {
    var pwd = document.getElementById('pwd').value;
    var checkPassword = document.getElementById('checkPassword').value;
    if (pwd != checkPassword) {
        document.getElementById("passwordAlert").innerHTML = "비밀번호가 일치 하지 않습니다";
        document.getElementById('passwordAlert').style.color = 'red';
    } else {
        document.getElementById("passwordAlert").innerHTML = "비밀번호가 일치합니다.";
        document.getElementById('passwordAlert').style.color = 'blue';
        return true;
    }
}