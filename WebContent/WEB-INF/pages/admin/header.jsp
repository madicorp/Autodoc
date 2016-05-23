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

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Autodoc v1.0</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li><a href="<c:url value='/'/>" title="User Mode"> <i
						class="fa fa-user fa-fw"></i>
				</a></li>
				<!-- /.dropdown -->
				<li><a href="<c:url value='/j_spring_security_logout'/>"
					title="Logout"> <i class="fa fa-sign-out fa-fw"></i>
				</a></li>

				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="${adduserPage }${listuserPage }"><a href="#"><i
								class="fa fa-user fa-fw"></i> Users<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="<c:url value='/admin/add-user'/>"
									class="${adduserPage }">create user</a></li>
								<li><a href="<c:url value='/admin/list-user'/>"
									class="${listuserPage }">List user</a></li>
							</ul> <!-- /.nav-second-level --></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>