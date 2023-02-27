/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
function updateCartAmount (index) {
    cartAmount = document.getElementById("cartAmount" + index).value
    cartNum = document.getElementById("cartNum" + index).innerText
    $.ajax({
        url: "/ShopMiniMall/cart/" + cartNum,
        type: "PUT",
        data: {
            amount: cartAmount
        },
        beforeSend: function (jqXHR, settings) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        }
    }).done(function () {
        window.location.reload();
    });
}

function getProductByCategory (category) {
    $.ajax({
        url: "/ShopMiniMall/product/category/" + category + "/" + curPage,
        type: "GET",
    }).done(function (result) {
        $("#resultProduct").replaceWith(result);
    });
}

function requestPay (isObject, cart) {
    let data = [];
    if (isObject != 'Object') {
        for (let i = 0; i < cart.length; i++) {
            data.push({
                userId: cart[i].userId,
                flightNum: document.getElementById("flight_id").value,
                productNum: cart[i].productNum,
                amount: cart[i].amount,
                price: cart[i].totalPrice
            })
        }
    }
    else {
        data.push({
            userId: cart.userId,
            flightNum: document.getElementById("flight_id").value,
            productNum: cart.productNum,
            amount: cart.amount,
            price: cart.totalPrice
        })
    }
    console.log(cart);
    console.log(data);
    var IMP = window.IMP;
    IMP.init("imp11174960");
    IMP.request_pay({
        pg: 'kcp.{상점ID}',
        pay_method: 'card',
        merchant_uid: "57008833-33010",
        name: '의류',
        amount: 100,
        buyer_email: 'Iamport@chai.finance',
        buyer_name: '포트원 기술지원팀',
        buyer_tel: '010-1234-5678',
        buyer_addr: '서울특별시 강남구 삼성동',
        buyer_postcode: '123-456'
    },
        function (rsp) { // callback
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제 완료되었습니다.';
                console.log("성공");
                console.log(rsp);
                $.ajax({
                    url: "/ShopMiniMall/purchase",
                    type: "POST",
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    beforeSend: function (jqXHR, settings) {
                        var header = $("meta[name='_csrf_header']").attr("content");
                        var token = $("meta[name='_csrf']").attr("content");
                        jqXHR.setRequestHeader(header, token);
                    }
                })
                $.ajax({
                    url: "/ShopMiniMall/product/amount",
                    type: "PUT",
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    beforeSend: function (jqXHR, settings) {
                        var header = $("meta[name='_csrf_header']").attr("content");
                        var token = $("meta[name='_csrf']").attr("content");
                        jqXHR.setRequestHeader(header, token);
                    }
                })
                alert(msg);
                location.href = 'http://localhost:8080/ShopMiniMall/mypage';
            } else {
                var msg = '결제 취소되었습니다.';
                console.log("실패");
                console.log(rsp);
                alert(msg);
                location.href = 'http://localhost:8080/ShopMiniMall/cart';
            }
        })
}


function idChecking () {
    document.getElementById("valid_id").innerHTML = null;
    if (document.getElementById("valid_idCheck") != null) {
        document.getElementById("valid_idCheck").innerHTML = null;
    }
    id = document.getElementById('id').value;
    $.ajax({
        url: "/ShopMiniMall/member/" + id,
        type: "GET",
    }).done(function (result) {
        if (!result || id == '' || id == null) {
            document.getElementById("idAlert").innerHTML = "아이디 사용 불가";
            document.getElementById('idAlert').style.color = 'red';
            document.getElementById("idCheck").value = false;
        } else {
            document.getElementById("idAlert").innerHTML = "아이디 사용 가능";
            document.getElementById('idAlert').style.color = 'blue';
            document.getElementById("idCheck").value = true;
        }
    });
}

function passwordCheck () {
    document.getElementById("valid_pwdCheck").innerHTML = null;
    var pwd = document.getElementById('pwd').value;
    var checkPassword = document.getElementById('checkPassword').value;
    if (pwd != checkPassword) {
        document.getElementById("passwordAlert").innerHTML = "비밀번호가 일치 하지 않습니다";
        document.getElementById('passwordAlert').style.color = 'red';
        document.getElementById("pwdCheck").value = false;
    } else {
        document.getElementById("passwordAlert").innerHTML = "비밀번호가 일치합니다.";
        document.getElementById('passwordAlert').style.color = 'blue';
        document.getElementById("pwdCheck").value = true;
        return true;
    }
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

function checkCarts (size) {
    let cartNumList = ''

    for (let i = 0; i < size; i++) {
        if ($('#cartCheck' + i).is(":checked") == true) {
            let cartNum = document.getElementById("cartNum" + i).innerText;
            cartNumList += cartNum + ',';
        }
    }

    document.getElementById('list').value = cartNumList.slice(0, cartNumList.length - 1);
    document.getElementById('purchaseList').value = cartNumList.slice(0, cartNumList.length - 1);
}

function selectFlight (index) {
    document.getElementById('flight_id').value = document.getElementById("flightCheck" + index).value;
    document.getElementById('requestPay').disabled = false
}

function getFlightList () {
    let arrival = document.getElementById("arrival").value
    let departure = document.getElementById("departure").value
    let boardingDate = document.getElementById("boardingDate").value

    $.ajax({
        url: "/ShopMiniMall/flight",
        type: "get",
        data: {
            arrival: arrival,
            departure: departure,
            boardingDate: boardingDate
        }
    }).done(function (result) {
        document.getElementById("flightList").innerHTML = result;
    });
}