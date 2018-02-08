<%@ include file="./init.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp"></jsp:include>
	
	<body class="bg-info">
		<c:if test="${not empty sessionScope.currentUser}">
			<jsp:include page="navigation.jsp"></jsp:include>
		</c:if>

		<c:url var="loginUserURL" value="/userLogin"></c:url>
		<c:url value="/registerOrUpdate" var="registerUserURL"></c:url>
		<c:url value="/forgotPassword" var="forgotPasswordURL"></c:url>
		
		<div class="login">
			<c:if test="${not empty param.successMessage}"><h4 class="alert alert-success">${param.successMessage}</h4></c:if>
			<c:if test="${not empty param.errorMessage}"><h4 class="alert alert-danger">${param.errorMessage}</h4></c:if>
			
			<form method="post" action="${loginUserURL}">
				<div class="form-group">
					<label for="username">Username</label>
					<input type="text" class="form-control" name="username" required="required">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" name="password" required="required">
				</div>
				<input type="submit" class="btn btn-primary btn-block" value="Login">
				
				<a href="${forgotPasswordURL}"><small>Forgot password?</small></a>
				
				<p class="text-muted text-center"><small>Do not have an account?</small></p>
				<a class="btn btn-sm btn-default btn-block" href="${registerUserURL}?userId=0&editUser=false">Create an Account</a>
			</form>
		</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
