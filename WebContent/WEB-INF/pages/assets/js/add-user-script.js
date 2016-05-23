/**
 * 
 */



$(".create-gategorie-conf").click(function(){
	var categName = $("#categorie-name").val();
	
	if(categName.replace(/\s/g, '') == "")
		{
			$(".empty-alert").show();
		}
	else{
		$(".empty-alert").hide();
		
		  $.ajax({
			    url: 'categorie?categName='+categName,
			    type: 'PUT',
			    data: '',
			    success: function (data) {
			    		if(data == "true")
			    			{
			    			$(".success-alert").fadeIn('slow',function(){
					    		$(".close").click();
					    		$(".success-alert").hide();
					    	});
			    			}
			    		else{
			    			$(".fail-alert").fadeIn('slow',function(){
					    		$(".close").click();
					    		$(".fail-alert").hide();
					    	});
			    		}
			    		
			    		$("#categorie-name").val("");
			    	
			    },
			    error: function (){
			    	
			    	$(".fail-alert").fadeIn('slow',function(){
			    		$(".close").click();
			    		$(".fail-alert").hide();
			    	});
			    	
			    	$("#categorie-name").val("");
			    	
			    }
			  });
	}
});

$(function(){
	
	$(".add-alert").fadeOut(5000);
	$("#form").submit(function(e){
		var msg ="";
		$("#form").find(".form-control").each(function(){
			if($(this).val()=="")
				{
					msg+="<li>"+$(this).attr("placeholder")+" Empty .</li>";
				}
			else if($(this).attr("placeholder")=="Email")
				{
					if(!validateEmail($(this).val()))
						{
						msg+="<li>"+$(this).attr("placeholder")+" Invalid .</li>";
						}
				}
			
		});
		
		if(msg!="")
			{
			$(".alert-warning > ul").html(msg);
			$(".alert-warning").fadeIn(1000);
			return false;
			
			}
	});
	
});

function randomString(len, charSet) {
    charSet = charSet || 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@=?!&%$';
    var randomString = '';
    for (var i = 0; i < len; i++) {
    	var randomPoz = Math.floor(Math.random() * charSet.length);
    	randomString += charSet.substring(randomPoz,randomPoz+1);
    }
    return randomString;
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 