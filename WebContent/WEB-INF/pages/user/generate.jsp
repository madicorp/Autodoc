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
			<h1 class="page-header">Generate Document</h1>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Generate Document</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-9" style="overflow: hidden;">
							<form id="myWizard" method="post" type="post"
								class="form-vertical">
								<section class="step" data-step-title="Categorie">
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

								<section class="step" data-step-title="Gabarit">
									<div class="control-group">
										<div class="control-group">
											<label class="control-label">Select Gabarit</label>
											<div class="controls">
												<select name="gabarit" id="Gabarit-container"
													class="form-control">
													<option class="option-title-gabarit" selected disabled
														value="default">Select By Categorie</option>
													<c:forEach items="${gabarits}" var="gabarit">
														<c:set var="newName" value="" />
														<c:set var="nfn"
															value="${fn:split(gabarit.nomGabarit, '_')}" />
														<c:choose>
															<c:when test="${fn:length(nfn) gt 2}">
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
																<c:set var="newName"
																	value="${nfn[0]}.${fn:split(nfn[1],'.')[1]}" />
															</c:otherwise>
														</c:choose>
														<option id="${gabarit.idGabarit}"
															class="${gabarit.categorie.idCategorie}"
															value="${gabarit.nomGabarit}">${newName}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
									<div class=" edit-agr-sec">
										<label class="control-label">Edit Variables</label>
										<button type="button" class="btn btn-default  edit-agr-btn">
											<i class='fa fa-edit'></i>
										</button>
										<button type="button" class="btn btn-default  cancel-agr-btn">
											<i class='fa fa-times'></i>
										</button>
									</div>
									<div class="control-group gp-var">
										<label class="control-label">Variables</label>
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
										<input type="hidden" id="clone-gabarit" value="false">
										<input type="hidden" id="clone-gabarit-id" value="">
										<button type="button" class="btn btn-default add-agr">
											<i class='fa fa-plus'></i>
										</button>

									</div>
								</section>
								<section class="step" data-step-title="Template">
									<div class="control-group">
										<label>Template Type</label> <label class="radio-inline">
											<input type="radio" name="templateType"
											class="optionTemplate" title="Docx" value="Docx" checked="">Docx
										</label> <label class="radio-inline"> <input type="radio"
											name="templateType" class="optionTemplate" title="Pptx"
											value="Ppt">Pptx
										</label> <label class="radio-inline"> <input type="radio"
											name="templateType" class="optionTemplate" title="Xls"
											value="Xls">Xls
										</label>

									</div>
									<div class="control-group">
										<label class="control-label">Select Template</label>
										<div class="controls">
											<select name="template" id="Template-container"
												class="form-control">
												<option class="option-title-template" selected disabled
													value="default">Select By Categorie</option>
												<c:forEach items="${templates}" var="template">
													<c:set var="newName" value="" />
													<c:set var="nfn"
														value="${fn:split(template.nomTemplate, '_')}" />
													<c:choose>
														<c:when test="${fn:length(nfn) gt 2}">
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
															<c:set var="newName"
																value="${nfn[0]}.${fn:split(nfn[1],'.')[1]}" />
														</c:otherwise>
													</c:choose>
													<option id="${template.idTemplate}"
														class="${template.categorie.idCategorie} ${template.type}"
														value="${template.nomTemplate}">${newName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</section>
								<section class="step" data-step-title="Generate">
									<div class="control-group">
										<label class="control-label" for="inputFirstname">New
											Document Name</label>
										<div class="controls">
											<input type="text" required="required" id="inputFirstname"
												name="docName" placeholder="New Document Name"
												class="input-xlarge">
										</div>
									</div>
								</section>
								<input type="hidden" id="templateId" name="templateId" value="">
								<input type="hidden" id="gabaritId" name="gabaritId" value="">

							</form>
						</div>
						<!-- /.col-lg-8 (nested) -->
						<div class="col-lg-3">
							<c:if test="${ not empty created }">
								<c:if test="${ created }">
									<div class="alert alert-success alert-created">Document
										Created !</div>
								</c:if>
								<c:if test="${not created }">
									<div class="alert alert-danger alert-created">Document
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
<script src="<c:url value='assets/js/generate-script.js'/>"></script>

</body>

</html>

