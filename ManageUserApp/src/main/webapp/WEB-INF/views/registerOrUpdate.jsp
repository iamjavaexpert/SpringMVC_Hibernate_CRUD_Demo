<%@ include file="./init.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp"></jsp:include>
	
	<body class="bg-info">
		<c:if test="${not empty sessionScope.currentUser}">
			<jsp:include page="navigation.jsp"></jsp:include>
		</c:if>
		
		<c:url value="/registerOrUpdate" var="registerUserURL"></c:url>
		<c:url value="/deleteUserAddress" var="deleteUserAddressURL"></c:url>
		<c:url value="/home" var="homeURL"></c:url>
		<c:url value="/userLogin" var="userLoginURL"></c:url>
		
		<div class="container">
			<h3 class="text-center">${empty sessionScope.currentUser ? "Create" : "Update"} an Account</h3>
			
			<c:if test="${not empty param.successMessage}"><h4 class="alert alert-success">${param.successMessage}</h4></c:if>
			<c:if test="${not empty param.errorMessage}"><h4 class="alert alert-danger">${param.errorMessage}</h4></c:if>
			
			<form:form id="user-form"  cssClass="form-horizontal col-sm-offset-3 col-sm-6" action="${registerUserURL}" method="post" modelAttribute="userForm">
				<form:hidden path="id"/>
				
				<div class="form-group">
					<form:input cssClass="form-control" placeholder="First Name" type="text" required="required" path="firstName"/>
				</div>
				<div class="form-group">
					<form:input cssClass="form-control" path="lastName" placeholder="Last Name" type="text" required="required"/>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">Gender</label>
					<div class="col-sm-10">
						<c:set var="userGender" value="${userForm.gender}"></c:set>
						<label class="radio-inline"><input type="radio" name="gender" value="male" ${(userGender eq "male" || empty userGender) ? ' checked' : ''}>Male</label>
						<label class="radio-inline"><input type="radio" name="gender" value="female"${userGender eq "female" ? ' checked' : ''}>Female</label>
						<label class="radio-inline"><input type="radio" name="gender" value="other"${userGender eq "other" ? ' checked' : ''}>Other</label>
					</div>
				</div>
				
				<div class="form-group">
					<form:label cssClass="control-label col-sm-2" path="role">Role</form:label>
					<div class="col-sm-10">
						<form:select path="role" title="Role" cssClass="form-control">
							<form:option value="Administrator" label="Administrator" />
							<form:option value="User" label="User" />
						</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<form:input path="userName" cssClass="form-control" placeholder="User Name" type="text" required="required"/>
				</div>
				
				<div class="form-group">
					<form:password path="password" cssClass="form-control" placeholder="Password" required="required" showPassword="true"/>
				</div>
				
				<div class="form-group">
					<form:input path="emailAddress" cssClass="form-control" placeholder="Eamil Address" type="email" required="required"/>
				</div>
				
				<div class="form-group">
					<form:input path="phone" cssClass="form-control" placeholder="Phone Number" type="phone" required="required"/>
				</div>
				
				<label>Address(es):</label>
				<div class="address-repeat">
					<table class="wrapper table">
						<thead>
							<tr>
								<td colspan="2"><span class="btn btn-primary add">Add</span></td>
							</tr>
						</thead>
						<tbody class="container">
							<tr class="template row">
								<td class="col-sm-6">
									
									<div class="form-group">
										<textarea placeholder="Street1" class="form-control" name="userAddresses[{{row-count-placeholder}}].street1" required="required"></textarea>
									</div>
									<div class="form-group">
										<input placeholder="Landmark" class="form-control" name="userAddresses[{{row-count-placeholder}}].landmark" type="text">
									</div>
									<div class="form-group">
										<input placeholder="City" class="form-control" name="userAddresses[{{row-count-placeholder}}].city" type="text" required="required">
									</div>
									<div class="form-group">
										<input placeholder="State" class="form-control" name="userAddresses[{{row-count-placeholder}}].state" type="text" required="required">
									</div>
									<div class="checkbox">
									    <label><input type="checkbox" name="userAddresses[{{row-count-placeholder}}].primaryAddress" > Is Primary Address</label>
									</div>
									
								</td>
								
								<td class="col-sm-6">
								
									<div class="form-group">
										<textarea placeholder="Street2" class="form-control" name="userAddresses[{{row-count-placeholder}}].street2" required="required"></textarea>
									</div>
									<div class="form-group invisible">
										<input placeholder="Address Id" class="form-control" name="userAddresses[{{row-count-placeholder}}].id" type="number" value="0">
									</div>
									<div class="form-group">
										<input placeholder="Pincode" class="form-control" name="userAddresses[{{row-count-placeholder}}].pincode" type="number" required="required">
									</div>
									<div class="form-group">
										<input placeholder="Country" class="form-control" name="userAddresses[{{row-count-placeholder}}].country" type="text" required="required">
									</div>
									<div class="text-right">
										<span class="btn btn-primary remove">Remove</span>
									</div>
									
								</td>
							</tr>
							<c:if test="${userForm.userAddresses eq null || empty userForm.userAddresses}">
							<tr class="row">
								<td class="col-sm-6">
									
									<div class="form-group">
										<textarea placeholder="Street1" class="form-control" name="userAddresses[0].street1" required="required"></textarea>
									</div>
									<div class="form-group">
										<input placeholder="Landmark" class="form-control" name="userAddresses[0].landmark" type="text">
									</div>
									<div class="form-group">
										<input placeholder="City" class="form-control" name="userAddresses[0].city" type="text" required="required">
									</div>
									<div class="form-group">
										<input placeholder="State" class="form-control" name="userAddresses[0].state" type="text" required="required">
									</div>
									<div class="checkbox">
									    <label><input type="checkbox" name="userAddresses[0].primaryAddress" checked> Is Primary Address</label>
									</div>
									
								</td>
								
								<td class="col-sm-6">
								
									<div class="form-group">
										<textarea placeholder="Street2" class="form-control" name="userAddresses[0].street2" required="required"></textarea>
									</div>
									<div class="form-group invisible">
										<input placeholder="Address Id" class="form-control" name="userAddresses[0].id" type="number" value="0">
									</div>
									<div class="form-group">
										<input placeholder="Pincode" class="form-control" name="userAddresses[0].pincode" type="number" required="required">
									</div>
									<div class="form-group">
										<input placeholder="Country" class="form-control" name="userAddresses[0].country" type="text" required="required">
									</div>
									<div class="text-right">
										<span class="btn btn-primary remove">Remove</span>
									</div>
									
								</td>
							</tr>
							</c:if>
							<c:forEach items="${userForm.userAddresses}" var="userAddress" varStatus="loop">
								<tr class="row">
									<td class="col-sm-6">
										
										<div class="form-group">
											<textarea placeholder="Street1" class="form-control" name="userAddresses[${loop.index}].street1" required="required">${userAddress.street1}</textarea>
										</div>
										<div class="form-group">
											<input placeholder="Landmark" class="form-control" name="userAddresses[${loop.index}].landmark" type="text" value="${userAddress.landmark}">
										</div>
										<div class="form-group">
											<input placeholder="City" class="form-control" name="userAddresses[${loop.index}].city" type="text" required="required" value="${userAddress.city}">
										</div>
										<div class="form-group">
											<input placeholder="State" class="form-control" name="userAddresses[${loop.index}].state" type="text" required="required" value="${userAddress.state}">
										</div>
										<div class="checkbox">
										    <label><input type="checkbox" name="userAddresses[${loop.index}].primaryAddress"${userAddress.primaryAddress ? ' checked' : ''}> Is Primary Address</label>
										</div>
										
									</td>
									
									<td class="col-sm-6">
									
										<div class="form-group">
											<textarea placeholder="Street2" class="form-control" name="userAddresses[${loop.index}].street2" required="required">${userAddress.street2}</textarea>
										</div>
										<div class="form-group invisible">
											<input placeholder="Address Id" class="form-control" name="userAddresses[${loop.index}].id" type="text" value="${userAddress.id}">
										</div>
										<div class="form-group">
											<input placeholder="Pincode" class="form-control" name="userAddresses[${loop.index}].pincode" type="number" required="required" value="${userAddress.pincode}">
										</div>
										<div class="form-group">
											<input placeholder="Country" class="form-control" name="userAddresses[${loop.index}].country" type="text" required="required" value="${userAddress.country}">
										</div>
										<div class="text-right">
											<a class='btn btn-primary' href="${deleteUserAddressURL}?userAddressId=${userAddress.id}&userId=${userForm.id}">Remove</a>
										</div>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<form:button name="submit" class="btn btn-primary btn-block">${empty sessionScope.currentUser ? "Create" : "Update"}</form:button>
				<a href="${empty sessionScope.currentUser ? userLoginURL : homeURL}" class="btn btn-default btn-block">Cancel</a>
			</form:form>
		</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>