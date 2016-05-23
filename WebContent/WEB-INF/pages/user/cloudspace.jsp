<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- header -->
<jsp:include page="header.jsp" />
<!-- end header -->



<!-- Page Content -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-9">
			<h1 class="page-header">Cloud Space</h1>

		</div>
		<!-- /.col-lg-9 -->
		<div class="col-lg-3">

			<div class="page-header utils-default"
				style="border: none; position: relative;">
				<form id="upload-form" role="form">
					<input type="hidden" name="name" value="upload"> <input
						id="fileupload" type="file" name="fileName" value="upload"
						style="display: none;">
				</form>
				<button type="button" data-toggle="modal" data-target="#nfolder"
					title="New Folder" class="btn btn-default btn-circle btn-lg">
					<i class="fa fa-plus"></i>
				</button>
				<button type="button" title="upload"
					class="btn btn-default btn-circle btn-lg btn-upload">
					<i class="fa fa-cloud-upload"></i>
				</button>
			</div>
			<div class="page-header utils-special"
				style="border: none; display: none; position: relative;">
				<!--  <button type="button" title="Rename" class="btn btn-default btn-circle btn-lg">
                    <i class="fa fa-minus"></i>
                    </button> -->
				<button type="button" title="download"
					class="btn btn-default btn-circle btn-lg btn-dowload">
					<i class="fa fa-cloud-download"></i>
				</button>
				<button type="button" data-toggle="modal" data-target="#delfolder"
					title="delete" class="btn btn-default btn-circle btn-lg btn-del">
					<i class="fa fa-times"></i>
				</button>
			</div>

		</div>
		<!-- /.col-lg-3 -->
	</div>
	<!-- /.row -->

	<!-- .row -->
	<div class="row">
		<div class="col-lg-12">
			<c:if test="${empty dropboxcpt}">
			<a class="btn btn-social btn-dropbox link-dropbox"data-toggle="modal" data-target="#dropbox-conf"> 
			<i class="fa fa-dropbox"></i> Link Dropbox Account
			</a> 
			</c:if>
			<c:if test="${not empty dropboxcpt}">
			<a class="btn btn-social btn-dropbox"> 
			<i class="fa fa-dropbox"></i> Linked with ${dropboxcpt.emailDropboxCpt}
			</a> 
			</c:if>
			
			
			<c:if test="${empty googleDriveCpt}">
			<a class="btn btn-social btn-google-plus link-googledrive"> 
			<i class="social_googledrive"></i> Link GoogleDrive Account
			</a>  
			</c:if>
			<c:if test="${not empty googleDriveCpt}">
			<a class="btn btn-social btn-google-plus"> 
			<i class="social_googledrive"></i> Linked With ${googleDriveCpt.emailGoogleDriveCpt }
			</a> 
			</c:if>
			<a class="btn btn-social btn-bitbucket link-onedrive"> 
			<i class="fa fa-cloud"></i> Link OneDrive Account
			</a>
		</div>
	</div>
	<!-- /.row -->


	<!-- .row -->
	<div class="row">
		<div id="tabs" class="col-lg-12">

			<ul class="nav nav-tabs">
				<li class="active"><a href="#local" data-toggle="tab">Local</a></li>
				<li class=""><a href="#dropbox" data-toggle="tab">Dropbox</a></li>
				<li class=""><a href="#googledrive" data-toggle="tab">Google
						Drive</a></li>
				<li class=""><a href="#onedrive" data-toggle="tab">One
						Drive</a></li>
			</ul>


			<!-- .tab-content -->
			<div class="tab-content">
				<div class="input-group custom-search-form">
					<input id="search" type="text" class="form-control"
						placeholder="Search..."> <span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<!-- .tab-pane fade -->
				<div class="tab-pane active" id="local">
					<!-- .row -->
					<div class="row">
						<div class="col-lg-12">

							<!--.filetree filemanager -->
							<div class="filetree filemanager">
								<div class="breadcrumbs"></div>
								<ul class="data animated" style="" id="local-data">
								</ul>
								<div class="loading-file">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
								</div>
								<div class="nothingfound">
									<div class="nofiles"></div>
									<span>No files found.</span>
								</div>
							</div>
							<!-- /.filetree filemanager -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- ./tab-pane fade -->

				<!-- .tab-pane fade -->
				<div class="tab-pane fade" id="dropbox">

					<!-- .row -->
					<div class="row">

						<!-- .col-lg-12 -->
						<div class="col-lg-12">
							<!--.filetree filemanager -->
							<div class="filetree filemanager">

								<div class="breadcrumbs"></div>
								<ul class="data animated" style="" id="dropbox-data">
								</ul>
								<div class="loading-file">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
								</div>
								<div class="nothingfound">
									<div class="nofiles"></div>
									<span>No files found.</span>
								</div>
							</div>
							<!-- /.filetree filemanager -->
						</div>
						<!-- ./col-lg-12 -->

					</div>
					<!-- ./row -->


				</div>
				<!-- ./tab-pane fade -->

				<!-- .tab-pane fade -->
				<div class="tab-pane fade" id="googledrive">

					<!-- .row -->
					<div class="row">

						<!-- .col-lg-12 -->
						<div class="col-lg-12">
							<!--.filetree filemanager -->
							<div class="filetree filemanager">

								<div class="breadcrumbs"></div>
								<ul class="data animated" style="" id="googledrive-data">
								</ul>
								<div class="loading-file">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
								</div>
								<div class="nothingfound">
									<div class="nofiles"></div>
									<span>No files found.</span>
								</div>
							</div>
							<!-- /.filetree filemanager -->
						</div>
						<!-- ./col-lg-12 -->

					</div>
					<!-- ./row -->


				</div>
				<!-- ./tab-pane fade -->

				<!-- .tab-pane fade -->
				<div class="tab-pane fade" id="onedrive">

					<!-- .row -->
					<div class="row">

						<!-- .col-lg-12 -->
						<div class="col-lg-12">
							<h1>Hello from Onedrive !</h1>
						</div>
						<!-- ./col-lg-12 -->

					</div>
					<!-- ./row -->


				</div>
				<!-- ./tab-pane fade -->
			</div>
			<!--./tab-content  -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->


	<!-- .row -->
	<div class="row">
		<div class="col-lg-6">
			<!-- Modal -->
			<div class="modal fade" id="delfolder" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Files</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this files ?</label>
								<input type="hidden" id="del-file-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary del-folder-conf">Delete</button>
							<div class="loading">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- Modal -->
			<div class="modal fade" id="nfolder" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">New Folder</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Folder Name</label> <input class="form-control"
									id="nfolder-name" placeholder="New Folder Name">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary create-folder-conf">Create</button>
							<div class="loading">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- Modal -->
			<div class="modal fade" id="dropbox-conf" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Dropbox
								Configuration</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Dropbox's Email</label> <input class="form-control"
									id="dropbox-config-email"
									placeholder="Enter your Dropbox's email">
							</div>
							<div class="form-group">
								<label>Dropbox's Code</label> <input class="form-control"
									id="dropbox-config-code" placeholder="Enter The code">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary dropbox-config-conf">Send</button>
							<div class="loading">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<!-- Modal -->
			<div class="modal fade" id="new-categorie" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">New Categorie</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-info empty-alert">Name is empty !</div>
							<div class="alert alert-success success-alert">Categorie
								Created !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input class="form-control" id="categorie-name"
									placeholder="New Categorie Name">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button"
								class="btn btn-primary create-gategorie-conf">Save
								changes</button>
							<div class="loading">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
							</div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery Version 1.11.0 -->
<script src="<c:url value='assets/js/jquery-1.11.0.js '/>"></script>
<script src="<c:url value='assets/js/jquery.oauthpopup.js'/>  "></script>

<script src="<c:url value='assets/js/jquery.mobile-1.4.5.js'/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='assets/js/bootstrap.min.js'/> "></script>

<!-- Metis Menu Plugin JavaScript -->
<script
	src="<c:url value='assets/js/plugins/metisMenu/metisMenu.min.js'/>"></script>


<!-- Custom Theme JavaScript -->
<script src="<c:url value='assets/js/sb-admin-2.js'/>"></script>
<script src="<c:url value='assets/js/cloud-script.js'/>"></script>
<script src="<c:url value='assets/js/jqueryFileTree.js'/> "></script>


<script type="text/javascript">
   
$(document).ready (function() {
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
	  
	$('#local-data').fileTree({root: '/', script: 'cloud-space'}, function(file) {
		console.log(file);
	});

	  $('#dropbox-data').fileTree({root: '/', script: 'dropbox-space'}, function(file) {
			console.log(file);
		});

	  $('#googledrive-data').fileTree({root: '/', script: 'googledrive-space'}, function(file) {
			console.log(file);
		});

	// event listeners
	$('.filetree')
		.on('filetreeexpand', function (e, data)	{ console.log(data); })
		.on('filetreeexpanded', function (e, data)	{ console.log(data); })
		.on('filetreecollapsed', function (e, data)	{ console.log(data); })
		.on('filetreecollapse', function (e, data)	{ console.log(data); })
		.on('filetreechecked', function (e, data)	{ console.log(data); })
		.on('filetreeunchecked', function (e, data)	{ console.log(data); })
		.on('filetreeclicked', function(e, data)	{ console.log(data); });

	
});
</script>
