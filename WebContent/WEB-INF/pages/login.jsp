<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Autodoc - Gen Doc App</title>



<!-- Bootstrap Core CSS -->
<link href="<c:url value='assets/css/bootstrap.min.css'/>"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<c:url value='assets/css/plugins/metisMenu/metisMenu.min.css'/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='assets/css/sb-admin-2.css" rel="stylesheet'/>">

<!-- Custom Fonts -->
<link
	href="<c:url value='assets/font-awesome-4.1.0/css/font-awesome.min.css'/> "
	rel="stylesheet" type="text/css">



<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form action="<c:url value='j_spring_security_check'/>"
							method="post" role="form">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="E-mail"
										name="username" type="email" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

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

</body>

</html>
