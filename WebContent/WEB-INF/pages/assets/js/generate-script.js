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

  function remarg(e){
	    	$(e).parent("td").parent("tr").remove();
	    }
  
  function cloneGabarit()
  	{
	  var ref = $("#Gabarit-container").find("option:selected").attr("id");
  	var args ="";
  	$("#table-args").find(".arg-couple").each(function(){
  		var key = $(this).find(".key").val();
  		var value = $(this).find(".value").val();
  		if(key!="" && value != "")
  			{
  			args += key+"/__/"+value+"/___/";
  			}
  	});
  	
  	
	   $.ajax({
			 
			 type:"put",
				url:"gabarit-clone?gabaritref="+ref+"&args="+args,
				success: function(data){
					var tdata = data.split(",");
					if(tdata[0] == "true")
						{
							$("#clone-gabarit-id").val(tdata[1]);
						}else
							{
							alert("faild");
							}
					},
				error: function(){
					alert("error");
				}
		 });
  	}

$(function(){
	
	
		
	
	$(".alert-created").fadeOut(3000);
	
	
	function loadGabarit(e)
	{
	$("select[name='gabarit'] > option").attr('disabled','disabled').hide();
	$(".option-title-gabarit").attr("selected","selected").show();
	$("select[name='gabarit']").find("option[class='"+e+"']").each(function(){
		$(this).removeAttr('disabled').show();
	});
	}
	
	function loadTemplate(e,t)
	{
	$("select[name='template'] > option").attr('disabled','disabled').hide();
	$(".option-title-template").attr("selected","selected").show();
	$("select[name='template']").find("option[class='"+e+" "+t+"']").each(function(){
		
			$(this).removeAttr('disabled').show();
		
	});
	}
	
    $('#myWizard').easyWizard({
        buttonsClass: 'btn',
        submitButtonClass: 'btn btn-info',
        before: function(wizardObj, currentStepObj, nextStepObj) {
        	var step = $(currentStepObj).attr("data-step");
        	var nextStep = $(nextStepObj).attr("data-step");
        	var categorie = $("select[name='categorie']").val();
        	var gabarit = $("select[name='gabarit']").val();
        	var template = $("select[name='template']").val();
        	if(step == 1)
        		{
        			if(categorie==null)
        				{ 
        				$('#myWizard')('goToStep', 1);
        				}
        			else
        				{
        				$(".loading").show();
        				loadGabarit(categorie);
        				}
        			
        		}
        	if(step == 2)
	        	{
        		if(gabarit==null && nextStep == 3)
				{ 
				$('#myWizard')('goToStep', 1);
				}
			else
				{
				$(".loading").show();
				if($("#clone-gabarit").val() == "true")
					{
					cloneGabarit();
					}
				loadTemplate(categorie,"Docx");
				}
	        	}
        	if(step == 3)
        	{
        		if(template==null && nextStep == 4)
				{ 
				$('#myWizard')('goToStep', 1);
				}
        	}
        },
        after: function(wizardObj, prevStepObj, currentStepObj) {
        	$(".loading").hide();
        },
        beforeSubmit: function(wizardObj) {
        	$(".loading").show();
        	var tId= $("select[name='template']").find(":selected").attr("id");
        	var gId ="";
        	if($("#clone-gabarit").val() == "true")
        		{
            	 gId= $("#clone-gabarit-id").val();
        		}
        	else{
            	 gId= $("select[name='gabarit']").find(":selected").attr("id");

        	}
        	$("#templateId").val(tId);
        	
        	$("#gabaritId").val(gId);
        }
    });
    
    $(".optionTemplate").click(function(){
    	var categorie = $("select[name='categorie']").val();
    	var type =$(this).attr("title");
    	loadTemplate(categorie,type);
    });
    
    $("#Gabarit-container").on("change",function(){
    	$(".gp-var").hide();
    	$(".cancel-agr-btn").hide();
    	$(".cancel-agr-btn").prev("label").text("Edit Variables");
    	$(".edit-agr-btn").show();
    	$(".edit-agr-sec").fadeIn("slow");
    	$("#clone-gabarit").val("false");
    });
    
    $(".add-agr").click(function(){
    	$("#table-args").find("tbody").append("<tr class=\"arg-couple\">"
                                           +"<td>"
                                            +"<input class=\"form-control key\"  placeholder=\"Key\">"
                                            +"</td>"
                                            +"<td>"
                                            +"<input class=\"form-control value\" placeholder=\"Value\">"
                                            +"</td>"
                                            +"<td>"
                                            +"<button onclick=\"remarg(this);\" type=\"button\" class=\"btn btn-default rem-agr\"> <i class='fa fa-minus'></i></button>"
                                            +"</td>"
                                            +"</tr>");
    });
    
    $(".edit-agr-btn").click(function(){
    	
    	var ref =  $("#Gabarit-container").find("option:selected").attr("id");

   	   $.ajax({
   			 
   			 type:"get",
   				url:"gabarit-arg?gabaritref="+ref,
   				success: function(data){
   					$("#table-args").find("tbody").html("");
   					var args = data.split("/___/");
   					for (var int = 0; int < args.length-1; int++) {
   						var key = args[int].split("/__/")[0];
   						var value = args[int].split("/__/")[1];
   						$("#table-args").find("tbody").append("<tr class=\"arg-couple\">"
   	                            +"<td>"
   	                             +"<input class=\"form-control key\" placeholder=\"Key\" value=\""+key+"\">"
   	                             +"</td>"
   	                             +"<td>"
   	                             +"<input class=\"form-control value\" placeholder=\"Value\" value=\""+value+"\">"
   	                             +"</td>"
   	                             +"<td>"
   	                             +"<a href=\"#\" onclick=\"remarg(this);\" class=\"btn btn-default rem-agr\"> <i class='fa fa-minus'></i></a>"
   	                             +"</td>"
   	                             +"</tr>");		
   					}
   					
   					
   					},
   				error: function(){
   					alert("error");
   				}
   		 });	
    	
    	$(".gp-var").fadeIn("slow");
    	$(this).fadeOut("slow");
    	$(this).prev("label").text("Cancel Edit");
    	$(".cancel-agr-btn").fadeIn("slow");
    	$("#clone-gabarit").val("true");
    });
    $(".cancel-agr-btn").click(function(){
    	$(".gp-var").fadeOut("slow");
    	$(this).fadeOut("slow");
    	$(this).prev("label").text("Edit Variables");
    	$(".edit-agr-btn").fadeIn("slow");
    	$("#clone-gabarit").val("false");
    });
    
//	function load(element,suff){
//		 $.ajax({
//			 
//			 type:"get",
//				url:"connector",
//				data:"command=GetFiles&type="+element+suff,
//				dataType: 'xml',
//				success: function(data){
//					if (data)
//						{
//						var d = $('#data').html((new XMLSerializer()).serializeToString(data));
//
//						var t ="";
//						
//						$(d).find("files").children().each(function(){
//							t+="<option>"+$(this).attr("name")+"</option>";
//							
//							});
//						$('#'+element+'-container').html(t);
//						
//						
//						}
//					 $('#data').html("");
//					
//					},
//				error: function(){
//					alert("Connection Failed");
//				}
//			 
//		 });	
//
//		}
	
});