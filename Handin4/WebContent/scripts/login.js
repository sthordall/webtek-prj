$(document).ready(function() {
	$('#login_link').click( function() {
		$('#page_content').load('views/login.html');
	});
});


///**
// * Author: Jacob Mulvad
// */
//
//$(document).ready(function() {
//	//var test2 = 0;
//	
//	$('a.login-window').on('click', function() {
//	//$('body').on('click', 'a.login-window', function(){
//		//alert( "Handler for .click() called." );
//		//var test = 1;
//		//test = test + 1;
//		
//		//console.log(test);
//		//console.log(test2);
//		
//		// Getting the variable's value from a link 
//		var loginBox = $(this).attr('href');
//		
//		//alert(loginBox);
//		//test2 = test2 + 1;
//		//console.log("test2: ", test2);
//		//console.log(loginBox);
//
//		//Fade in the Popup and add close button
//		$(loginBox).fadeIn(300);
//		
//		//Set the center alignment padding + border
//		var popMargTop = ($(loginBox).height() + 24) / 2; 
//		var popMargLeft = ($(loginBox).width() + 24) / 2; 
//		
//		$(loginBox).css({ 
//			'margin-top' : -popMargTop,
//			'margin-left' : -popMargLeft
//		});
//		
//		// Add the mask to body
//		$('body').append('<div id="mask"></div>');
//		$('#mask').fadeIn(300);
//		
//		return false;
//	});
//	
//	// When clicking on the button close or the mask layer the popup closed
////	$('a.close, #mask').on('click', function() { 
////	  $('#mask, .container').fadeOut(300 , function() {
////		//$('#mask, container').hide(function(){
////		$('#mask').remove();
////	}); 
////	return false;
////	});
//	$('body').on('click', 'a.close,#mask', function(){
//		$('#mask, .container').fadeOut(300, function(){
//			$('#mask').remove();
//		});
//	});
//});