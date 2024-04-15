<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


			<html>

			<head>
				<meta charset="ISO-8859-1">
				<title>Report</title>
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
					crossorigin="anonymous">

			</head>

			<body>

				<div class="container">
					<h3 class="mb-4">Report Application:</h3>
					<form:form action="search" modelAttribute="search" method="POST">
						<table>
							<tr>
								<td>Plan Name:</td>
								<td>
									<form:select path="planName">
										<form:option value=''>-Select-</form:option>
										<form:options items="${ plans}" />
									</form:select>
								</td>
								<td>Plan Status:</td>
								<td>
									<form:select path="planStatus">
										<form:option value=''>-Select-</form:option>
										<form:options items="${ statuses}" />
									</form:select>
								</td>

								<td>Gender:</td>
								<td>
									<form:select path="gender">
										<form:option value=''>-Select-</form:option>
										<form:option value='Male'>Male</form:option>
										<form:option value='Female'>Female</form:option>
									</form:select>
								</td>
							</tr>
							<tr>
								<td>Start date:</td>
								<td>
									<form:input type="date" path="planStartDate"></form:input>
								</td>
								<td>End Date:</td>
								<td>
									<form:input type="date" path="planEndDate"></form:input>
								</td>
								<td><a href="/" class="btn btn-light"> Reset </a></td>
								<td><input type="submit" value="Search" class="btn btn-secondary"></input></td>
							</tr>

						</table>

						<hr>

						<table class="table table-striped table-hover">

							<thead>

								<tr>
									<th>S. No</th>
									<th>Holder Name</th>
									<th>Gender</th>
									<th>Plan Name</th>
									<th>Plan Status</th>
									<th>Plan Start date</th>
									<th>Plan End date</th>
									<th>Benefit Amount</th>
									<th>Plan Terminated date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ allPlans}" var="plan" varStatus="index">
									<tr>
										<td>${ index.count}</td>
										<td>${ plan.citizenName}</td>
										<td>${ plan.gender}</td>
										<td>${ plan.planName}</td>
										<td>${ plan.planStatus}</td>
										<td>${ plan.planStartDate}</td>
										<td>${ plan.planEndDate}</td>
										<td>${ plan.benefitAmount}</td>
										<td>${ plan.terminatedDate}</td>

									</tr>
								</c:forEach>
								<tr>
								<c:if test="${empty allPlans }">
									<td colspan="9"><h3 class="text-center" >No Records Found!!!</h3></td>
								</c:if>
								</tr>
							</tbody>

						</table>


						<hr>


						<div>
							Export: <a href="/excel">Excel</a> | <a href="/pdf">PDF</a>
						</div>
						
						<p class="text-success"> ${ msg} </p>
							
					</form:form>
				</div>



				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
					crossorigin="anonymous"></script>
			</body>

			</html>