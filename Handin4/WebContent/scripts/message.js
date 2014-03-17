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
		NoobHasOurProduct();
	});
	setInterval("nextMessage()", 200); //update the chart every 200 ms
//	var updateMessage = function(){
//		 $.get('rest/messageHandler/getNextMessage', function(gsonMessage) {
//			 
//			 if(gsonMessage != "noUpdate"){
//			 var msg = JSON.parse(gsonMessage);
//		        //Update the DOM with new information
//			 $('#chat').append('<div class="msg">[' +msg.dateSent+ '] <strong>'+msg.user+'</strong>: '+msg.message+'</div>');
//		        //Run again after 200 milliseconds
//		        window.setTimeout(updateMessage, 1000);
//			 }
//		});
//		
//	};
//	window.setTimeout(updateMessage, 1000);
});

function getUserInfo(){
	$.get('rest/messageHandler/getUserInfo', function(gsonMessage) {
		var customer = JSON.parse(gsonMessage);
		var userNameTextField = document.getElementById('userN');
		userNameTextField.value = customer.customerName;
		var userNameTextField = document.getElementById('userI');
		userNameTextField.value = customer.customerID;
	});
}

function NoobHasOurProduct(){
	 $.get('http://services.brics.dk/java4/cloud/listCustomerSales?customerID=' + 971, 
			 function(xmlSale) {
		 var xml = xmlSale;
		 xmlDoc = $.parseXML(xml),
		  
		 $xml = $(xmlDoc),
		 $shopid = $xml.find("shopID");
		 $itemid = $xml.find("itemID");
		 
		 if($shopid.Text() != "446" && $itemid.Text() != "2104"){
				alert("You dont have our product buy the product 2104 :)");
				window.location.href="/DynamicMusicShop";
		 }

		 
	});
	
};

function nextMessage(){
	 $.get('rest/messageHandler/getNextMessage', function(gsonMessage) {
		 
		 if(gsonMessage != "noUpdate"){
		 var msg = JSON.parse(gsonMessage);
	        //Update the DOM with new information
		 $('#chat').append('<div class="msg">[' +msg.dateSent+ '] <strong>'+msg.user+'</strong>: '+msg.message+'</div>');
	        //Run again after 200 milliseconds
	        //window.setTimeout(updateMessage, 1000);
		 }
	});
	
};

