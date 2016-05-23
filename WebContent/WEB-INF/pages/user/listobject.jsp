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
			<h1 class="page-header">List Object</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Object List</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables">
							<thead>
								<tr>
									<th>Date</th>
									<c:set var="fields"
										value="${fn:split(object_type.visibleFields, '/___/')}" />
									<c:forEach items="${fields}" var="field">
										<th>${field}</th>
									</c:forEach>
									<th>Categorie</th>
									<th>Object Type</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${fullObjects}" var="object">
									<tr class="${object.key.nomGabarit}">
										<jsp:useBean id="datetime" class="java.util.Date" />
										<c:set var="nfn"
											value="${fn:split(object.key.nomGabarit, '_')}" />
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



										<c:forEach items="${object.value}" var="args">
											<c:set var="targs" value="${fn:split(args, '/__/')}" />
											<c:forEach items="${fields}" var="field">
												<c:if test="${fn:contains(field, targs[0])}">
													<td>${targs[1]}</td>
												</c:if>
											</c:forEach>
										</c:forEach>
										<td><a
											href="list-object-type?item=${object.key.categorie.idCategorie}">${object.key.categorie.nomCategorie}</a></td>
										<td><a
											href="list-object?item=${object.key.objectType.idObjectType}">${object.key.objectType.nomObjectType}</a></td>
										<td class="center">
											<button class='btn btn-success edit-ob' data-toggle="modal"
												data-target="#edit-ob" title="Edit Object"
												data-button='${object.key.idGabarit}'>
												<i class='fa fa-edit'></i>
											</button>
											<button class='btn btn-info rename-ob' data-toggle="modal"
												data-target="#rename-ob" title="Rename Object"
												data-button='${object.key.idGabarit}'>
												<i class='fa fa-minus'></i>
											</button>
											<button class='btn btn-warning generate-doc'
												data-toggle="modal" data-target="#generate-doc"
												title="Generate Document"
												data-button='${object.key.idGabarit}'>
												<i class='fa fa-cogs'></i>
											</button>
											<button class='btn btn-danger remove-ob' data-toggle="modal"
												data-target="#delete-ob" title="Remove Object"
												data-button='${object.key.idGabarit}'>
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
			<div class="modal fade" id="edit-ob" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Edit Object</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-success success-alert">Object
								Edited !</div>
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
								<input type="hidden" id="edit-ob-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary edit-ob-conf">Save
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
			<div class="modal fade" id="generate-doc" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Generate Document
							</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-info empty-alert">
								<ul>

								</ul>
							</div>
							<div class="alert alert-success success-alert">Document
								Created !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Document Name</label> <input class="form-control"
									id="doc-name" placeholder="New Dcument Name">
							</div>
							<div class="form-group">
								<label>Template Type</label> <label class="radio-inline">
									<input type="radio" name="templateType" class="optionTemplate"
									title="Docx" value="Docx" checked="">Docx
								</label> <label class="radio-inline"> <input type="radio"
									name="templateType" class="optionTemplate" title="Pptx"
									value="Ppt">Pptx
								</label> <label class="radio-inline"> <input type="radio"
									name="templateType" class="optionTemplate" title="Xls"
									value="Xls">Xls
								</label> <input type="hidden" id="generate-doc-ref" value="">
							</div>
							<div class="control-group">
								<label class="control-label">Select Template</label> <select
									name="template" id="Template-container" class="form-control">
									<option class="option-title-template" selected disabled
										value="default">Select By Categorie</option>

								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary generate-doc-conf">Generate
								Document</button>
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
			<div class="modal fade" id="rename-ob" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Rename Object</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>
							<div class="alert alert-info empty-alert">Name is empty !</div>
							<div class="alert alert-success success-alert">Object
								Renamed !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>New Object Name</label> <input class="form-control"
									id="ob-name" placeholder="New Object Name"> <input
									type="hidden" id="ren-ob-ref" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary rename-ob-conf">Save
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
			<div class="modal fade" id="delete-ob" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Object</h4>
							<div class="alert alert-danger fail-alert">Error Try Again
								!</div>

							<div class="alert alert-success success-alert">Object
								Removed !</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Confirmation: Do you want to Remove this Object ?</label>
								<input type="hidden" id="del-ob-ref" value=""> <input
									type="hidden" id="del-ob-name" value="">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancel</button>
							<button type="button" class="btn btn-primary delete-ob-conf">Delete</button>
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
<script src="<c:url value='assets/js/list-object-script.js'/>"></script>


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

