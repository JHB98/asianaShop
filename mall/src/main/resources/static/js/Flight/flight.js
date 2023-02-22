//
//function flightSelectCheck() {
//	if ($(':radio[name="fligthRadioButton"]:checked').length < 1) {
//		alert("항공편을 선택해주세요.");
//	} else {
//		$.ajax({
//			url : "/ShopMiniMall/",
//			type: "get",
//			data : 
//		})
//	}
//}

function getFlightList() {
	let arrival = $("#arrival").get(0).value
	let departure = $("#departure").get(0).value
	let boardingDate = $("#boardingDate").get(0).value
	$.ajax({
		url: "/ShopMiniMall/flight", 
		type :"get",
		data : {
			arrival : arrival,
			departure :departure,
			boardingDate: boardingDate
		}
	}).done(function (result) {
		$("#flightList").replaceWith(result)
	});
}
