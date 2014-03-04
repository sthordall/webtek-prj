$(document).ready(function() {	
	$(".item").hover( function() {
		$(this).children('.itemInfo').children('.itemName').slideUp("fast");
		$(this).children('.itemInfo').children('.itemDescription').slideDown("fast");
	},
	function() {
		$(this).children('.itemInfo').children('.itemDescription').slideUp("fast");
		$(this).children('.itemInfo').children('.itemName').slideDown("fast");
	});
});

