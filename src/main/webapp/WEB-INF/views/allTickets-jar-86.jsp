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
<link href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css" rel="stylesheet">
<script src="//code.jquery.com/jquery.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
<script>
	$(function() {
		$('#ticketList').bootstrapTable()
	})
</script>
<title>Tickets</title>
</head>
<body>
	<div class='container'>
		<h1>Ticket List</h1>
		<c:forEach var="message" items="${messages}">
			<div class="alert alert-success alert-dismissible fade show" role="alert" id="mainAlertMessage">${message}
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:forEach>
		<table class="table table-striped table-hover" id="ticketList" data-striped="true" data-sort-name="creation_date">
			<thead>
				<tr>
					<!-- <th scope="col">ID</th> -->
					<th scope="col" data-field="creation_date" data-sortable="true">Creation Date</th>
					<th scope="col" data-sortable="true">Status</th>
					<th scope="col" data-sortable="true">Creator</th>
					<th scope="col" data-sortable="true">Assignee user</th>
					<th scope="col" data-sortable="true">Type</th>
					<th scope="col" data-sortable="true">Priority</th>
					<th scope="col" data-sortable="true">Position</th>
					<th scope="col" data-sortable="true">Hardware</th>
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
						<td><a href="${pageContext.request.contextPath}/tickets/viewbyone/${t.id}" class='btn btn-primary'>View</a></td>
						<td><a href="${pageContext.request.contextPath}/tickets/delete/${t.id}" class='btn btn-danger'>Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	</div>
</body>
</html>
