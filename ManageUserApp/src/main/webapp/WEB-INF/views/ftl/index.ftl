<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
	<#include "/header.ftl">
	
	<body class="bg-info">
		<#if Session.currentUser??>
			<#include "/navigation.ftl">
		</#if>
		
		<div class="login">
			<#if RequestParameters.successMessage?has_content><h4 class="alert alert-success">${RequestParameters.successMessage}</h4></#if>
			<#if RequestParameters.errorMessage?has_content><h4 class="alert alert-danger">${RequestParameters.errorMessage}</h4></#if>
			
			<form method="post" action="<@spring.url "/userLogin" />">
				<div class="form-group">
					<label for="username">Username</label>
					<input type="text" class="form-control" name="username" required="required">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" name="password" required="required">
				</div>
				<input type="submit" class="btn btn-primary btn-block" value="Login">
				
				<a href="<@spring.url "/forgotPassword" />"><small>Forgot password?</small></a>
				
				<p class="text-muted text-center"><small>Do not have an account?</small></p>
				<a class="btn btn-sm btn-default btn-block" href="<@spring.url "/registerOrUpdate" />?userId=0&editUser=false">Create an Account</a>
			</form>
		</div>
		
		<#include "/footer.ftl">
	</body>
</html>
