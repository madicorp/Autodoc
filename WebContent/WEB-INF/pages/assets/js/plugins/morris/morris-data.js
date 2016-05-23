$(function() {

	function createData(e) {
	    jsonObj = [];
	    e.find("input").each(function() {
	        var label = $(this).attr("name");
	        var value = $(this).val();

	        item = {};
	        item ["label"] = label;
	        item ["value"] = value;

	        jsonObj.push(item);
	    });

	   return jsonObj;
	}

 var categorie_graph =   Morris.Donut({
        element: 'categorie-chart',
        data: [{ }],
        resize: true
    });
	
 var document_share_graph =   Morris.Donut({
     element: 'document-share-chart',
     data: [{ }],
     resize: true
 });
	var categorie_data = createData($(".data-categorie"));
	
	categorie_graph.setData(categorie_data);
	
var document_share_data = createData($(".data-document-share"));
	
document_share_graph.setData(document_share_data);

});
