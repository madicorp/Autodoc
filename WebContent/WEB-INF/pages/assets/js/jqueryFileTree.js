/**
 * jQuery File Tree Plugin
 *
 * @author - Cory S.N. LaViska - A Beautiful Site (http://abeautifulsite.net/) - 24 March 2008
 * @author - Dave Rogers - https://github.com/daverogers/jQueryFileTree
 *
 *
 * Usage: $('.fileTreeDemo').fileTree( options, callback )
 *
 * Options:  root           - root folder to display; default = /
 *           script         - location of the serverside AJAX file to use; default = jqueryFileTree.php
 *           folderEvent    - event to trigger expand/collapse; default = click
 *           expandSpeed    - default = 500 (ms); use -1 for no animation
 *           collapseSpeed  - default = 500 (ms); use -1 for no animation
 *           expandEasing   - easing function to use on expand (optional)
 *           collapseEasing - easing function to use on collapse (optional)
 *           multiFolder    - whether or not to limit the browser to one subfolder at a time
 *           loadMessage    - Message to display while initial tree loads (can be HTML)
 *           multiSelect    - append checkbox to each line item to select more than one
 *
 *
 * TERMS OF USE
 *
 * This plugin is dual-licensed under the GNU General Public License and the MIT License and
 * is copyright 2008 A Beautiful Site, LLC.
 */

if(jQuery) (function($){

	$.extend($.fn, {
		fileTree: function(options, file) {
			// Default options
			if( options.root			=== undefined ) options.root			= '/';
			if( options.script			=== undefined ) options.script			= '/files/filetree';
			if( options.folderEvent		=== undefined ) options.folderEvent		= 'dblclick';
			if( options.expandSpeed		=== undefined ) options.expandSpeed		= 500;
			if( options.collapseSpeed	=== undefined ) options.collapseSpeed	= 500;
			if( options.expandEasing	=== undefined ) options.expandEasing	= null;
			if( options.collapseEasing	=== undefined ) options.collapseEasing	= null;
			if( options.multiFolder		=== undefined ) options.multiFolder		= true;
			if( options.loadMessage		=== undefined ) options.loadMessage		= 'Loading...';
			if( options.multiSelect		=== undefined ) options.multiSelect		= false;

			var dataContainer = this;
			var serverSript = options.script;
			$(this).each( function() {

				function showTree(element, dir) {
					$(".loading-file").show();
					$(dataContainer).hide();
					$(element).addClass('wait');
					$(".jqueryFileTree.start").remove();
					if($(element).html() != "")
					{
						dir =$(element).find("a").attr("rel");
					}
					
					$.post(options.script,
					{
						dir: dir,
						multiSelect: options.multiSelect
					}).done(function(data){
						$(element).find('.start').html('');
						$(element).removeClass('wait');
						
						var breadcrumbs ="";
						
						var t = dir.split("/");
						var breadcrumburl ="";
						
						if(t.length <3){
							
							breadcrumbs+="<i class=\"fa fa-home fa-fw\"></i><span title=\"/\" class=\" current folderName\">Home</span>";
							breadcrumburl+="/";
							
						}
						else{
						
						for (var int = 0; int < t.length-1; int++) {
							t[int] = t[int].replace(/%20/g, " ");
							if(int == 0)
								{
								if (dir.substring(0, 6) == "/") {
									breadcrumburl+="/";
								}
								else{
									breadcrumburl+=t[int]+"/";
								}
								breadcrumbs+="<a onclick=\"goTo(this,'"+$(dataContainer).attr("id")+"');\" data-toggle=\"om\" data-target=\""+serverSript+"\" href=\"#\"rel=\"/\"><i class=\"fa fa-home fa-fw\"></i><span class=\"folderName\">Home</span></a>"
											+"<span class=\"arrow\">→</span>";
								}
							else if(int > 0 && int < t.length-2 )
								{
								
							breadcrumburl+=t[int]+"/";
							breadcrumbs+="<a onclick=\"goTo(this,'"+$(dataContainer).attr("id")+"');\" data-toggle=\""+breadcrumburl+"\" data-target=\""+serverSript+"\" href=\"#;\" rel=\""+breadcrumburl+"\"><span class=\"folderName\">"+t[int]+"</span></a>"
							+"<span class=\"arrow\">→</span>";
								}
							else if( int == t.length-2)
								{
								breadcrumburl+=t[int]+"/";
								breadcrumbs+="<span title='"+breadcrumburl+"\' class=\"current folderName\">"+t[int]+"</span>";
								
								}
						}
						}
						$(dataContainer).prev(".breadcrumbs").html(breadcrumbs);
						$(dataContainer).html(data);
						var empty = true;
						 $(dataContainer).find("li").each(function(){
							   if(!($(this).css('display') == "none"))
								   {
							   empty = false;
								   }
						   });
						   if(empty)
							   {
							   $(dataContainer).parent().find(".nothingfound").show();
							   }
						   else{
							   $(dataContainer).parent().find(".nothingfound").hide();
						   }
						
						
						   $(dataContainer).find("li").hover(function(){
								$(this).animate({ bottom: 5 });
							},function(){
								$(this).animate({ bottom: 0 });
								
							});
						   
						   $(dataContainer).find("li").on("swipe",function(event){
							   if($(this).hasClass("directory"))
								   {
								   
							  var path =$(this).find("a").attr("rel");
							   $(dataContainer).fileTree({root: path, script: serverSript}, function(file) {
									console.log(file);
								});
								   }
							});
						   	
						   
//						   $(".data").find("li").swipe( {
//							 //Generic swipe handler for all directions
//							 swipe:function(event, direction, distance, duration, fingerCount) {
//							 alert("You swiped " + direction );
//							 },
//							 //Default is 75px, set to 0 for demo so any distance triggers swipe
//							 threshold:0,
//							 });
						   
						   var checked = false;
							
						    $(dataContainer).find("li").each(function () {
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
		
						    $(".loading-file").hide();
						$(dataContainer).fadeIn('fast');
						
						bindTree($(dataContainer));

						$(this).parent().removeClass('collapsed').addClass('expanded');

						_trigger($(this), 'filetreeexpanded', data);
					})
					.fail(function(){
						$(element).find('.start').html('');
						$(element).removeClass('wait').append("<li>Unable to get file tree information</li>");
					});
				}

				function bindTree(element) {
					$(element).find('LI A').on(options.folderEvent, function() {
						// set up data object to send back via trigger
						var data = {};
						data.li = $(this).closest('li');
						data.type = ( data.li.hasClass('directory') ? 'directory' : 'file' );
						data.value	= $(this).text();
						data.rel	= $(this).prop('rel');

						if( $(this).parent().hasClass('directory') ) {
							if( $(this).parent().hasClass('collapsed') ) {
								// Expand
								_trigger($(this), 'filetreeexpand', data);

								if( !options.multiFolder ) {
									$(this).parent().parent().find('UL').slideUp({ duration: options.collapseSpeed, easing: options.collapseEasing });
									$(this).parent().parent().find('LI.directory').removeClass('expanded').addClass('collapsed');
								}
								$(this).parent().find('UL').remove(); // cleanup
								showTree( $(this).parent(), escape($(this).attr('rel').match( /.*\// )) );
							} else {
								// Collapse
								_trigger($(this), 'filetreecollapse', data);

								$(this).parent().find('UL').slideUp({ duration: options.collapseSpeed, easing: options.collapseEasing });
								$(this).parent().removeClass('expanded').addClass('collapsed');

								_trigger($(this), 'filetreecollapsed', data);
							}
						} else {
							// this is a file click, return file information
							file($(this).attr('rel'));

							_trigger($(this), 'filetreeclicked', data);
						}
						return false;
					});
					// Prevent A from triggering the # on non-click events
					if( options.folderEvent.toLowerCase != 'click' ) $(element).find('LI A').on('click', function() { return false; });
				}

				// Loading message
				$(this).html('<ul class="jqueryFileTree start"><li class="wait">' + options.loadMessage + '<li></ul>');

				// Get the initial file list
				showTree( $(this), escape(options.root) );

				// wrapper to append trigger type to data
				function _trigger(element, eventType, data) {
					data.trigger = eventType;
					element.trigger(eventType, data);
				}
				

				// checkbox event (multiSelect)
				$(this).on('change', 'input:' , function(){
					var data = {};
					data.li		= $(this).closest('li');
					data.type	= ( data.li.hasClass('directory') ? 'directory' : 'file' );
					data.value	= data.li.children('a').text();
					data.rel	= data.li.children('a').prop('rel');

					// propagate check status to (visible) child checkboxes
					data.li.find('input:checkbox').prop( 'checked', $(this).prop('checked') );

					// set triggers
					if( $(this).prop('checked') )
						_trigger($(this), 'filetreechecked', data);
					else
						_trigger($(this), 'filetreeunchecked', data);
				});
			});
		}
	});

})(jQuery);
