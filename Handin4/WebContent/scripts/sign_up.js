/**
 * 
 */

$(document).ready(function() {
	
	$('#signupButton').click( function() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		
		$.post('rest/userhandler/login',"username="+username+"&password="+password, function(loginResponse) {		
			if(loginResponse == "success") {
				loginSuccesful(username);
			} else if (loginResponse == "failure"){
				loginUnsuccesful(username);
			}
		});
	});
	
});
