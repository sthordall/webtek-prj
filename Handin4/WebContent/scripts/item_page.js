$(document).ready(function() {
	$.get('rest/baskethandler/productlist', function(itemsText) {
		var items = JSON.parse(itemsText);
		addItemsToPage(items);
		
		$('.buyItem').click(function() {
	    	var buttId = this.id;
	    	var itemId = buttId.split('_').pop();
	    	alert(itemId);
	    	$.post('rest/baskethandler/addProductsTobasket/'+itemId, function(status) {
	    		if(status == "success") {
	    			var items = JSON.parse(itemsText);
	    			addItemsToPage(items);
	    		} else {
	    			alert('Something went wrong!');
	    		}
	    	});
	    });
		
		$('.item').hover( function() {
			$(this).children('.itemInfo').children('.itemName').slideUp("fast");
			$(this).children('.itemInfo').children('.itemDescription').slideDown("fast");
		},
		function() {
			$(this).children('.itemInfo').children('.itemDescription').slideUp("fast");
			$(this).children('.itemInfo').children('.itemName').slideDown("fast");
		});
		
	});
});

function addItemsToPage(items) {
    
    var pageContent = $('#page_content');
    for (var i = 0; i < items.length; i++) {
    	
        var item = items[i];
       
        itemDiv = createItemSkeleton(pageContent, items[i].itemID);
        
        itemDiv.children('.itemImage').append('<img src="' + item.itemURL + '"/>');
        itemDiv.children('.itemInfo').children('.itemName').append('<h3>'+item.itemName+'</h3>');
        itemDiv.children('.itemInfo').children('.itemDescription').append('<p>'+ item.itemDescription + '</p>');
        itemDiv.children('.itemBuyInfo').children('.itemPrice').append('<p> DKK '+ item.itemPrice + ',-</p>');
        itemDiv.children('.itemBuyInfo').children('.itemStock').append('<p> Stock '+ item.itemStock + '</p>');
        
        if(item.itemStock <= 0) {
        	 itemDiv.children('.itemBuyInfo').children('.buyItem').attr('disabled', true);
        }
    }
}

function createItemSkeleton(rootDiv, itemId) {
	rootDiv.append('<div class="item" id="' + itemId + '"></div>'); 
    itemDiv = $('#'+itemId);
	itemDiv.append('<div class="itemImage"></div>');
	
    itemDiv.append('<div class="itemInfo"></div>');
    itemDiv.children('.itemInfo').append('<div class="itemName"></div>');
    itemDiv.children('.itemInfo').append('<div class="itemDescription"></div>');
    
    itemDiv.append('<div class="itemBuyInfo"></div>');
    itemDiv.children('.itemBuyInfo').append('<div class="itemPrice"></div>');
    itemDiv.children('.itemBuyInfo').append('<div class="itemStock"></div>');
    itemDiv.children('.itemBuyInfo').append('<button class="buyItem" type="submit" id="buyButt_' + itemId +'">Buy</button>');
    
    return itemDiv;
}