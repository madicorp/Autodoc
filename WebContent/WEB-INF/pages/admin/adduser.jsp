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
			<h1 class="page-header">User Add Control</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Add User</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form id="form" role="form" method="post">
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
										class="form-control" placeholder="Password">
								</div>

								<div class="form-group">
									<label>Role</label> <select name="Role" class="form-control">
										<c:forEach items="${roles}" var="role">
											<option>${role.codeRole}</option>
										</c:forEach>
									</select>
								</div>
								<button type="submit" class="btn btn-default"
									style="float: right;">Submit Button</button>
								<button type="reset" class="btn btn-default">Reset
									Button</button>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
						<div class="col-lg-6">
							<c:if test="${ not empty added }">
								<c:if test="${ added }">
									<div class="alert alert-success add-alert">User Added !</div>
								</c:if>
								<c:if test="${not added }">
									<div class="alert alert-danger add-alert">User Add Failed
										!</div>
								</c:if>
							</c:if>
							<div style="display: none;" class="alert alert-warning">
								<ul>
									<li>email empty .</li>
									<li>password empty .</li>
								</ul>
							</div>
						</div>
						<!-- /.col-lg-6 (nested) -->
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

<!-- Custom Theme JavaScript -->
<script src="<c:url value='assets/js/sb-admin-2.js'/>"></script>
<script src="<c:url value='assets/js/add-user-script.js'/>"></script>

</body>

</html>

