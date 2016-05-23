/**
 * 
 */

$(document).ready(function(){

	if( $.trim($(".categorie-value").html()).length == 0)
		{
		$(".categorie-add").show();
		$(".categorie-select").hide();
		$(".categorie-action[title='Cancel']").hide();
		}
	
	$(".categorie-action").click(function(){
		var title = $(this).attr("title");
		if(title == "Cancel")
			{
				$(".categorie-add").fadeOut(1000);
				$(".categorie-select").fadeIn(2000);
			}
		else
			{
			$(".categorie-select").fadeOut(1000);
			$(".categorie-add").fadeIn(2000);
			
			}
	});
	
	 $(".rename-module").click(function(){
         $("#ren-module-ref").val($(this).attr("data-button"));

     });

      $(".remove-module").click(function(){
         $("#del-module-ref").val($(this).attr("data-button"));

     });
      
      
      $(".rename-conf").click(function(){
    	  var module = $("#ren-module-ref").val();
    	  var name = $("#module-name").val();
   	   var loading = $(this).next(".loading");
   	   loading.show();
   	   $.ajax({
 			 
 			 type:"put",
 				url:"modules?moduleref="+module+"&modulename="+name,
 				success: function(data){
 					if (data)
 						{
 							location.href = "modules";
 						
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
    	   var module = $("#del-module-ref").val();
    	   var loading = $(this).next(".loading");
    	   loading.show();
    	   $.ajax({
  			 
  			 type:"delete",
  				url:"modules?moduleref="+module,
  				success: function(data){
  					if (data)
  						{
  							location.href = "modules";
  						
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
       
       $("form").submit(function(){
    	   $(".loading").show();
       });
});