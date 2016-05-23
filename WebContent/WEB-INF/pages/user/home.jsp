<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="header.jsp" />
<!-- end header -->

<!-- Page Content -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Welcome ${name}</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-cloud fa-2x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<c:if test="${not empty cloud }">
								<div class="huge">${cloud[0]}Items</div>
								<div>${cloud[1]}Bytes</div>
							</c:if>
							<c:if test="${ empty cloud }">
								<div class="huge">0 Items</div>
								<div>0 Bytes</div>
							</c:if>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">Cloud Space</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-green">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-book fa-2x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<c:if test="${not empty document }">
								<div class="huge">${document[0]}Items</div>
								<div>${document[1]}Bytes</div>
							</c:if>
							<c:if test="${ empty document }">
								<div class="huge">0 Items</div>
								<div>0 Bytes</div>
							</c:if>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">Documents Space</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-yellow">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-file fa-2x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<c:if test="${not empty template }">
								<div class="huge">${template[0]}Items</div>
								<div>${template[1]}Bytes</div>
							</c:if>
							<c:if test="${ empty template }">
								<div class="huge">0 Items</div>
								<div>0 Bytes</div>
							</c:if>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">Templates Space</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-red">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-edit fa-2x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<c:if test="${not empty gabarit }">
								<div class="huge">${gabarit[0]}Items</div>
								<div>${gabarit[1]}Bytes</div>
							</c:if>
							<c:if test="${ empty gabarit }">
								<div class="huge">0 Items</div>
								<div>0 Bytes</div>
							</c:if>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">Object Space</span> <span
							class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>

	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
			<div class="data data-categorie">
				<c:forEach items="${categories}" var="categorie">
					<input type="hidden" name="${categorie.nomCategorie}"
						value="${fn:length(categorie.gabarits)}">
				</c:forEach>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">Objects by Categorie Chart</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div id="categorie-chart"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
		<div class="col-lg-6">
			<div class="data data-document-share">
				<input type="hidden" name="Out going share" value="${owned}">
				<input type="hidden" name="In Coming Share " value="${shared}">
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">Documents Share chart</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div id="document-share-chart"></div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
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

<!-- Morris Charts JavaScript -->
<script src="<c:url value='assets/js/plugins/morris/raphael.min.js'/> "></script>
<script src="<c:url value='assets/js/plugins/morris/morris.min.js'/> "></script>
<script src="<c:url value='assets/js/plugins/morris/morris-data.js'/>"></script>


<!-- Custom Theme JavaScript -->
<script src="<c:url value='assets/js/sb-admin-2.js'/>"></script>
<script src="<c:url value='assets/js/home-script.js'/>"></script>

</body>

</html>