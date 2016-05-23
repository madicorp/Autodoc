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

	$("form").submit(function(e){
		
		$(".loading").show();
		  e.preventDefault();
		  
		  	var formData = new FormData($(this)[0]);
			  var filename = "";
	
		  var categorie = $(this).find("select[name='categorie']").val();
		  		 
		  $.ajax({
		    url: 'connector?command=FileUpload&type=Gabarit',
		    type: 'POST',
		    data: formData,
		    async: false,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (data) {
		    	filename = data.split(",")[0];
		    	$("form")[0].reset();
		    	 $.ajax({
					    url: 'gabarit',
					    type: 'POST',
					    data: 'gabaritName='+filename+'&categorieref='+categorie,
					    success: function (data) {
					    	if(data)
					    		{
					    		location.href = "gabarit";
					    		}
					    },error: function(){
							
						}
					  });
		    },error: function(a,b,c){
		    	alert(c);
				$("form")[0].reset();
				$(".loading").hide();
				
			}
		  });
		  
		 return false;
	});
	
	 $(".rename-gabarit").click(function(){
         $("#ren-gabarit-ref").val($(this).attr("data-button"));

     });
	 
	
    $(".remove-gabarit").click(function(){
        $("#del-gabarit-ref").val($(this).attr("data-button"));
        $("#del-gabarit-name").val($(this).parents("tr").find(".name > a").html());

    });
    
    $(".rename-conf").click(function(){
  	  var gabarit = $("#ren-gabarit-ref").val();
  	  var name = $("#gabarit-name").val();
 	   var loading = $(this).next(".loading");
 	   loading.show();
 	   $.ajax({
			 
			 type:"put",
				url:"gabarit?gabaritref="+gabarit+"&gabaritname="+name,
				success: function(data){
					if (data)
						{
							location.href = "gabarit";
						
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
 	   var gabarit = $("#del-gabarit-ref").val();
 	   var loading = $(this).next(".loading");
 	   var fileName =  $("#del-gabarit-name").val();
 	   loading.show();

 	   $.ajax({
			 
			 type:"delete",
				url:"gabarit?gabaritref="+gabarit+"&fileName"+fileName,
				success: function(data){
					if (data)
						{
							location.href = "gabarit";
						
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
    

    $(".edit-gabarit").click(function(){
    	var ref = $(this).attr("data-button");
   	 $("#edit-gabarit-ref").val(ref);

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
					location.href = "gabarit";
					alert("error");
				}
		 });	
   	 
   	 
   });
   
	
    $(".edit-conf").click(function(){
    	var ref =  $("#edit-gabarit-ref").val();
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
				url:"gabarit-arg?gabaritref="+ref+"&args="+args,
				success: function(data){
					if(data)
						{
						location.href = "gabarit";
						}else
							{
							$(".fail-alert").show();
							}
					},
				error: function(){
					location.href = "gabarit";
					alert("error");
				}
		 });
    	
    	
    	
    });
	
	
});

function load(){
	 $.ajax({
		 
		 type:"get",
			url:"connector",
			data:"command=GetFiles&type=Gabarit",
			dataType: 'xml',
			success: function(data){
				if (data)
					{
					var d = $('#data').html((new XMLSerializer()).serializeToString(data));
					
					$(d).find("files").children().each(function(){
						var fileName = "";
						 fileName = $(this).attr("name");
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
								$(this).find(".name").html("<a href='connector?command=DownloadFile&type=Gabarit&FileName="+fileName+"'>"+newName+"</a>");
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
