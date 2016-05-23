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

function goTo(e,id){
	var path = $(e).attr("data-toggle");
	var script = $(e).attr("data-target");
	$("#"+id).fileTree({root: path, script: script}, function(file) {
		console.log(file);
	});
}



function checkIt(e){
	if($(e).parent().hasClass( "checked" ))
		{
		$(e).parent().removeClass('checked');
		$(e).next(".fa").remove();
		}
	else{
		$(e).parent().addClass('checked');
		$(e).after("<i class=\"fa fa-check\"></i>");
		
	}
	
	var checked = false;
	
    $(".data").find("li").each(function () {
        if ($(this).hasClass( "checked" )) {
            checked = true;
        } 
    });
    
    if(checked)
    	{
    	$(".utils-special").show();
		$(".utils-default").hide();
    	}
    else
    	{
    	$(".utils-special").hide();
		$(".utils-default").show();
    	}
    
	
}

function localCloudCreateFolder(current,name)
{
	$.ajax({
	url: 'cloud-space?currentFolder='+current+"&NewFolderName="+name,
	type: 'PUT',
	async: false,
	cache: false,
	contentType: false,
	processData: false,
	success: function (data) {
		$("#nfolder-name").val("");
		$(".loading").hide();
	},error: function(a,b,c){
		$("#nfolder-name").val("");
		$(".loading").hide();
			
		}
	});
$(".close").click();

$('#local-data').fileTree({root: current, script: 'cloud-space'}, function(file) {
	console.log(file);
});
}


function localCloudDownloadFile(current,name){
	$("#local-data").find("li").each(function () {
        if ($(this).hasClass( "checked" )) {
        	var name =$(this).find(".name").text();
        	if($(this).find("a").hasClass( "files" ))
        		{
        		location.href = 'connector?command=DownloadFile&type=Cloud&currentFolder='+current+'&FileName='+name;
        		}
        } 
    });
}

function localCloudUploadFile(current,formData){
	$.ajax({
		url: 'connector?command=FileUpload&type=Cloud&currentFolder='+current,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			  $("#upload-form")[0].reset();
		},error: function(a,b,c){
			alert(c);
			$("#upload-form")[0].reset();
				
			}
		});

		$('#local-data').fileTree({root: current, script: 'cloud-space'}, function(file) {
			console.log(file);
		});
}

function localDeleteFile(current,files){
	
	$(".loading").show();
	$.ajax({
		url: 'cloud-space-del?currentFolder='+current+"&files="+files,
		type: 'DELETE',
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			$("#del-file-ref").val("");
			$(".loading").hide();
		},error: function(a,b,c){
			alert(c);
			$("#del-file-ref").val("");
			$(".loading").hide();
				
			}
		});
	$(".close").click();
	
	$('#local-data').fileTree({root: current, script: 'cloud-space'}, function(file) {
		console.log(file);
	});
	
}

function dropboxCreateFolder(current)
{
	$.ajax({
	url: 'dropbox-create-folder?currentFolder='+current+"&NewFolderName="+name,
	type: 'PUT',
	async: false,
	cache: false,
	contentType: false,
	processData: false,
	success: function (data) {
		$("#nfolder-name").val("");
		$(".loading").hide();
	},error: function(a,b,c){
		$("#nfolder-name").val("");
		$(".loading").hide();
			
		}
	});
$(".close").click();

$('#dropbox-data').fileTree({root: current, script: 'dropbox-space'}, function(file) {
	console.log(file);
});
}


function dropboxDownloadFile(current){
	$("#dropbox-data").find("li").each(function () {
        if ($(this).hasClass( "checked" )) {
        	var name =$(this).find(".name").text();
        	if($(this).find("a").hasClass( "files" ))
        		{
        		location.href = 'dropbox-download-file?currentFolder='+current+'&FileName='+name;
        		}
        } 
    });
}

function dropboxUploadFile(current,formData){
	$.ajax({
		url: 'dropbox-upload-file?currentFolder='+current,
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			  $("#upload-form")[0].reset();
		},error: function(a,b,c){
			alert(c);
			$("#upload-form")[0].reset();
				
			}
		});

	$('#dropbox-data').fileTree({root: current, script: 'dropbox-space'}, function(file) {
		console.log(file);
	});
}

function dropboxDeleteFile(current,files){
	
	$(".loading").show();
	$.ajax({
		url: 'dropbox-delete-file?currentFolder='+current+"&files="+files,
		type: 'DELETE',
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {
			$("#del-file-ref").val("");
			$(".loading").hide();
		},error: function(a,b,c){
			alert(c);
			$("#del-file-ref").val("");
			$(".loading").hide();
				
			}
		});
	$(".close").click();
	
	$('#dropbox-data').fileTree({root: current, script: 'dropbox-space'}, function(file) {
		console.log(file);
	});
	
}

$(".link-dropbox").click(function(){
	$.ajax({
		type:"post",
		url:"link-dropbox",
		success: function(data){
			$.oauthpopup({
	            path: data,
	            close: function(){
	            	location.href = "";
	            },
	            callback: function(e){

	            }
	        });
			
			},
		error: function(){
		}
	});
});

$(".link-googledrive").click(function(){
	$.ajax({
		type:"post",
		url:"link-googledrive",
		success: function(data){
			$.oauthpopup({
	            path: data,
	            close: function(){
	            	
	            },
	            callback: function(e){

	            }
	        });
			
			},
		error: function(){
		}
	});
});

$(".dropbox-config-conf").click(function(){
	var code = $("#dropbox-config-code").val();
	var email = $("#dropbox-config-email").val();
	$(".loading").show();
	$.ajax({
		type:"post",
		url:"dropbox-save-account",
		data:"code="+code+"&email="+email,
		success: function(data){
			$(".loading").hide();
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
		
			$("#dropbox-config-code").val("");
			$("#dropbox-config-email").val("");
			},
		error: function(){
			$(".fail-alert").fadeIn('slow',function(){
	    		$(".close").click();
	    		$(".fail-alert").hide();
	    	});
	    	
			$("#dropbox-config-code").val("");
			$("#dropbox-config-email").val("");
		}
	});
	
});


$(".btn-dowload").click(function(){
	var current = "";
	var type ="";
	$("#tabs").find(".tab-pane").each(function(){
		if($(this).hasClass("active"))
			{
			current = $(this).find(".current").attr("title");
			type = $(this).attr("id");
			}
	});
	switch (type) {
    case "local":
    	localCloudDownloadFile(current);
        break;
    case "dropbox":
    	dropboxDownloadFile(current);
        break;
    case "googledrive":
    	alert(3);
        break;
    case "onedrive":
    	alert(4);
        break;
}
});




$(".btn-del").click(function(){
	var files="";
	$("#tabs").find(".tab-pane").each(function(){
		if($(this).hasClass("active"))
			{
			
			$(this).find(".data").find("li").each(function () {
		        if ($(this).hasClass( "checked" )) {
		            files+= $(this).find(".name").text()+",";
		          } 
		      });
			}
	});
	$("#del-file-ref").val(files);
	
	
});


$(".del-folder-conf").click(function(){
	
	var files = $("#del-file-ref").val();
	var current = "";
	var type ="";
	$("#tabs").find(".tab-pane").each(function(){
		if($(this).hasClass("active"))
			{
			current = $(this).find(".current").attr("title");
			type = $(this).attr("id");
			}
	});
	switch (type) {
    case "local":
    	localDeleteFile(current,files);
        break;
    case "dropbox":
    	dropboxDeleteFile(current,files);
        break;
    case "googledrive":
    	alert(3);
        break;
    case "onedrive":
    	alert(4);
        break;
}

	
});

$(".create-folder-conf").click(function(){
	$(".loading").show();
	var name = $("#nfolder-name").val();
	var current = "";
	var type ="";
	$("#tabs").find(".tab-pane").each(function(){
		if($(this).hasClass("active"))
			{
			current = $(this).find(".current").attr("title");
			type = $(this).attr("id");
			}
	});
	
	
	switch (type) {
    case "local":
    	localCloudCreateFolder(current,name);
        break;
    case "dropbox":
    	dropboxCreateFolder(current,name);
        break;
    case "googledrive":
    	alert(3);
        break;
    case "onedrive":
    	alert(4);
        break;
}
	
	
});



$(".btn-upload").click(function(){
	$("#fileupload").click();
});



$("#fileupload").change(function(){
	
	var current ="";
	var type = "";
	$("#tabs").find(".tab-pane").each(function(){
		if($(this).hasClass("active"))
			{
			current = $(this).find(".current").attr("title");
			type = $(this).attr("id");
			$(this).find(".loading-file").show();
			$(this).find(".data").hide();
			}
	});
	
	var formData = new FormData($("#upload-form")[0]);		
	
	switch (type) {
    case "local":
    	localCloudUploadFile(current,formData);
        break;
    case "dropbox":
    	dropboxUploadFile(current,formData);
        break;
    case "googledrive":
    	alert(3);
        break;
    case "onedrive":
    	alert(4);
        break;
}

});



$("#search").keyup(function () {
	var empty = true;
    var filter = $(this).val();
    $(".data").find("li").each(function () {
        if ($(this).find(".name").text().search(new RegExp(filter, "i")) < 0) {
            $(this).hide();
            $(".data").hide().fadeIn('fast');
        } else {
            $(this).show();
            $(".data").hide().fadeIn('fast');
        }
    });
    
   $(".data").find("li").each(function(){
	   if(!($(this).css('display') == "none"))
		   {
	   empty = false;
		   }
   });
   if(empty)
	   {
	   $(".nothingfound").show();
	   }
   else{
	   $(".nothingfound").hide();
   }
    	
});

//function init() {
//
//    if(isMobile()) {
//document.write('<script  src="assets/js/jquery.mobile-1.4.5.js"></script>');
//    }
//}
//
//
//function isMobile() {
//
//    if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/iPhone|iPad|iPod/i) || navigator.userAgent.match(/IEMobile/i))
//            return true;
//        return false;
//}
//





