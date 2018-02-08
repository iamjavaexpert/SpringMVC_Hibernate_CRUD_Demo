<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
	<#include "/header.ftl">
	
	<body class="bg-info">
		<#if Session.currentUser??>
			<#include "/navigation.ftl">
		</#if>
		
		<div class="resetPassword">
			<#if RequestParameters.successMessage?has_content><h4 class="alert alert-success">${RequestParameters.successMessage}</h4></#if>
			<#if RequestParameters.errorMessage?has_content><h4 class="alert alert-danger">${RequestParameters.errorMessage}</h4></#if>
			
			<form method="post" action="<@spring.url "/forgotPassword" />">
				<div class="form-group">
					<label for="emailAddress">Email Address</label>
					<input type="email" class="form-control" name="emailAddress" required="required">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" name="password" required="required">
				</div>
				<input type="submit" class="btn btn-primary btn-block" value="Reset Password">
				<a class="btn btn-default btn-block" href="<@spring.url "/userLogin" />">Cancel</a>
			</form>
		</div>

		<#include "/footer.ftl">
	</body>
</html>
