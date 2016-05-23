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


$(".edit-user").click(function(){
	var ref = $(this).attr("data-button");
	$("#edit-user-ref").val(ref);
	var nom = "";
	var prenom = "";
	var login = "";
	var role = "";
	$(this).parent("td").parent("tr").find("td").each(function(){
		if($(this).hasClass("fname"))
			{
				prenom = $(this).text();
			}
		if($(this).hasClass("lname"))
			{
				nom = $(this).text();
			}
		if($(this).hasClass("login"))
			{
				login = $(this).text();
			}
		if($(this).hasClass("role"))
			{
				role = $(this).text();
			}
	});
	


	$("#edit-user").find(".modal-body").find(".form-control").each(function() {
		var name = $(this).attr("name");
			if(name =="Prenom")
				{
					 $(this).val(prenom);
				}
			if(name == "Nom")
				{
					  $(this).val(nom);
				}
			if(name =="Email")
				{
					$(this).val(login);
				}
			if(name =="Role")
				{
				 $(this).find("option").each(function(){
					 if($(this).text() == role)
						  {
						 $(this).prop("selected","selected");
						  }
				 });
				}
	});
	
	$(".edit-conf").click(function(){
		$(".loading").show();
		var ref = 	$("#edit-user-ref").val();
		var nom = "";
		var prenom = "";
		var login = "";
		var password = "";
		var role = "";
		$("#edit-user").find(".modal-body").find(".form-control").each(function() {
			var name = $(this).attr("name");
				if(name =="Prenom")
					{
					prenom = $(this).val();
					}
				if(name == "Nom")
					{
					nom =  $(this).val();
					}
				if(name =="Email")
					{
					login = $(this).val();
					}
				if(name =="Password" && $(this).val() != "password")
				{
				password = $(this).val();
				}
				if(name =="Role")
					{
							role = $(this).val();
							  
					}
		});
		  $.ajax({
		    url: 'edit-user?idUser='+ref+'&nom='+nom+'&prenom='
		    								+prenom+'&login='+login+'&password='
		    								+password+'&role='+role,
			    type: 'PUT',
			    success: function (data) {
			    	if(data)
			    		{
			    		
			    		$(".loading").hide();
						 location.href = "list-user";
			    		}
			    	else{
			    		$(".fail-alert").show();
						$(".loading").hide();
			    	}
			    	
			    },
			    error: function(){
					$(".fail-alert").show();
					$(".loading").hide();
				}
			  });
		  
		
	});
});