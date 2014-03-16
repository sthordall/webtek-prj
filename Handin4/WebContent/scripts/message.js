$(document).ready(function() {
	$.get('rest/userhandler/loggedin', function(isLogin) {
		if(isLogin != "loggedIn"){
			alert("You need to be logged in");
			window.location.href="/DynamicMusicShop";
		}
		else{
			alert("Welcome to support page");
		}
	});
	//setInterval("nextMessage()", 200); //update the chart every 200 ms
});

function nextMessage(){
	
}