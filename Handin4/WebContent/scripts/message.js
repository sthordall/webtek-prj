$(document).ready(function() {
	$.get('rest/userhandler/loggedin', function(isLogin) {
		if(isLogin != "loggedIn"){
			alert("You need to be logged in");
			window.location.href="/DynamicMusicShop";
		}
		else{
			alert("Welcome to support page");
			getUserInfo();
		}
	});
	//setInterval("nextMessage()", 200); //update the chart every 200 ms
	var updateMessage = function(){
		 $.get('rest/messageHandler/getNextMessage', function(gsonMessage) {
			 
			 if(gsonMessage != "noUpdate"){
			 var msg = JSON.parse(gsonMessage);
		        //Update the DOM with new information
			 $('#chat').append('<div class="msg">[' +msg.dateSent+ '] <strong>'+msg.user+'</strong>: '+msg.message+'</div>');
		        //Run again after 200 milliseconds
		        window.setTimeout(updateMessage, 1000);
			 }
		});
		
	};
	window.setTimeout(updateMessage, 1000);
});

function getUserInfo(){
	$.get('rest/messageHandler/getUserInfo', function(username) {
		var userNameTextField = document.getElementById('userN');
		userNameTextField.value = username;
	});
}

