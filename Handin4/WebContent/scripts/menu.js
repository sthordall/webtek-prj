$(document).ready(function() {
	$('#products_link').click( function() {
		$('#page_content').load('views/item_page.html');
	});
	
	$('#login_link').click( function() {
		$('#page_content').load('views/login.html');
	});
});
