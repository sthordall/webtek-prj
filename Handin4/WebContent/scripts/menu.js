$(document).ready(function() {
	//Determines if user logged in or not, and switch login/logout menu
	$.get('rest/userhandler/loggedin', function(data) {
		if(data == "loggedIn") {
			$('#login_link').html('Logout');
		}
		if(data == "notLoggedIn") {
			$('#login_link').html('Login');
		}
	});
	
	$('#products_link').click( function() {
		$('#page_content').load('views/item_page.html');
	});
	
	$('#login_link').click( function() {
		if($(this).html() == "Login") {
			$('#page_content').load('views/login.html');
		}
		if($(this).html() == "Logout") {
			$.post('rest/userhandler/logout', function () {
				alert("Logged out!");
				location.reload();
			});
		}
		
	});
	
	$('#signup_link').click(function(){
		$('#page_content').load('views/sign_up.html');
	});
});
