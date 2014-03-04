$(document).ready(function() {
	$.get('rest/baskethandler/productlist', function(itemsText) {
		var items = JSON.parse(itemsText);
		addItemsToPage(items);
	});
});

function addItemsToPage(items) {
    
    var pageContent = $('#page_content');
    pageContent.append('<script type="text/javascript" src="scripts/item.js"></script>');
 
    for (var i = 0; i < items.length; i++) {
    	
        var item = items[i];
        var itemId = 'item' + i;
              
       
        itemDiv = createItemSkeleton(pageContent, itemId);
        
        itemDiv.children('.itemImage').append('<img src="' + item.itemURL + '"/>');
        itemDiv.children('.itemInfo').children('.itemName').append('<h3>'+item.itemName+'</h3>');
        itemDiv.children('.itemInfo').children('.itemDescription').append('<p>'+ item.itemDescription + '</p>');
        itemDiv.children('.itemBuyInfo').children('.itemPrice').append('<p> DKK '+ item.itemPrice + ',-</p>');
        itemDiv.children('.itemBuyInfo').children('.itemStock').append('<p> Stock '+ item.itemStock + '</p>');
        
    }
}

function createItemSkeleton(rootDiv, itemId) {
	rootDiv.append('<div class="item" id="' + itemId + '"></div>'); 
    itemDiv = $('#'+itemId);
	itemDiv.append('<script type="text/javascript" src="scripts/item.js"></script>');
	itemDiv.append('<div class="itemImage"></div>');
	
    itemDiv.append('<div class="itemInfo"></div>');
    itemDiv.children('.itemInfo').append('<div class="itemName"></div>');
    itemDiv.children('.itemInfo').append('<div class="itemDescription"></div>');
    
    itemDiv.append('<div class="itemBuyInfo"></div>');
    itemDiv.children('.itemBuyInfo').append('<div class="itemPrice"></div>');
    itemDiv.children('.itemBuyInfo').append('<div class="itemStock"></div>');
    itemDiv.children('.itemBuyInfo').append('<button class="buyItem" type="submit">Buy</button>');
   
    return itemDiv;
}