<%@ include file="./init.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp"></jsp:include>
	
	<body class="bg-info">
		<c:if test="${not empty sessionScope.currentUser}">
			<jsp:include page="navigation.jsp"></jsp:include>
		</c:if>

		<c:url value="/forgotPassword" var="resetPasswordURL"></c:url>
		<c:url value="/userLogin" var="userLoginURL"></c:url>
		
		<div class="resetPassword">
			<c:if test="${not empty param.successMessage}"><h4 class="alert alert-success">${param.successMessage}</h4></c:if>
			<c:if test="${not empty param.errorMessage}"><h4 class="alert alert-danger">${param.errorMessage}</h4></c:if>
			
			<form method="post" action="${resetPasswordURL}">
				<div class="form-group">
					<label for="emailAddress">Email Address</label>
					<input type="email" class="form-control" name="emailAddress" required="required">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" name="password" required="required">
				</div>
				<input type="submit" class="btn btn-primary btn-block" value="Reset Password">
				<a class="btn btn-default btn-block" href="${userLoginURL}">Cancel</a>
			</form>
		</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
