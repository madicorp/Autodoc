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
			<h1 class="page-header">Welcome ${name}</h1>
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

</body>

</html>