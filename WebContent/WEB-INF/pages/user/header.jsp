<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
<link href="<c:url value='assets/css/bootstrap-switch.min.css'/>"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<c:url value='assets/css/plugins/metisMenu/metisMenu.min.css'/>"
	rel="stylesheet">

<link href="<c:url value='assets/css/plugins/timeline.css'/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='assets/css/sb-admin-2.css" rel="stylesheet'/>">

<!-- Custom Fonts -->
<link
	href="<c:url value='assets/font-awesome-4.1.0/css/font-awesome.min.css'/> "
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value='assets/css/chosen.css'/> ">
<c:if test="${not empty cloudPage }">
	<link href="<c:url value='assets/css/cloud.css" rel="stylesheet'/> ">
</c:if>

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
				<a rel="external" class="navbar-brand" href="<c:url value='/'/> ">
					<img class="logo" alt=""
					src="<c:url value='assets/img/logo.png'/> ">
				</a>
			</div>
			<!-- /.navbar-header -->



			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->

				<security:authorize ifAllGranted="ROLE_admin">
					<li><a rel="external" href="admin" title="Admin Mode"> <i
							class="fa fa-male fa-fw"></i>
					</a></li>
					<!-- /.dropdown -->
				</security:authorize>
				<li><a rel="external"
					href="<c:url value='j_spring_security_logout'/>" title="Logout">
						<i class="fa fa-sign-out fa-fw"></i>
				</a></li>

				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->
			<c:if test="${empty cloudPage }">

				<ul class="nav navbar-top-links navbar-right">

					<li class="dropdown"><a rel="external" id="dLabel"
						role="button" data-toggle="dropdown" data-target="#">
							<button type="button" class="btn btn-primary">
								Categorie <span class="caret"></span>
							</button>
					</a>
						<ul class="dropdown-menu multi-level" role="menu"
							aria-labelledby="dropdownMenu">
							<li><a data-toggle="modal" data-target="#new-categorie"
								href="#">New Categorie</a></li>
							<c:if test="${false}">
								<li><a href="#">List Categories</a></li>
							</c:if>
							<c:if test="${not empty  categories}">
								<li class="divider"></li>
								<li class="dropdown-submenu"><a rel="external"
									tabindex="-1" href="#">Hover me for more options</a>

									<ul class="dropdown-menu">
										<!-- catecories -->

										<c:forEach var="categorie" items="${categories }">
											<li class="dropdown-submenu"><a rel="external"
												href="list-object-type?item=${categorie.idCategorie}">${categorie.nomCategorie}</a>
												<c:if test="${not empty categorie.objectTypes}">
													<ul class="dropdown-menu">
														<c:forEach items="${categorie.objectTypes }"
															var="objectType">
															<li><a rel="external"
																href="list-object?item=${objectType.idObjectType}">${objectType.nomObjectType}</a></li>
														</c:forEach>
													</ul>
												</c:if></li>
										</c:forEach>
										<!-- end categories -->
									</ul></li>
							</c:if>
						</ul></li>
				</ul>
			</c:if>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a rel="external" href="<c:url value='/'/> "
							class="${homePage}"><i class="fa fa-dashboard fa-fw"></i>
								Home</a></li>
						<li><a rel="external" href="cloud-space" class="${cloudPage}"><i
								class="fa fa-cloud fa-fw"></i> Cloud Space</a></li>
						<li class="${documentPage}"><a rel="external" href="#"><i
								class="fa fa-book fa-fw"></i> Documents<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a rel="external" href="documents"
									class="${documentPage}"> My Documents</a></li>
								<li><a rel="external" href="shared-documents"
									class="${sharedPage }"> Shared Documents</a></li>
							</ul></li>
						<li
							class="${templateDocPage }${templatePptPage }${templateXlsPage }">
							<a rel="external" href="#"><i class="fa fa-file fa-fw"></i>
								Templates<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a rel="external" href="template-doc"
									class="${templateDocPage }">Docx</a></li>
								<li><a rel="external" href="template-ppt"
									class="${templatePptPage }">Pptx</a></li>
								<li><a rel="external" href="template-xls"
									class="${templateXlsPage }">Xls</a></li>
							</ul> <!-- /.nav-second-level -->
						</li>

						<c:if test="${false }">
							<li><a rel="external" href="gabarit" class="${gabaritPage }"><i
									class="fa fa-edit fa-fw"></i> Gabarits</a></li>
						</c:if>
						<li class="${addObjectTypePage }${listObjectTypePage }"><a
							rel="external" href="#"><i class="fa fa-bullseye fa-fw"></i>
								Object Type<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a rel="external" href="add-object-type"
									class="${addObjectTypePage}">New Object Type</a></li>
								<li><a rel="external" href="list-object-type"
									class="${listObjectTypePage }">List Object Type</a></li>
							</ul> <!-- /.nav-second-level --></li>
						<li class="${addObjectPage}${listObjectPage }"><a
							rel="external" href="#"><i class="fa fa-edit fa-fw"></i>
								Objects<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a rel="external" href="add-object"
									class="${addObjectPage}">New Object</a></li>

								<c:if test="${false }">
									<li><a rel="external" href="list-object"
										class="${listObjectPage }">List Object</a></li>
								</c:if>
							</ul> <!-- /.nav-second-level --></li>
						<li><a rel="external" href="generate"
							class="${generatePage }"><i class="fa fa-cogs fa-fw"></i>
								Generate Doc</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>