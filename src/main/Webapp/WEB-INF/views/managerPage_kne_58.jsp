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

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>User Landing</title>
</head>
<body>
	<div class="container">
		<h1>Employee List</h1>
		<c:if test="${ messages !=null}">
			<c:forEach var="message" items="${messages}">
				<div class="alert alert-success fade show" role="alert">${message}</div>
			</c:forEach>
		</c:if>
		
		<table class="table table-striped">
			<thead>
				<th>Name</th>
				<th>Email</th>
				<th>Position</th>
				<th>Number of Current Tickets</th>
			</thead>
			
			<tbody>
				<c:forEach var="e" items="${employeesArray}">
					<tr>
						<td>${e.name}</td>
						<td>${e.email}</td>
						<td>${e.position}</td>
						<td>${e.numAssignTicks}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container">
		<h1>User List</h1>
		<c:if test="${ messages !=null}">
			<c:forEach var="message" items="${messages}">
				<div class="alert alert-success fade show" role="alert">${message}</div>
			</c:forEach>
		</c:if>
		
		<table class="table table-striped">
			<thead>
				<th>Name</th>
				<th>Email</th>
				<th>Number of Current Tickets</th>
			</thead>
			
			<tbody>
				<c:forEach var="u" items="${usersArray}">
					<tr>
						<td>${u.name}</td>
						<td>${u.email}</td>
						<td>${u.numAssignTicks}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

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