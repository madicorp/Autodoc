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
			<h1 class="page-header">Document</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Document List</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables">
							<thead>
								<tr>
									<th>Date</th>
									<th>Nom</th>
									<th>Shared By</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${documents}" var="document">
									<c:set var="newName" value="" />
									<tr class="${document.key.nomDocument}">
										<jsp:useBean id="datetime" class="java.util.Date" />
										<c:set var="nfn"
											value="${fn:split(document.key.nomDocument, '_')}" />

										<c:choose>
											<c:when test="${fn:length(nfn) gt 2}">

												<jsp:setProperty name="datetime" property="time"
													value="${fn:split(nfn[fn:length(nfn)-1],'.')[0]}" />
												<c:forEach var="i" begin="0" end="${fn:length(nfn)-2}">
													<c:choose>
														<c:when test="${i <= (fn:length(nfn)-3) }">
															<c:set var="newName" value="${newName}${nfn[i]}_" />
														</c:when>
														<c:otherwise>
															<c:set var="newName" value="${newName}${nfn[i]}" />
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="newName"
													value="${newName}.${fn:split(nfn[fn:length(nfn)-1],'.')[1]}" />
											</c:when>
											<c:otherwise>
												<jsp:setProperty name="datetime" property="time"
													value="${fn:split(nfn[1],'.')[0]}" />
												<c:set var="newName"
													value="${nfn[0] }.${fn:split(nfn[1],'.')[1]}" />
											</c:otherwise>
										</c:choose>
										<td class="date"><fmt:formatDate value="${datetime}"
												pattern="MM/dd/yyyy HH:mm" /></td>
										<td class="name">${newName}</td>
										<td>${document.key.categorie.user.nomUser}
											${document.key.categorie.user.prenomUser}</td>
										<td class="center">

											<button class='btn btn-default download-document'
												title="Download Document"
												data-button='${document.key.idDocument}'>
												<i class='fa fa-cloud-download'></i>
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
<script src="<c:url value='assets/js/jquery.fileDownload.js'/>"></script>
<script src="<c:url value='assets/js/shared-document-script.js'/>"></script>


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

