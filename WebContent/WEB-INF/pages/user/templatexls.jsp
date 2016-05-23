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
			<h1 class="page-header">Template Xls</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">Template Xls List</div>
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
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${templates}" var="template">
									<tr class="${template.nomTemplate}">
										<jsp:useBean id="datetime" class="java.util.Date" />
										<c:set var="nfn"
											value="${fn:split(template.nomTemplate, '_')}" />
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
										<td class="name">${template.nomTemplate}</td>
										<td>${template.categorie.nomCategorie}</td>
										<td class="center size"></td>
										<td class="center">
											<button class='btn btn-info rename-template'
												data-toggle="modal" data-target="#rename-template"
												title="Rename Categorie"
												data-button='${template.idTemplate}'>
												<i class='fa fa-edit'></i>
											</button>
											<button class='btn btn-danger remove-template'
												data-toggle="modal" data-target="#delete-template"
												title="Remove Template" data-button='${template.idTemplate}'>
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
				<div class="panel-heading">Upload Template Xls</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-info empty-alert">
								File extention must be :
								<ul>
									<li>.xlsx</li>
									<li>.xlsm</li>
									<li>.xltx</li>
									<li>.xltm</li>
								</ul>
							</div>
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
										<label>File input</label> <input required="required"
											name="fileName" type="file">
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
			<div class="modal fade" id="rename-template" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Rename Template</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Template Name</label> <input class="form-control"
									id="template-name" placeholder="New Template Name"> <input
									type="hidden" id="ren-template-ref" value="">
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
			<div class="modal fade" id="delete-template" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Template</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this template
									?</label> <input type="hidden" id="del-template-ref" value="">
								<input type="hidden" id="del-template-name" value="">
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
<script src="<c:url value='assets/js/template-xls-script.js'/>"></script>


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

