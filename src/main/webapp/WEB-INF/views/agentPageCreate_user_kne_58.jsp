<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Create User</title>
</head>
<body>
	<div class="sidebar">
		<ul class="nav-list">
			<li><a
				href="${pageContext.request.contextPath}/manager/tickets/create">
					<i class='bx bx-message-alt-add'></i> <span class="links_name">Create
						Ticket</span>
			</a> <span class="tooltip">Create Ticket</span></li>

			<li><a
				href="${pageContext.request.contextPath}/manager/tickets/all"> <i
					class='bx bxs-book-content'></i> <span class="links_name">View
						Tickets</span>
			</a> <span class="tooltip">View Tickets</span></li>

			<li><a href="${pageContext.request.contextPath}/inventory">
					<i class='bx bxs-book-content'></i> <span class="links_name">View
						Inventory</span>
			</a> <span class="tooltip">View Inventory</span></li>

			<li><a href="${pageContext.request.contextPath}/manager"> <i
					class='bx bxs-user-detail'></i> <span class="links_name">Users</span>
			</a> <span class="tooltip">Users</span></li>

			<li><a
				href="${pageContext.request.contextPath}/manager/employee/create">
					<i class='bx bx-user-plus'></i> <span class="links_name">Create
						Employee</span>
			</a> <span class="tooltip">Create Employee</span></li>

			<li><a
				href="${pageContext.request.contextPath}/manager/user/create"> <i
					class='bx bxs-user-plus'></i> <span class="links_name">Create
						User</span>
			</a> <span class="tooltip">Create User</span></li>

			<li><a href="${pageContext.request.contextPath}/preanswers/list">
					<i class='bx bx-copy-alt'></i> <span class="links_name">Predefined
						Answers</span>
			</a> <span class="tooltip">Predefined Answers</span></li>

			<li><a href="${pageContext.request.contextPath}/logout"><i
					class='bx bx-log-out' id="log_out"></i><span class="links_name">Logout</span>
			</a> <span class="tooltip">Logout</span></li>
		</ul>
	</div>
	<section class="home-section">
		<div class="container">
			<h3>Add an User</h3>
			<form:form
				action="${pageContext.request.contextPath}/agent/user/create"
				method="POST" class="form-horizontal" modelAttribute="user">

				<div class="mb-3">
					Name
					<form:input type="text" class="form-control" path="name" />
				</div>
				<div class="mb-3">
					Email
					<form:input type="email" class="form-control" path="email" />
					<form:button type="submit" class="btn btn-primary">Submit</form:button>
				</div>
			</form:form>
		</div>
	</section>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>