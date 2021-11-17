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
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create ticket</title>
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
				<form:form action="${pageContext.request.contextPath}/tickets/update" method="POST" cssClass="form-horizontal" modelAttribute="ticketViewed">
					<h1>Seen Ticket N# ${ticketViewed.id}</h1>

					<form:input path="id" class="form-select" readonly="true" style="display:none" />

					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">Type</span>
						<form:input type="text" path="typeOfTicket" class="form-select" readonly="true" />
						<span class="input-group-text" id="basic-addon1">Priority</span>
						<form:input type="text" path="priority" class="form-select" readonly="true" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">Hardware to be changed</span>
						<form:input type="text" path="hardwareToBeChanged" class="form-select" readonly="true" />
						<span class="input-group-text" id="basic-addon1">Position number</span>
						<form:input path="position" type="text" />
					</div>
					<form:form method="POST" cssClass="form-horizontal" modelAttribute="comment">
						<table class="table">
							<c:forEach var='c' items="${commentViewed}">
								<tbody>
									<tr>
										<td><i class='bx bxs-user-circle' ></i><b>${c.creator}</b></td>
										<td><span style="font-size: 70%">${c.creationDate}</span></td>
									</tr>
									<tr>
										<td>${c.comment}</td>
									</tr>
							</c:forEach>
							</tbody>
						</table>
						<label for="basic-url" class="form-label">Comments</label>
						<div class="input-group mb-3 form-floating">
							<form:textarea path="comment" class="form-control" id="floatingTextarea" style="height: 120px" required="required"/>
							<label for="floatingTextarea">Write about your issue</label>
						</div>
						<form:button type="submit" class="btn btn-success">Update ticket</form:button>
					</form:form>
					<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
				</form:form>
			</div>
		</div>
	</main>
</body>
</html>
