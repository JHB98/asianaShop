function getPurchaseFromProductPage(product) {
	let amount= $("#amount").get(0).value
	let productNum = product.number;
	let purchaseInfo = {
		productNum : productNum ,
		amount : amount,
	}
	const token = $("meta[name='_csrf']").attr("content")
	const header = $("meta[name='_csrf_header']").attr("content");

	let purchaseInfoList = [purchaseInfo]
	$.ajax({
		url: "/ShopMiniMall/purchasePage", 
		type :"GET",
		data : {  
			purchaseInfoList :purchaseInfoList 
		},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8;"  
		,beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		}
	}).done(function (result) {
		console.log(result)
//		$("#flightList").replaceWith(result)
	});
}
