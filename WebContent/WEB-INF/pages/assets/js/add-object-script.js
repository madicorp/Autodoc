/**
 * 
 */
    
    function initChoosen(){
        var config = {
          '.chosen-select'           : {},
          '.chosen-select-deselect'  : {allow_single_deselect:true},
          '.chosen-select-no-single' : {disable_search_threshold:10},
          '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
          '.chosen-select-width'     : {width:"95%"}
        };
        for (var selector in config) {
          $(selector).chosen(config[selector]);
        }
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
    
    function getObjectType(m)
    {
    	var categorie = m;

  	  $.ajax({
  	    url: 'get-object-type?mod='+categorie,
  	    type: 'PUT',
  	    data: '',
  	    success: function (data) {
  	    	var text = "<option class=\"option-title-categorie\" selected disabled value=\"default\">Select By Categorie</option>";
  	    	var tdata = data.split("/___/");
  	    	for (var int = 0; int < tdata.length-1; int++) {
				var field = tdata[int].split("/__/");
				text+="<option value=\""+field[0]+"\">"+field[1]+"</option>";
			}
  	    	
  	    	$("#object-type-select").html(text);
  	    	$("#object-type-select").trigger("chosen:updated");
  	    }
  	  });
    }
    
    
    function getVariables (o)
    	{
    		var objetType = o;
    		var text = "";
    	  	  $.ajax({
    	    	    url: 'get-object-type?objtype='+objetType,
    	    	    type: 'POST',
    	    	    data: '',
    	    	    async: false,
    	    	    cache: false,
    	    	    contentType: false,
    	    	    processData: false,
    	    	    success: function (data) {
    	    	    	var tdata = data.split("/__/");
    	    	    	var targs = tdata[0].split("/___/");
    	    	    	$("#objetname").val(tdata[1]);
    	    	    	for (var int = 0; int < targs.length-1; int++) {
							
						
    	    	    	text += "<tr class=\"arg-couple\">"
    	                       +"<td>"
    	                        +"<input disabled=\"disabled\" class=\"form-control key\" placeholder=\"Key\" value=\""+targs[int]+"\">"
    	                        +"</td>"
    	                        +"<td>"
    	                        +"<input class=\"form-control value\" placeholder=\"Value\" >"
    	                        +"</td>"
    	                        +"</tr>";
    	    	    	}
    	  			}
    	    	  });
    	  	  
    	  	$("#table-args").find("tbody").html(text);		
    	}
    
    function objectVariables()
    {
    	var args ="";
    	$("#table-args").find(".arg-couple").each(function(){
    		var key = $(this).find(".key").val();
    		var value = $(this).find(".value").val();
    		if(key!="" && value != "")
    			{
    			args += key+"/__/"+value+"/___/";
    			}
    	});
    	
    	$("#args").val(args);
    	
    }

$(function(){
	
	$(".alert-created").fadeOut(3000);
	initChoosen();
	
	
    $('#myWizard').easyWizard({
        buttonsClass: 'btn',
        submitButtonClass: 'btn btn-info',
        before: function(wizardObj, currentStepObj, nextStepObj) {
        	var step = $(currentStepObj).attr("data-step");
        	var nextStep = $(nextStepObj).attr("data-step");
        	var categorie = $("select[name='categorie']").val();
        	var objType = $("#object-type-select").val();
        	var args = $("#args").val();
        	if(step == 1)
        		{
        			if(categorie==null)
        				{ 
        				$('#myWizard')('goToStep', 1);
        				}
        			else
        				{
        				$(".loading").show();
        				getObjectType(categorie);
        				}
        			
        		}
        	if(step == 2)
	        	{
        		if(objType==null && nextStep == 3)
				{ 
				$('#myWizard')('goToStep', 1);
				}
			else
				{
				$(".loading").show();
				getVariables (objType);
				}
	        	}
        	
        },
        after: function(wizardObj, prevStepObj, currentStepObj) {
        	$(".loading").hide();
        },
        beforeSubmit: function(wizardObj) {
        	objectVariables();
        	
        }
    });
	
});