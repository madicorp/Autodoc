/**
 * 
 */


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
	  
	  
	  
	  
    function remarg(e){
    	$(e).parent("td").parent("tr").remove();
    }
    
    function checkboxAction (e) {
    	
    	if($(e).prev("input").is(":checked"))
    		{
    		$(e).prev("input").prop("checked",false);
    		
    		}
    	else
    		{
    		$(e).prev("input").prop("checked",true);
    		}
    }
    
    

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

	  

$(".create-gategorie-conf").click(function(){
	var categName = $("#categorie-name").val();
	
	if(categName.replace(/\s/g, '') == "")
		{
			$(".empty-alert").show();
		}
	else{
		$(".empty-alert").hide();
		$(".loading").show();
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
			    		$(".loading").hide();
			    	
			    },
			    error: function (){
			    	
			    	$(".fail-alert").fadeIn('slow',function(){
			    		$(".close").click();
			    		$(".fail-alert").hide();
			    	});
			    	
			    	$("#categorie-name").val("");
			    	
			    	$(".loading").hide();
			    	
			    }
			  });
	}
});


$(".edit-ot").click(function(){
	var ref = $(this).attr("data-button");
  	 $("#edit-ot-ref").val(ref);
 	$("#table-args").find("tbody").html("");
 	
 	var text = "";
 	$(".loading").show();
	  $.ajax({
  	    url: 'get-object-type?objtype='+ref,
  	    type: 'POST',
  	    data: '',
  	    success: function (data) {
  	    	var tdata = data.split("/__/");
  	    	var args = tdata[0].split("/___/");
  	    	var fields = tdata[1].split("/___/")
  	    	for (var int = 0; int < args.length-1; int++) {
  	    		var visible = false;
  	    	text += "<tr class=\"arg-couple\">"
                +"<td>"
                +"<input class=\"form-control key\" placeholder=\"Key\" value=\""+args[int]+"\">"
                +"</td>"
                +"<td>"
                +"<a href=\"#\" onclick=\"remarg(this);\" class=\"btn btn-default rem-agr\"> <i class='fa fa-minus'></i></a>"
                +"</td>"
                +"<td>";
  	    	for (var i = 0; i < fields.length-1; i++) {
  	    		if(fields[i] == args[int])
  	    			{
  	    			visible = true;
  	    			}
  	    	}
  	    	
  	    	if(visible)
  	    		{
  	    		text += "<input type='checkbox' class=\"visible-field\" checked name='visible-field' value='"+args[int]+"'/><a onclick=\"checkboxAction(this);\"><i class='fa fa-eye'></i></a>"
                
  	    		}
  	    	else{
  	    		text += "<input type='checkbox' class=	\"visible-field\" name='visible-field' value='"+args[int]+"'/><a onclick=\"checkboxAction(this);\"><i class='fa fa-eye'></i></a>"
                
  	    	}
  	    	
  	    	text +="</td>"
  	    		+"</tr>";
  	    	
  	    	}
  	    	$("#table-args").find("tbody").append(text);
  	    	$(".loading").hide();
			}
  	  });
 	 	
});


$(".rename-ot").click(function(){
	
	var ref = $(this).attr("data-button");
	
	$("#ren-ot-ref").val(ref);
	
});


$(".create-ob").click(function(){
	$(".loading").show();
	var ref = $(this).attr("data-button");
	$("#create-ob-ref").val(ref);
	
	 	$("#table-args-create").find("tbody").html("");
		var text = "";
	  	  $.ajax({
	    	    url: 'get-object-type?objtype='+ref,
	    	    type: 'POST',
	    	    data: '',
	    	    success: function (data) {
	    	    	var tdata = data.split("/__/");
	    	    	var args = tdata[0].split("/___/")
	    	    	$("#ob-name").val(tdata[1]);
	    	    	for (var int = 0; int < args.length-1; int++) {
	    	    	text += "<tr class=\"arg-couple\">"
	                       +"<td>"
	                        +"<input disabled=\"disabled\" class=\"form-control key\" placeholder=\"Key\" value=\""+args[int]+"\">"
	                        +"</td>"
	                        +"<td>"
	                        +"<input class=\"form-control value\" placeholder=\"Value\" >"
	                        +"</td>"
	                        +"</tr>";
	    	    	}
	    	    	$("#table-args-create").find("tbody").append(text);
	    	    	$(".loading").hide();
	  			}
	    	  });
});

$(".remove-ot").click(function(){
	
	var ref = $(this).attr("data-button");
	$("#del-ot-ref").val(ref);
	
});



$(".edit-conf").click(function(){
	$(".loading").show();
	var ref = $("#edit-ot-ref").val();
	var args ="";
	var vivible_fields = "";
  	$("#table-args").find(".arg-couple").each(function(){
  		var key = $(this).find(".key").val();
  		var field = $(this).find(".visible-field");
  		if(key!="")
  			{
  			args += key+"/___/";
  			}
  		if(field.is(":checked"))
  		{
  			vivible_fields += field.val()+"/___/";
  		}
  	});
  	
  	 $.ajax({
 	    url: 'edit-object-type?ref='+ref+'&args='+args+'&vivible_fields='+vivible_fields,
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
 	    	$(".loading").hide();
			},
			error: function(){
				$(".fail-alert").fadeIn('slow',function(){
		    		$(".close").click();
		    		$(".fail-alert").hide();
		    	});
				$(".loading").hide();
			}
 	  });
	
});

$(".rename-ot-conf").click(function(){
	var ref = $("#ren-ot-ref").val();
	var name = $("#ot-name").val();
	
	if(name.replace(/\s/g, '') == "")
	{
		$(".empty-alert").show();
	}
	else{
		$(".loading").show();
		$(".empty-alert").hide();
		
	  	 $.ajax({
	  	    url: 'rename-object-type?ref='+ref+'&name='+name,
	  	    type: 'PUT',
	  	    data: '',
	  	    success: function (data) {
	  	    	
	  	    	if(data == "true")
	  	    		{
	  	    		$(".success-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".success-alert").hide();
	 		    	});
	  	    		
	  	    		location.href="";
	  	    		
	  	    		}
	  	    	else{
	  	    		$(".fail-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".fail-alert").hide();
	 		    	});
	  	    	}
	  	    	$(".loading").hide();
	 			},
	 			error: function(){
	 				$(".fail-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".fail-alert").hide();
	 		    	});
	 				$(".loading").hide();
	 			}
	  	  });
		
		
	}
	
	
});


$(".create-ob-conf").click(function(){
	
	var ref = $("#create-ob-ref").val();
	var ob_name = $("#ob-name").val();
	if(ob_name.replace(/\s/g, '') == "")
		{
		$(".empty-alert").show();
		}
	else
		{
		$(".loading").show();
		$(".empty-alert").hide();
		
	var args ="";
	$("#table-args-create").find(".arg-couple").each(function(){
		var key = $(this).find(".key").val();
		var value = $(this).find(".value").val();
		if(key!="" && value != "")
			{
			args += key+"/__/"+value+"/___/";
			}
	});
	
	
	$.ajax({
 	    url: 'add-object?objecttype='+ref+'&objetname='+ob_name+'&args='+args,
 	    type: 'POST',
 	    data: '',
 	    success: function (data) {
 	    	
 	    
 	    		$(".success-alert").fadeIn('slow',function(){
		    		$(".close").click();
		    		$(".success-alert").hide();
		    	});
 	    	
 	    	$(".loading").hide();
			},
			error: function(){
				$(".fail-alert").fadeIn('slow',function(){
		    		$(".close").click();
		    		$(".fail-alert").hide();
		    	});
				$(".loading").hide();
			}
 	  });
		}
	
});

$(".delete-ot-conf").click(function(){
	var ref = $("#del-ot-ref").val();
	 $.ajax({
	  	    url: 'delete-object-type?ref='+ref,
	  	    type: 'DELETE',
	  	    data: '',
	  	    success: function (data) {
	  	    	
	  	    	if(data == "true")
	  	    		{
	  	    		$(".success-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".success-alert").hide();
	 		    	});
	  	    		
	  	    		location.href="";
	  	    		
	  	    		}
	  	    	else{
	  	    		$(".fail-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".fail-alert").hide();
	 		    	});
	  	    	}
	  	    	$(".loading").hide();
	 			},
	 			error: function(){
	 				$(".fail-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".fail-alert").hide();
	 		    	});
	 				$(".loading").hide();
	 			}
	  	  });
	
});






