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
	  
	  load();
	  
	  

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

	$("form").submit(function(e){
		
		  event.preventDefault();
		  
		  $(".loading").show();
		  var formData = new FormData($(this)[0]);
		  
		  var filename = "";
		  var t = $(this).find("input[name='fileName']").val().split(".");
		  var extention = t[t.length-1];
			if(extention != "xlsx" && extention != "xlsm" && extention != "xltx" && extention != "xltm")
				{
					$(".empty-alert").show();
					 $(".loading").hide();
				}
			else{
				$(".empty-alert").hide();
			
		  var categorie = $(this).find("select[name='categorie']").val();
		  
		 
		  $.ajax({
		    url: 'connector?command=FileUpload&type=TemplateXls',
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
					    url: 'template-xls',
					    type: 'POST',
					    data: 'templateName='+filename+'&categorieref='+categorie,
					    success: function (data) {
					    	if(data)
					    		{
					    		location.href = "template-xls";
					    		}
					    },error: function(){
							
						}
					  });
		    },error: function(){
				$("form")[0].reset();
				$(".loading").hide();
			}
		  });
		  
		 
			}
		 return false;
	});
	

	 $(".rename-template").click(function(){
        $("#ren-template-ref").val($(this).attr("data-button"));

    });
	
    $(".remove-template").click(function(){
        $("#del-template-ref").val($(this).attr("data-button"));
        $("#del-template-name").val($(this).parents("tr").find(".name > a").html());

    });
    
    $(".rename-conf").click(function(){
    	  var template = $("#ren-template-ref").val();
    	  var name = $("#template-name").val();
   	   var loading = $(this).next(".loading");
   	   loading.show();
   	   $.ajax({
  			 
  			 type:"put",
  				url:"template?type=Xls&templateref="+template+"&templatename="+name,
  				success: function(data){
  					if (data)
  						{
  							location.href = "template-xls";
  						
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
 	   var template = $("#del-template-ref").val();
 	   var loading = $(this).next(".loading");
 	   loading.show();

 	   $.ajax({
			 
			 type:"delete",
				url:"template?templateref="+template+"&type=Xls",
				success: function(data){
					if (data)
						{
							location.href = "template-xls";
						
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
			data:"command=GetFiles&type=TemplateXls",
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
								$(this).find(".name").html("<a href='connector?command=DownloadFile&type=TemplateXls&FileName="+fileName+"'>"+newName+"</a>");
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
