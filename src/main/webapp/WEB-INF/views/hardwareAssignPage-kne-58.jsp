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
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tickets</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class='bx bx-help-circle' ></i>Help ME!</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/tickets/all">View Tickets</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/tickets/create">Create Ticket</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="container">
		<div class="bg-light p-5 rounded">
			<div class='container'>
				<h1>Hardware Tickets</h1>
				<c:forEach var="message" items="${messages}">
					<div class="alert alert-success alert-dismissible fade show" role="alert" id="mainAlertMessage">${message}
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:forEach>
				<table class="table table-striped table-hover" id="ticketArray" data-striped="true" data-sort-name="creation_date">
					<thead>
						<tr>
							<!-- <th scope="col">ID</th> -->
							<th scope="col" data-sortable="true">Ticket ID</th>
							<th scope="col" data-field="creation_date" data-sortable="true">Creation Date</th>
							<th scope="col" data-sortable="true">Status</th>
							<th scope="col" data-sortable="true">Creator</th>
							<th scope="col" data-sortable="true">Assignee user</th>
							<th scope="col" data-sortable="true">Type</th>
							<th scope="col" data-sortable="true">Priority</th>
							<th scope="col" data-sortable="true">Position</th>
							<th scope="col" data-sortable="true">Hardware</th>
							<th scope="col">View</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var='t' items="${ticketArray}">
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
								<form:form action="${pageContext.request.contextPath}/inventory/assign"
				cssClass="form-control" method="post" modelAttribute="inventory">
								<td><a href="${pageContext.request.contextPath}/inventory/assign/assignTo/${t.id}/${t.hardwareToBeChanged}" class='btn btn-primary'>Assign ${t.hardwareToBeChanged}</a></td>
								</form:form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Option 1: Bootstrap Bundle with Popper -->
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
			</div>
		</div>
	</main>
</body>
</html>