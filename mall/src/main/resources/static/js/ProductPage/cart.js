
function createCartItem(cart) {
    amount = $("#amount")[0].value
    console.log({
        productNum : cart["number"],
        ...cart,
        totalPrice: cart["price"] * amount,            
        amount : amount 
    })
    const token = $("meta[name='_csrf']").attr("content")
	const header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
		type :"post",
		url: "/ShopMiniMall/cart", 
		data : {
            productNum : cart["number"],
            ...cart,
            totalPrice: cart["price"] * amount,            
            amount : amount 
		}, dataType : 'text',beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		}
	}).done(function (result) {
        alert("장바구니에 추가되었습니다.")
	});
}
