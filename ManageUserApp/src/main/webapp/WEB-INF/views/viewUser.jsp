<%@ include file="./init.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp"></jsp:include>
	
	<body class="bg-info">
		<c:if test="${not empty sessionScope.currentUser}">
			<jsp:include page="navigation.jsp"></jsp:include>
		</c:if>

		<c:url value="/home" var="homeURL"></c:url>
		
		<div class="container">
			<h3 class="text-center">User Details</h3>
			<div class="row col-sm-offset-3 col-sm-6">
				<div class="col-sm-4"><h4>Name:</h4></div>
				<div class="col-sm-8"><h4>${user.getFirstName()}&nbsp;${user.getLastName()}</h4></div>
				
				<div class="col-sm-4"><h4>Username:</h4></div>
				<div class="col-sm-8"><h4>${user.getUserName()}</h4></div>
				
				<div class="col-sm-4"><h4>Password:</h4></div>
				<div class="col-sm-8"><h4>${user.getPassword()}</h4></div>
				
				<div class="col-sm-4"><h4>Gender:</h4></div>
				<div class="col-sm-8"><h4>${user.getGender()}</h4></div>
				
				<div class="col-sm-4"><h4>Role:</h4></div>
				<div class="col-sm-8"><h4>${user.getRole()}</h4></div>
				
				<div class="col-sm-4"><h4>Email Address:</h4></div>
				<div class="col-sm-8"><h4>${user.getEmailAddress()}</h4></div>
				
				<div class="col-sm-4"><h4>Phone Number:</h4></div>
				<div class="col-sm-8"><h4>${user.getPhone()}</h4></div>
				
				<div class="col-sm-12"><h4>Addresses:</h4></div>
				<c:forEach items="${user.getUserAddresses()}" var="address">
					<div class="col-sm-6">
						<h4>
							${address.getStreet1()},<br>
							${address.getStreet2()},<br>
							${address.getLandmark()}&nbsp;${address.getCity()}-${address.getPincode()},<br>
							${address.getState()},&nbsp;${address.getCountry()}.<br>
						</h4>
					</div>
				</c:forEach>
				
				<div class="col-sm-12 text-center">
    				<a href="${homeURL}" class="btn btn-default btn-block">Back To List</a>
				</div>
			</div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>