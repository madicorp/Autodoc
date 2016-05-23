<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- header -->
<jsp:include page="header.jsp" />
<!-- end header -->

<!-- Page Content -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">New Object Type</h1>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Add Object Type</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-9" style="overflow: hidden;">
							<form id="myWizard" method="post" type="post"
								class="form-vertical">
								<section class="step" data-step-title="Module">
									<div class="control-group">
										<label class="control-label">Select Categorie</label>
										<div class="controls">
											<select name="categorie" class="form-control">
												<c:forEach items="${categories}" var="categorie">
													<option value="${categorie.idCategorie}">${categorie.nomCategorie}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</section>

								<section class="step" data-step-title="Name">
									<div class="control-group">
										<label class="control-label" for="inputFirstname">Object
											Type Name</label>
										<div class="controls">
											<input type="text" required="required" id="objettypename"
												name="objettypename" placeholder="Object Type Name"
												class="input-xlarge">
										</div>
									</div>
								</section>
								<section class="step" data-step-title="Variables">
									<div class="control-group">
										<label class="control-label">Variables</label>
										<table id="table-args">
											<thead>
												<tr>
													<th>key</th>
												</tr>
											</thead>
											<tbody>
												<tr class="arg-couple">
													<td><input class="form-control key" type="text"
														placeholder="Variable name"></td>
													<td><a href="#" class="btn btn-default rem-agr"> <i
															class='fa fa-minus'></i></a></td>
												</tr>
											</tbody>
										</table>
										<button type="button" class="btn btn-default add-agr">
											<i class='fa fa-plus'></i>
										</button>

									</div>
								</section>
								<input type="hidden" id="args" name="args" value="">
							</form>
						</div>
						<!-- /.col-lg-8 (nested) -->
						<div class="col-lg-3">
							<c:if test="${ not empty created }">
								<c:if test="${ created }">
									<div class="alert alert-success alert-created">Object
										Type Created !</div>
								</c:if>
								<c:if test="${not created }">
									<div class="alert alert-danger alert-created">Object Type
										creation Failed !</div>
								</c:if>
							</c:if>
							<div class="loading">
								<img alt="" src="<c:url value='assets/img/loading.gif'/>">
							</div>
						</div>
					</div>
					<!-- /.row (nested) -->
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
<div id="data"></div>

<!-- jQuery Version 1.11.0 -->
<script src="<c:url value='assets/js/jquery-1.11.0.js '/>"></script>
<script src="<c:url value='assets/js/jquery.easyWizard.js'/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='assets/js/bootstrap.min.js'/> "></script>

<!-- Metis Menu Plugin JavaScript -->
<script
	src="<c:url value='assets/js/plugins/metisMenu/metisMenu.min.js'/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value='assets/js/sb-admin-2.js'/>"></script>
<script src="<c:url value='assets/js/add-object-type-script.js'/>"></script>

</body>

</html>

