/**
 * 
 */

    function remarg(e){
    	$(e).parent("td").parent("tr").remove();
    }
    

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
	
	$(".alert-created").fadeOut(3000);

	
	$(".add-agr").click(function(){
		$("#table-args").find("tbody").append("<tr class=\"arg-couple\">"
                +"<td>"
                 +"<input class=\"form-control key\"  placeholder=\"Variable name\">"
                 +"</td>"
                 +"<td>"
                 +"<button onclick=\"remarg(this);\" type=\"button\" class=\"btn btn-default rem-agr\"> <i class='fa fa-minus'></i></button>"
                 +"</td>"
                 +"</tr>");
	});
	
    $('#myWizard').easyWizard({
        buttonsClass: 'btn',
        submitButtonClass: 'btn btn-info',
        before: function(wizardObj, currentStepObj, nextStepObj) {
        	var step = $(currentStepObj).attr("data-step");
        	var nextStep = $(nextStepObj).attr("data-step");
        	var categorie = $("select[name='categorie']").val();
        	var otn = $("#objettypename").val();
        	if(step == 1)
        		{
        			if(categorie==null)
        				{ 
        				$('#myWizard')('goToStep', 1);
        				}
        			else
        				{
        				$(".loading").show();
        				}
        			
        		}
        	if(step == 2)
	        	{
        		if(otn=="" && nextStep == 3)
				{ 
				$('#myWizard')('goToStep', 1);
				}
			else
				{
				$(".loading").show();
			
				}
	        	}
        },
        after: function(wizardObj, prevStepObj, currentStepObj) {
        	$(".loading").hide();
        },
        beforeSubmit: function(wizardObj) {
        	var args ="";
          	$("#table-args").find(".arg-couple").each(function(){
          		var key = $(this).find(".key").val();
          		if(key!="")
          			{
          			args += key+"/___/";
          			}
          	});
          	
          	$("#args").val(args);
        	
        }
    });
	
});