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
	  
	  load();
	  
	  

	  String.prototype.splice = function( idx, rem, s ) {
		    return (this.slice(0,idx) + s + this.slice(idx + Math.abs(rem)));
		};
		
		String.prototype.contains = function(it) { return this.indexOf(it) != -1; };

		
		$(".view-agr").click(function(){
			
	    	var ref = $(this).attr("data-button");
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
	   	                             +"<input disabled=\"disabled\" class=\"form-control key\" placeholder=\"Key\" value=\""+key+"\">"
	   	                             +"</td>"
	   	                             +"<td>"
	   	                             +"<input disabled=\"disabled\" class=\"form-control value\" placeholder=\"Value\" value=\""+value+"\">"
	   	                             +"</td>"
	   	                             +"</tr>");		
	   					}
	   					
	   					
	   					},
	   				error: function(){
	   					location.href = "documents";
	   					alert("error");
	   				}
	   		 });	
			
		});
		
	
	$(".share-document").click(function(){
		var documentref = $(this).attr("data-button");
        $("#share-document-ref").val(documentref);
        
        
   	 
  	  $.ajax({
  	    url: 'user-share?documentref='+documentref,
  	    type: 'PUT',
  	    data: '',
  	    success: function (data) {
  	    	
  	    		$("input[name='users-ref']").bootstrapSwitch('state',false,false);
  	    		var tdata = data.split(",");
  	    		for (var int = 0; int < tdata.length-1; int++) {
  	    			$("#dataTables-user").children("tbody").find(".users-ref").each(function(){
  	    				if($(this).hasClass(tdata[int]))
  	    					{
  	    					$(this).bootstrapSwitch('state',true,true);
  	    					}
  	    			});
				}
  	    			
  	    		
  	    }
  	  });
        

    });
	
	 $(".rename-document").click(function(){
	        $("#ren-document-ref").val($(this).attr("data-button"));

	    });
	
    $(".remove-document").click(function(){
        $("#del-document-ref").val($(this).attr("data-button"));
        $("#del-document-name").val($(this).parents("tr").find(".name > a").html());

    });
    
    $(".share-conf").click(function(){
    	  var document = $("#share-document-ref").val();
    	  var users = "";
    	  $("#dataTables-user").children("tbody").find(".users-ref").each(function(){
    		  if($(this).bootstrapSwitch('state'))
    			  {
    			  users +=$(this).val()+",";
    			  }
    		 
    	  });
   	   var loading = $(this).next(".loading");
   	   loading.show();
   	   alert(users);
   	   
   	   $.ajax({
  			 
  			 type:"post",
  				url:"documents?documentref="+document+"&users="+users,
  				success: function(data){
  					if (data)
  						{
  							location.href = "documents";
  						
  						}
  					else{
  						$(".fail-alert").show();
  					}
  					
  					},
  				error: function(){
  					$(".fail-alert").show();
  					loading.hide();
  				}
  		 });	

      });

    
    $(".rename-conf").click(function(){
  	  var document = $("#ren-document-ref").val();
  	  var name = $("#document-name").val();
 	   var loading = $(this).next(".loading");
 	   loading.show();
 	   $.ajax({
			 
			 type:"put",
				url:"documents?documentref="+document+"&documentname="+name,
				success: function(data){
					if (data)
						{
							location.href = "documents";
						
						}
					else{
						$(".fail-alert").show();
					}
					
					},
				error: function(){
					$(".fail-alert").show();
					loading.hide();
				}
		 });	

    });
	
    $(".delete-conf").click(function(){
 	   var document = $("#del-document-ref").val();
 	   var loading = $(this).next(".loading");
 	   loading.show();

 	   $.ajax({
			 
			 type:"delete",
				url:"documents?documentref="+document,
				success: function(data){
					if (data)
						{
							location.href = "documents";
						
						}
					else{
						$(".fail-alert").show();
					}
					
					},
				error: function(){
					$(".fail-alert").show();
					loading.hide();
				}
		 });	
 	   

   });
	
	
});

function load(){
	 $.ajax({
		 
		 type:"get",
			url:"connector",
			data:"command=GetFiles&type=Doc",
			dataType: 'xml',
			success: function(data){
				if (data)
					{
					var d = $('#data').html((new XMLSerializer()).serializeToString(data));
					
					$(d).find("files").children().each(function(){
						var fileName = $(this).attr("name");
						var size = $(this).attr("size");
						
						$('#dataTables > tbody').find("tr").each(function(){
							var cls = $(this).attr("class");
							if(cls.contains(fileName))
								{
								$(this).find(".size").html(size+" ko");
								
								var nfn = fileName.split("_");
								var newName = "";
								if(nfn.length >2)
									{
										for (var int = 0; int < nfn.length-1; int++) {
											if(int < nfn.length-2)
											newName +=nfn[int]+"_";
											else
												newName +=nfn[int];
										}
										newName +="."+nfn[nfn.length-1].split(".")[1];
									}
								else{
									newName = nfn[0] +"."+nfn[1].split(".")[1];
								}
								
								$(this).find(".name").html("<a href='connector?command=DownloadFile&type=Doc&FileName="+fileName+"'>"+newName+"</a>");
								return;
								}
							
						});
						});
					
					}
				 $('#data').html("");
				
				},
			error: function(){
				alert("Connection Failed");
			}
		 
	 });	

	}
