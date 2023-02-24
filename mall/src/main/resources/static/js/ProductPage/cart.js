
function createCartItem(cart) {
    amount = $("#amount")[0].value
    console.log({
        productNum : cart["number"],
        ...cart,
        totalPrice: cart["price"] * amount,            
        amount : amount 
    })
    $.ajax({
		type :"post",
		url: "/ShopMiniMall/cart", 
		data : {
            productNum : cart["number"],
            ...cart,
            totalPrice: cart["price"] * amount,            
            amount : amount 
		}, dataType : 'text'
	}).done(function (result) {
        alert("장바구니에 추가되었습니다.")
	});
}
