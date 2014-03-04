////When DOM is fully loaded, such as images etc.
//$(document).ready(function() {
//	$('#addToBasket').click( function() {
//		$('#basket_content').html('<table id="basket_table"></table>');
//		//TODO: Create WebMethod for getting the product
//		//and add it to basket_table
//		$.get('rest/baskethandler/addProductsTobasket', function(itemText) {
//			var item = JSON.parse(itemText);
//            addItemToBasketTable(item);
//		});
//	});
//
//});
//
//function addItemToBasketTable(item) {
//    //Get the table body we we can add items to it
//    var tableBody = document.getElementById("items_table");
//    //Remove all contents of the table body (if any exist)
//    tableBody.innerHTML = "";
//
//    //Loop through the items from the server
//    for (var i = 0; i < items.length; i++) {
//        var item = items[i];
//        //Create a new line for this item
//        var tr = document.createElement("tr");
//
//        var nameCell = document.createElement("td");
//        nameCell.textContent = item.name;
//        tr.appendChild(nameCell);
//
//        var priceCell = document.createElement("td");
//        priceCell.textContent = item.price;
//        tr.appendChild(priceCell);
//
//        tableBody.appendChild(tr);
//    }
//}


/*TEST*/

$(document).ready(function() {
	$('#products_link').click( function() {
		$('#page_content').html('<table id="items_table"></table>');
		$.get('rest/baskethandler/productlist', function(itemsText) {
			var items = JSON.parse(itemsText);
            addItemsToTable(items);
		});
	});

});

function addItemsToTable(items) {
    //Get the table body we we can add items to it
    var tableBody = document.getElementById("items_table");
    //Remove all contents of the table body (if any exist)
    tableBody.innerHTML = "";

    //Loop through the items from the server
    for (var i = 0; i < items.length; i++) {
        var item = items[i];
        //Create a new line for this item
        var tr = document.createElement("tr");

        var nameCell = document.createElement("td");
        nameCell.textContent = item.itemName;
        tr.appendChild(nameCell);

        var priceCell = document.createElement("td");
        priceCell.textContent = item.itemPrice;
        tr.appendChild(priceCell);

        tableBody.appendChild(tr);
    }
}