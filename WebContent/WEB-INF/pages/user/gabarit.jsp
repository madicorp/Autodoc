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
			<h1 class="page-header">Gabarit</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">Gabarit List</div>
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
									<th>taille</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${gabarits}" var="gabarit">
									<tr class="${gabarit.nomGabarit}">
										<jsp:useBean id="datetime" class="java.util.Date" />
										<c:set var="nfn" value="${fn:split(gabarit.nomGabarit, '_')}" />
										<c:choose>
											<c:when test="${fn:length(nfn) gt 2}">

												<jsp:setProperty name="datetime" property="time"
													value="${fn:split(nfn[fn:length(nfn)-1],'.')[0]}" />
											</c:when>
											<c:otherwise>
												<jsp:setProperty name="datetime" property="time"
													value="${fn:split(nfn[1],'.')[0]}" />
											</c:otherwise>
										</c:choose>
										<td class="date"><fmt:formatDate value="${datetime}"
												pattern="MM/dd/yyyy HH:mm" /></td>
										<td class="name">${gabarit.nomGabarit}</td>
										<td>${gabarit.categorie.nomCategorie}</td>
										<td class="center size"></td>
										<td class="center">
											<button class='btn btn-success edit-gabarit'
												data-toggle="modal" data-target="#edit-gabarit"
												title="Edit Gabarit" data-button='${gabarit.idGabarit}'>
												<i class='fa fa-edit'></i>
											</button>
											<button class='btn btn-info rename-gabarit'
												data-toggle="modal" data-target="#rename-gabarit"
												title="Rename Gabarit" data-button='${gabarit.idGabarit}'>
												<i class='fa fa-minus'></i>
											</button>
											<button class='btn btn-danger remove-gabarit'
												data-toggle="modal" data-target="#delete-gabarit"
												title="Remove Gabarit" data-button='${gabarit.idGabarit}'>
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
		<div class="col-lg-4">
			<div class="panel panel-default">
				<div class="panel-heading">Upload gabarit</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<input type="hidden" name="name" value="upload">
								<div class="form-group">
									<label>Categorie</label> <select name="categorie"
										class="form-control">
										<c:forEach items="${categories}" var="categorie">
											<option value="${categorie.idCategorie}">${categorie.nomCategorie}</option>
										</c:forEach>
									</select>
									<div class="form-group">
										<label>File input</label> <input name="fileName" type="file"
											required="required">
									</div>
								</div>
								<button type="submit" class="btn btn-default"
									style="float: right;">Upload Button</button>
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
			<div class="modal fade" id="edit-gabarit" tabindex="-1" role="dialog"
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
								<table id="table-args">
									<thead>
										<tr>
											<th>Key</th>
											<th>Value</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								<input type="hidden" id="edit-gabarit-ref" value="">
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
			<div class="modal fade" id="rename-gabarit" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Rename Gabarit</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Gabarit Name</label> <input class="form-control"
									id="gabarit-name" placeholder="New Gabarit Name"> <input
									type="hidden" id="ren-gabarit-ref" value="">
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
			<div class="modal fade" id="delete-gabarit" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Gabarit</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this gabarit
									?</label> <input type="hidden" id="del-gabarit-ref" value=""> <input
									type="hidden" id="del-gabarit-name" value="">
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
<script src="<c:url value='assets/js/gabarit-script.js'/>"></script>


<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script type="text/javascript">

    $(document).ready(function() {
		$('#dataTables').dataTable();

        $('#dataTables').on( 'draw.dt', function () {
            load();
        } );
    });
    </script>

</body>

</html>

