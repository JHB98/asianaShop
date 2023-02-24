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
		console.log(result)
		$("#flightListSection").replaceWith(result)
	});
}
