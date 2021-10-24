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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>Update ticket - Manager View</title>
</head>
<body>
	<div class='container'>
		<h1>Update Ticket</h1>
		<form:form action="${pageContext.request.contextPath}/manager/tickets/update" method="POST" cssClass="form-horizontal" modelAttribute="ticketViewed">
			<div class="row">
				<div class="col">
					<table class="table table-striped table-hover" id="ticketList" data-striped="true" data-sort-name="creation_date" data-search="true">
						<tbody>
							<tr style="display:none">
								<td><span class="input-group-text" id="basic-addon1">ID</span>
									<form:input path="id" class="form-select" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Creation Date</span>
									<form:input path="creationDate" class="form-select" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Status</span>
									<form:select path="status" class="form-select">
										<form:option value="Open">Open</form:option>
										<form:option value="Pending">Pending</form:option>
										<form:option value="Solved">Solved</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Creator</span>
								<form:select path="userCreator" class="form-select">
										<form:option value="user1">User1</form:option>
										<form:option value="user1">User2</form:option>
										<form:option value="user2">User3</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Assignee User</span>
								<form:select path="assigneeUser" class="form-select">
										<form:option value="user1">User1</form:option>
										<form:option value="user1">User2</form:option>
										<form:option value="user2">User3</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Type</span>
									<form:select path="typeOfTicket" class="form-select">
										<form:option value="Software">Software</form:option>
										<form:option value="Hardware">Hardware</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Priority</span>
									<form:select path="priority" class="form-select">
										<form:option value="Low">Low</form:option>
										<form:option value="Normal">Normal</form:option>
										<form:option value="High">High</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Hardware
										to be changed</span> <form:select path="hardwareToBeChanged"
										class="form-select">
										<form:option value="N/A">NA</form:option>
										<form:option value="Keyboard">Keyboard</form:option>
										<form:option value="Mouse">Mouse</form:option>
										<form:option value="HeadSet">HeadSet</form:option>
										<form:option value="Screen">Screen</form:option>
										<form:option value="Tower">Tower</form:option>
										<form:option value="Webcam">Webcam</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td><span class="input-group-text" id="basic-addon1">Position
										number</span> <form:input path="position" type="text" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col">
					<table class="table table-striped table-hover" id="ticketList"
						data-striped="true" data-sort-name="creation_date"
						data-search="true">
						<tbody>
							<tr>
								<td class="input-group mb-3 form-floating">
									<form:textarea path="commentsID" class="form-control" id="floatingTextarea" style="height: 120px" readonly="true" /> 
									<label	for="floatingTextarea">Write about your issue</label>
								</td>
							</tr>
							<tr>
								<td><form:button type="submit" class="btn btn-success">Update ticket</form:button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
