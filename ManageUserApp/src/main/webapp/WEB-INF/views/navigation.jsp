<%@ include file="./init.jsp" %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
  	<div class="navbar-header">
  	  <c:url var="homeURL" value="/home"></c:url>
      <a class="navbar-brand" href="${homeURL}">Home</a>
    </div>
  	<ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome, ${sessionScope.currentUser.firstName}&nbsp;${sessionScope.currentUser.lastName}&nbsp;<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <c:url var="logoutURL" value="/logout"></c:url>
          <li><a href="${logoutURL}">Logout</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>