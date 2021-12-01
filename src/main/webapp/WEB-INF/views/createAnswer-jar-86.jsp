<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style type="text/css">
/* Google Font Link */
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Poppins", sans-serif;
}

.sidebar {
	position: fixed;
	left: 0;
	top: 0;
	height: 100%;
	width: 100px;
	background: #11101D;
	padding: 6px 14px;
	z-index: 99;
	transition: all 0.5s ease;
}

.sidebar.open {
	width: 250px;
}

.sidebar .logo-details {
	height: 60px;
	display: flex;
	align-items: center;
	position: relative;
}

.sidebar .logo-details .icon {
	opacity: 0;
	transition: all 0.5s ease;
}

.sidebar .logo-details .logo_name {
	color: #fff;
	font-size: 20px;
	font-weight: 600;
	opacity: 0;
	transition: all 0.5s ease;
}

.sidebar.open .logo-details .icon, .sidebar.open .logo-details .logo_name
	{
	opacity: 1;
}

.sidebar .logo-details #btn {
	position: absolute;
	top: 50%;
	right: 0;
	transform: translateY(-50%);
	font-size: 22px;
	transition: all 0.4s ease;
	font-size: 23px;
	text-align: center;
	cursor: pointer;
	transition: all 0.5s ease;
}

.sidebar.open .logo-details #btn {
	text-align: right;
}

.sidebar i {
	color: #fff;
	height: 60px;
	min-width: 10px;
	font-size: 28px;
	text-align: center;
	line-height: 60px;
}

.sidebar .nav-list {
	margin-top: 20px;
	height: 100%;
}

.sidebar li {
	position: relative;
	margin: 10px 0;
	list-style: none;
}

.sidebar li .tooltip {
	position: absolute;
	top: -20px;
	left: calc(100% + 15px);
	z-index: 3;
	background: #fff;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
	padding: 6px 12px;
	border-radius: 4px;
	font-size: 15px;
	font-weight: 400;
	opacity: 0;
	white-space: nowrap;
	pointer-events: none;
	transition: 0s;
}

.sidebar li:hover .tooltip {
	opacity: 1;
	pointer-events: auto;
	transition: all 0.4s ease;
	top: 50%;
	transform: translateY(-50%);
}

.sidebar.open li .tooltip {
	display: none;
}

.sidebar input {
	font-size: 15px;
	color: #FFF;
	font-weight: 400;
	outline: none;
	height: 50px;
	width: 100%;
	width: 50px;
	border: none;
	border-radius: 12px;
	transition: all 0.5s ease;
	background: #1d1b31;
}

.sidebar.open input {
	padding: 0 20px 0 50px;
	width: 100%;
}

.sidebar .bx-search {
	position: absolute;
	top: 50%;
	left: 0;
	transform: translateY(-50%);
	font-size: 22px;
	background: #1d1b31;
	color: #FFF;
}

.sidebar.open .bx-search:hover {
	background: #1d1b31;
	color: #FFF;
}

.sidebar .bx-search:hover {
	background: #FFF;
	color: #11101d;
}

.sidebar li a {
	display: flex;
	height: 100%;
	width: 100%;
	border-radius: 12px;
	align-items: center;
	text-decoration: none;
	transition: all 0.4s ease;
	background: #11101D;
}

.sidebar li a:hover {
	background: #FFF;
}

.sidebar li a .links_name {
	color: #fff;
	font-size: 15px;
	font-weight: 400;
	white-space: nowrap;
	opacity: 0;
	pointer-events: none;
	transition: 0.4s;
}

.sidebar.open li a .links_name {
	opacity: 1;
	pointer-events: auto;
}

.sidebar li a:hover .links_name, .sidebar li a:hover i {
	transition: all 0.5s ease;
	color: #11101D;
}

.sidebar li i {
	height: 50px;
	line-height: 50px;
	font-size: 18px;
	border-radius: 12px;
}

.sidebar li.profile {
	position: fixed;
	height: 60px;
	width: 78px;
	left: 0;
	bottom: -8px;
	padding: 10px 14px;
	background: #1d1b31;
	transition: all 0.5s ease;
	overflow: hidden;
}

.sidebar.open li.profile {
	width: 250px;
}

.sidebar li .profile-details {
	display: flex;
	align-items: center;
	flex-wrap: nowrap;
}

.sidebar li img {
	height: 45px;
	width: 45px;
	object-fit: cover;
	border-radius: 6px;
	margin-right: 10px;
}

.sidebar li.profile .name, .sidebar li.profile .job {
	font-size: 15px;
	font-weight: 400;
	color: #fff;
	white-space: nowrap;
}

.sidebar li.profile .job {
	font-size: 12px;
}

.sidebar .profile #log_out {
	position: absolute;
	top: 50%;
	right: 0;
	transform: translateY(-50%);
	background: #1d1b31;
	width: 100%;
	height: 60px;
	line-height: 60px;
	border-radius: 0px;
	transition: all 0.5s ease;
}

.sidebar.open .profile #log_out {
	width: 50px;
	background: none;
}

.home-section {
	position: relative;
	background: #E4E9F7;
	min-height: 100vh;
	top: 0;
	left: 100px;
	width: calc(90% - 78px);
	transition: all 0.5s ease;
	z-index: 2;
}

.sidebar.open ~ .home-section {
	left: 250px;
	width: calc(100% - 250px);
}

.home-section .text {
	display: inline-block;
	color: #11101d;
	font-size: 20px;
	font-weight: 500;
	margin: 18px
}

@media ( max-width : 420px) {
	.sidebar li .tooltip {
		display: none;
	}
}
</style>
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Create ticket</title>
</head>
<body>
	<div class="sidebar">
		<ul class="nav-list">
			<li><a href="${pageContext.request.contextPath}/manager/tickets/create"> <i class='bx bx-message-alt-add'></i> <span class="links_name">Create Ticket</span>
			</a> <span class="tooltip">Create Ticket</span></li>

			<li><a href="${pageContext.request.contextPath}/manager/tickets/all"> <i class='bx bxs-book-content'></i> <span class="links_name">View Tickets</span>
			</a> <span class="tooltip">View Tickets</span></li>

			<li><a href="${pageContext.request.contextPath}/inventory"> <i class='bx bxs-book-content'></i> <span class="links_name">View Inventory</span>
			</a> <span class="tooltip">View Inventory</span></li>

			<li><a href="${pageContext.request.contextPath}/manager"> <i class='bx bxs-user-detail'></i> <span class="links_name">Users</span>
			</a> <span class="tooltip">Users</span></li>

			<li><a href="${pageContext.request.contextPath}/manager/employee/create"> <i class='bx bx-user-plus'></i> <span class="links_name">Create Employee</span>
			</a> <span class="tooltip">Create Employee</span></li>

			<li><a href="${pageContext.request.contextPath}/manager/user/create"> <i class='bx bxs-user-plus'></i> <span class="links_name">Create User</span>
			</a> <span class="tooltip">Create User</span></li>

			<li><a href="${pageContext.request.contextPath}/logout"><i class='bx bx-log-out' id="log_out"></i><span class="links_name">Logout</span> </a> <span class="tooltip">Logout</span></li>
		</ul>
	</div>
	<section class="home-section">
		<nav class="navbar navbar-expand-lg navbar-dark bg-black">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/preanswers/list">View Predefined answers</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/preanswers/create">Create Predefined Answer</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class='container'>
			<h1>Create Answer</h1>
			<form:form action="${pageContext.request.contextPath}/preanswers/create" method="POST" cssClass="form-horizontal" modelAttribute="answer">
				<div class="row">
					<div class="col">
						<table class="table table-striped table-hover" id="ticketList" data-striped="true" data-sort-name="name" data-search="true">
							<tbody>
								<tr>
									<td><span class="input-group-text" id="basic-addon1">Name</span> <form:input path="name" type="text" required="required" /></td>
								</tr>
								<tr>
									<td class="input-group mb-3 form-floating"><form:textarea path="comment" class="form-control" id="floatingTextarea" style="min-width:100px; max-width:100%;min-height:50px;height:100%;width:100%;" required="required" /> <label
										for="floatingTextarea">Write your predefined answer</label></td>
								</tr>
								<tr>
									<td><form:button type="submit" class="btn btn-success">Create Predefined Answer</form:button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</body>
</html>
