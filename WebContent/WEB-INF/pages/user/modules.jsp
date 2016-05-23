<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="header.jsp" />
<!-- end header -->


<!-- Page Content -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Module</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">Module List</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables">
							<thead>
								<tr>
									<th>Date</th>
									<th>Nom</th>
									<th>Categorie</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${modules}" var="module">
									<tr>
										<td>${module.creationDateModule}</td>
										<td>${module.nomModule}</td>
										<td>${module.categorie.nomCategorie}</td>
										<td class="center">
											<button class='btn btn-info rename-module'
												data-toggle="modal" data-target="#rename-module"
												title="Rename Module" data-button='${module.idModule}'>
												<i class='fa fa-edit'></i>
											</button>
											<button class='btn btn-danger remove-module'
												data-toggle="modal" data-target="#delete-module"
												title="Remove Module" data-button='${module.idModule}'>
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
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">New Module</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form" method="post">
								<div class="row categorie-select">
									<div class="col-lg-8">
										<div class="form-group">
											<label>Categorie</label> <select name="categ"
												class="form-control categorie-value">
												<c:forEach items="${categories}" var="categorie">
													<option value="${categorie.idCategorie}">${categorie.nomCategorie}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label> </label>
											<button type="button" title="Add New Categorie"
												class='form-control btn btn-info categorie-action'>
												<i class='fa fa-plus'></i> New Categorie
											</button>
										</div>
									</div>
								</div>
								<div class="row categorie-add" style="display: none;">
									<div class="col-lg-8">
										<div class="form-group">
											<label>Categorie Name</label> <input name="categorieNew"
												class="form-control" placeholder="categorie Name">
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label> </label>
											<button type="button" title="Cancel"
												class='form-control btn btn-info categorie-action'>
												<i class='fa fa-minus'></i> Cancel
											</button>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>Module Name</label> <input name="modu"
										class="form-control" placeholder="Module Name">
								</div>

								<button type="submit" class="btn btn-default"
									style="float: right;">Submit Button</button>

								<button type="reset" class="btn btn-default">Reset
									Button</button>
								<div class="loading">
									<img alt="" src="<c:url value='assets/img/loading.gif'/>">
								</div>
							</form>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel-default -->
		</div>
		<!-- /.col-lg-6 -->
		<div id="data"></div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<!-- Modal -->
			<div class="modal fade" id="rename-module" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Rename Module</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Module Name</label> <input class="form-control"
									id="module-name" placeholder="New Module Name"> <input
									type="hidden" id="ren-module-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary rename-conf">Save
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
			<div class="modal fade" id="delete-module" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Module</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this module ?</label>
								<input type="hidden" id="del-module-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary delete-conf">Delete</button>
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
<script src="<c:url value='assets/js/module-script.js'/>"></script>


<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables').dataTable();
    });
    </script>

</body>

</html>

