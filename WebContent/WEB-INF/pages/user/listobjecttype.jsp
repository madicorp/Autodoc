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
		<div class="col-lg-12">
			<h1 class="page-header">List Object Type</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Object Type List</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Categorie</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${objectTypes}" var="objectType">
									<tr>
										<td><a href="list-object?item=${objectType.idObjectType}">${objectType.nomObjectType}</a></td>
										<td><a
											href="list-object-type?item=${objectType.categorie.idCategorie}">${objectType.categorie.nomCategorie}</a></td>
										<td class="center">
											<button class='btn btn-success edit-ot' data-toggle="modal"
												data-target="#edit-ot" title="Edit Object Type"
												data-button='${objectType.idObjectType}'>
												<i class='fa fa-edit'></i>
											</button>
											<button class='btn btn-info rename-ot' data-toggle="modal"
												data-target="#rename-ot" title="Rename Object Type"
												data-button='${objectType.idObjectType}'>
												<i class='fa fa-minus'></i>
											</button>
											<button class='btn btn-warning create-ob' data-toggle="modal"
												data-target="#create-ob" title="Create Object"
												data-button='${objectType.idObjectType}'>
												<i class='fa fa-plus'></i>
											</button>
											<button class='btn btn-danger remove-ot' data-toggle="modal"
												data-target="#delete-ot" title="Remove Object Type"
												data-button='${objectType.idObjectType}'>
												<i class='fa fa-times'></i>
											</button>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
		<div id="data"></div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<!-- Modal -->
			<div class="modal fade" id="edit-ot" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Edit Object Type</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-success success-alert">Object Type
								Edited !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<table id="table-args">
									<thead>
										<tr>
											<th>Key</th>
											<th></th>
											<th>visible field</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								<input type="hidden" id="edit-ot-ref" value="">
							</div>
							<button type="button" class="btn btn-default add-agr">
								<i class='fa fa-plus'></i>
							</button>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary edit-conf">Save
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

			<!-- Modal -->
			<div class="modal fade" id="create-ob" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Create Object</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-info empty-alert">Name is empty !</div>
							<div class="alert alert-success success-alert">Object
								Created !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" class="form-control" id="ob-name"
									placeholder="New Object Name">
							</div>
							<div class="form-group">
								<table id="table-args-create">
									<thead>
										<tr>
											<th>Key</th>
											<th>Value</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								<input type="hidden" id="create-ob-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary create-ob-conf">Save
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
			<!-- Modal -->
			<div class="modal fade" id="rename-ot" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Rename Object Type</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-info empty-alert">Name is empty !</div>
							<div class="alert alert-success success-alert">Object Type
								Renamed !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Object Type Name</label> <input class="form-control"
									id="ot-name" placeholder="New Object Type Name"> <input
									type="hidden" id="ren-ot-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary rename-ot-conf">Save
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
			<!-- Modal -->
			<div class="modal fade" id="delete-ot" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Object Type</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>

							<div class="alert alert-success success-alert">Object Type
								Removed !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this Object
									Type ?</label> <input type="hidden" id="del-ot-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary delete-ot-conf">Delete</button>
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

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='assets/js/bootstrap.min.js'/> "></script>

<!-- Metis Menu Plugin JavaScript -->
<script
	src="<c:url value='assets/js/plugins/metisMenu/metisMenu.min.js'/>"></script>

<!-- DataTables JavaScript -->
<script
	src="<c:url value='assets/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
<script
	src="<c:url value='assets/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value='assets/js/sb-admin-2.js'/>"></script>
<script src="<c:url value='assets/js/list-object-type-script.js'/>"></script>


<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables').dataTable();
        $('#dataTables').on( 'draw.dt', function () {
            load();
        } );
    });
    </script>

</body>

</html>

