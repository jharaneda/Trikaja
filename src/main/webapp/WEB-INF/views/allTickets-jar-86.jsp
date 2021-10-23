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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"	rel="stylesheet"	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"	crossorigin="anonymous">

<title>Tickets</title>
</head>
<body>
	<div class='container'>
		<h1>Ticket List</h1>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Creation Date</th>
					<th scope="col">Status</th>
					<th scope="col">Creator</th>
					<th scope="col">Assignee user</th>
					<th scope="col">Type</th>
					<th scope="col">Priority</th>
					<th scope="col">Position</th>
					<th scope="col">Hardware</th>
					<th scope="col">Edit</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach var='t' items="${allTickets}">
						<tr>
							<th scope="row">${t.id }</th>
							<td>${t.creationDate}</td>
							<td>${t.status}</td>
							<td>${t.userCreator}</td>
							<td>${t.assigneeUser}</td>
							<td>${t.typeOfTicket}</td>
							<td>${t.priority}</td>
							<td>${t.position}</td>
							<td>${t.hardwareToBeChanged}</td>
							<td><a href="${pageContext.request.contextPath}/tickets/edit/${s.id}" class='btn btn-primary'>Edit</a></td>
							<td><a href="${pageContext.request.contextPath}/tickets/delete/${s.id}" class='btn btn-danger'>Delete</a></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous"></script>
	</div>
</body>
</html>
