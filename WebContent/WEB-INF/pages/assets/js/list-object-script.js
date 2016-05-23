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

    function remarg(e){
    	$(e).parent("td").parent("tr").remove();
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


$(".edit-ob").click(function(){
	var ref = $(this).attr("data-button");
	 $("#edit-ob-ref").val(ref);
	 $(".loading").show();
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
                             +"<input class=\"form-control value\" placeholder=\"Value\" value=\""+value+"\">"
                             +"</td>"
                             +"</tr>");		
				}
				
				$(".loading").hide();
				},
			error: function(){
				$(".loading").hide();
				alert("error");
			}
	 });	
	 
	 
});

$(".rename-ob").click(function(){
    $("#ren-ob-ref").val($(this).attr("data-button"));

});


$(".remove-ob").click(function(){
    $("#del-ob-ref").val($(this).attr("data-button"));
    $("#del-ob-name").val($(this).parents("tr").find(".name > a").html()+".txt");

});

$(".generate-doc").click(function(){
	var ref = $(this).attr("data-button");
	$("#generate-doc-ref").val(ref);
	type="Docx";
	getTemplate(ref,type);
});

$(".optionTemplate").click(function(){
	var ref = $("#generate-doc-ref").val();
	var type =$(this).attr("title");
	getTemplate(ref,type);
});

$(".edit-ob-conf").click(function(){
	$(".loading").hide();
	var ref =  $("#edit-ob-ref").val();
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
					$(".success-alert").fadeIn('slow',function(){
			    		$(".close").click();
			    		$(".success-alert").hide();
			    	});
					}else
						{
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

$(".rename-ob-conf").click(function(){
	  var gabarit = $("#ren-ob-ref").val();
	  var name = $("#ob-name").val();
	  
	  if(name.replace(/\s/g, '') == "")
		{
			$(".empty-alert").show();
		  
		  }
	  else{
		  
		  $(".empty-alert").hide();
	   var loading = $(this).next(".loading");
	   loading.show();
	   $.ajax({
			 
			 type:"put",
				url:"gabarit?gabaritref="+gabarit+"&gabaritname="+name,
				success: function(data){
					if (data)
						{
							location.href = "";
						
						}
					else{
						$(".fail-alert").fadeIn('slow',function(){
				    		$(".close").click();
				    		$(".fail-alert").hide();
				    	});
						$(".loading").hide();
					}
					
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

$(".generate-doc-conf").click(function(){
	 var loading = $(this).next(".loading");
	var docName = $("#doc-name").val();
	var templateId = $("#Template-container").val(); //null
	var gabaritId = $("#generate-doc-ref").val();
	var text = "";
	if(docName.replace(/\s/g, '') == "")
		{
			text+="<li>Name Empty !</li>";
		}
	 if(templateId == null)
		{
		text+="<li>Template Empty !</li>"
		}
	
	if(text != "")
		{
		$(".empty-alert").find("ul").html(text);
		$(".empty-alert").show();
		}
	else{
		$(".empty-alert").hide();
		loading.show();
		   $.ajax({
				 
				 type:"POST",
					url:"generate?docName="+docName+"&gabaritId="+gabaritId+"&templateId="+templateId,
					success: function(data){
					
							$(".success-alert").fadeIn('slow',function(){
			 		    		$(".close").click();
			 		    		$(".success-alert").hide();
			 		    	});
								
							
						loading.hide();
						},
					error: function(){
						$(".fail-alert").fadeIn('slow',function(){
		 		    		$(".close").click();
		 		    		$(".fail-alert").hide();
		 		    	});
						loading.hide();
					}
			 });
		
	}
	
});

$(".delete-ob-conf").click(function(){
	   var gabarit = $("#del-ob-ref").val();
	   var loading = $(this).next(".loading");
	   var fileName =  $("#del-ob-name").val();
	   loading.show();

	   $.ajax({
			 
			 type:"delete",
				url:"gabarit?gabaritref="+gabarit+"&fileName"+fileName,
				success: function(data){
					if (data == "true")
						{
						$(".success-alert").fadeIn('slow',function(){
		 		    		$(".close").click();
		 		    		$(".success-alert").hide();
		 		    	});
							location.href = "";
						
						}
					else{
						$(".fail-alert").fadeIn('slow',function(){
		 		    		$(".close").click();
		 		    		$(".fail-alert").hide();
		 		    	});
					}
					loading.hide();
					},
				error: function(){
					$(".fail-alert").fadeIn('slow',function(){
	 		    		$(".close").click();
	 		    		$(".fail-alert").hide();
	 		    	});
					loading.hide();
				}
		 });	
	   

});

function getTemplate(ref,type){
	
	$(".loading").show();
	
	
	   $.ajax({
			 
			 type:"put",
				url:"get-template?objectref="+ref+"&type="+type,
				
				success: function(data){
					var text = "<option class=\"option-title-template\" selected disabled value=\"default\">Select By Categorie</option>";
					data = data.replace(/\[/g, '').replace(/\]/g, '').replace(/\{/g, '').replace(/\}/g, '');
					var d =  data.split(",");
					$.each(d,function(a,b){
						var t = b.split("/___/");
						var id = "";
						var name ="";
						var newName = "";
						$.each(t,function(e,f){
							if(e == 0)
								{
								id=f;
								}
							else{
								var nfn = f.split("_");
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
								name = f;
							}
							
             			
						});
						if(id != ""){
							
							text+="<option id=\""+name+"\" value=\""+id+"\">"+newName+"</option>";
						}
						
						
					});
					$("#Template-container").html(text);
					$(".loading").hide();
					},
				error: function(){
					$(".loading").hide();
					alert("server Error .!");
				}
		 });
		
}
  

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
						$('#dataTables > tbody').find("tr").each(function(){
							var cls = $(this).attr("class");
							if(cls.contains(fileName))
								{
								
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
									
									}
								else{
									newName = nfn[0] ;
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

