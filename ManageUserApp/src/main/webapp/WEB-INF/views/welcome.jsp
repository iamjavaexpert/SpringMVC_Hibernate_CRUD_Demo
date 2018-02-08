<%@ include file="./init.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp"></jsp:include>
	
	<body class="bg-info">
		<c:if test="${not empty sessionScope.currentUser}">
			<jsp:include page="navigation.jsp"></jsp:include>
		</c:if>
		
		<c:url var="logoutURL" value="/logout"></c:url>
		<c:url var="editUserURL" value="/registerOrUpdate"></c:url>
		<c:url var="viewUserURL" value="/viewUserDetails"></c:url>
		<c:url var="deleteUserURL" value="/deleteUser"></c:url>
		
		<div class="container">
			<c:if test="${not empty param.successMessage}"><h4 class="alert alert-success">${param.successMessage}</h4></c:if>
			<c:if test="${not empty param.errorMessage}"><h4 class="alert alert-danger">${param.errorMessage}</h4></c:if>
			
			<table id="userTable" class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Gender</th>
						<th>Username</th>
						<th>Email</th>
						<th>Address</th>
						<th>Phone</th>
						<th>Role</th>
						<th>CreatedOn</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td>${user.getFirstName()} ${user.getLastName()}</td>
							<td>${user.getGender()}</td>
							<td>${user.getUserName()}</td>
							<td>${user.getEmailAddress()}</td>
							<td>
								<c:forEach items="${user.getUserAddresses()}" var="address">
									<c:if test="${address.getPrimaryAddress()}">
										${address.getStreet1()},<br>
										${address.getStreet2()},<br>
										${address.getLandmark()}&nbsp;${address.getCity()}-${address.getPincode()}<br>
										${address.getState()},&nbsp;${address.getCountry()}.
									</c:if>
								</c:forEach>
							</td>
							<td>${user.getPhone()}</td>
							<td>${user.getRole()}</td>
							<td>${user.getCreateDate()}</td>
							<td>
								<a href="${viewUserURL}?userId=${user.getId()}">View</a>
								<a href="${editUserURL}?userId=${user.getId()}&editUser=true">Edit</a>
								<c:if test="${not empty sessionScope.currentUser && sessionScope.currentUser.getId() ne user.getId()}">
									<a href="${deleteUserURL}?userId=${user.getId()}">Delete</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>