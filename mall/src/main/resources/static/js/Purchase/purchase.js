function getPurchaseFromProductPage(product) {
	let amount= $("#amount").get(0).value
	let productNum = product.number;
	let purchaseInfo = {
		productNum : productNum ,
		amount : amount,
	}

	let purchaseInfoList = [purchaseInfo]
	console.log(purchaseInfoList)
	$.ajax({
		url: "/ShopMiniMall/purchasePage", 
		type :"GET",
		data : {  
			purchaseInfoList :purchaseInfoList 
		},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8;"
	}).done(function (result) {
		console.log(result)
//		$("#flightList").replaceWith(result)
	});
}
