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
			<h1 class="page-header">List of Users</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">users list</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Prenom</th>
									<th>Email</th>
									<th>Role</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${users}" var="user">
									<tr>
										<td class="lname">${user.nomUser}</td>
										<td class="fname">${user.prenomUser}</td>
										<td class="login">${user.login}</td>
										<td class="role center">${user.role.codeRole}</td>
										<td class="center">
											<button class='btn btn-info edit-user' data-toggle="modal"
												data-target="#edit-user" title="Edit User"
												data-button='${user.idUser}'>
												<i class='fa fa-edit'></i>
											</button> <c:if test="${user.login ne me }">
												<button class='btn btn-danger remove-user'
													data-toggle="modal" data-target="#delete-user"
													title="Remove User" data-button='${user.idUser}'>
													<i class='fa fa-times'></i>
												</button>
											</c:if>
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
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<!-- Modal -->
			<div class="modal fade" id="edit-user" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Edit Gabarit</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Nom</label> <input name="Nom" class="form-control"
									placeholder="Nom">
							</div>
							<div class="form-group">
								<label>Prenom</label> <input name="Prenom" class="form-control"
									placeholder="Prenom">
							</div>
							<div class="form-group">
								<label>Email</label> <input name="Email" class="form-control"
									placeholder="Email">
							</div>
							<div class="form-group">
								<label>Password</label> <input name="Password" type="password"
									class="form-control" placeholder="Password" value="password">
							</div>
							<div class="form-group">
								<label>Role</label> <select name="Role" class="form-control">
									<c:forEach items="${roles}" var="role">
										<option>${role.codeRole}</option>
									</c:forEach>
								</select>
							</div>
							<input type="hidden" id="edit-user-ref" value="">
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
		</div>
		<!-- /.col-lg-6 -->
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
<script src="<c:url value='assets/js/list-user-script.js'/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>

</body>

</html>

