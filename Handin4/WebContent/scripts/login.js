$(document).ready(function() {
	
	$('#loginButton').click( function() {
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

function loginSuccesful(username) {
	$('.signin' ).remove();
	$('#page_content').append('<h3>Welcome ' + username + '</h3>');
	$('#page_content').append('<h4>Login Successful!</h3>');
	
	setTimeout(function() {
		location.reload();
	}, 1000);
	
}

function loginUnsuccesful(username) {
	
	document.getElementById("username").value = "";
	document.getElementById("password").value = "";
	alert("Login unsuccessful, try again!");
}
