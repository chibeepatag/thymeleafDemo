var lastCartUpdate = 0;
function addToCart(itemCode) {

	var req = newXMLHttpRequest();

	req.onreadystatechange = getReadyStateHandler(req, getJSONResponse(req));

	req.open("POST", "addToCart", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send("action=add&item=" + itemCode);
}

function getJSONResponse(req) {// req.responseText
	var updateCartFromJSON = function() {
		updateCart(req.responseText);
	};// close updateCartFromJSON
	return updateCartFromJSON;
}

function removeButtonListener(itemCode) {
	var removeFromCart1 = function() {
		console.log("remove");

		// removeFromCart(itemCode);
	};
	return removeFromCart1;
}

function updateCart(jsonResponse) {
	var cartJSONObj = jsonParse(jsonResponse);

	var cart = cartJSONObj.cart;
	var generated = cart.generated;

	if (generated > lastCartUpdate) {
		lastCartUpdate = generated;
		var contents = document.getElementById("purchases");
		contents.innerHTML = "";

		var items = cart.item;

		for ( var I = 0; I < items.length; I++) {

			var item = items[I];
			var name = item.name;
			var quantity = item.quantity;
			var price = item.price;
			var itemCode = item.code;

			var row = document.createElement("tr");
			var nameCell = document.createElement("td");
			var quantityCell = document.createElement("td");
			var priceCell = document.createElement("td");
			var removeBtnCell = document.createElement("td");	

			nameCell.appendChild(document.createTextNode(name));
			quantityCell.appendChild(document.createTextNode(quantity));
			priceCell.appendChild(document.createTextNode(price));
		
			row.appendChild(nameCell);
			row.appendChild(quantityCell);
			row.appendChild(priceCell);
			row.appendChild(removeBtnCell);
			contents.appendChild(row);
		}

	}
	 tableRowColor();

}
