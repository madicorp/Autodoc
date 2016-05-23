/**
 * 
 */
$(function(){

	
	 
	  $.ajax({
	    url: 'connector?command=Init',
	    type: 'GET',
	    data: '',
	    async: false,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (data) {
	    }
	  });
	  
	  
	  
	  

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
	  

	  String.prototype.splice = function( idx, rem, s ) {
		    return (this.slice(0,idx) + s + this.slice(idx + Math.abs(rem)));
		};
		
		String.prototype.contains = function(it) { return this.indexOf(it) != -1; };

	
	$(".download-document").click(function(){
       var documentref = $(this).attr("data-button");
       $.fileDownload("shared-documents-dowload?documentref="+documentref);
 	 
    });
	
});


