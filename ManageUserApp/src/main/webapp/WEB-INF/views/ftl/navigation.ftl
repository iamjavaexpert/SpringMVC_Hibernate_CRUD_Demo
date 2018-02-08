<nav class="navbar navbar-inverse">
  <div class="container-fluid">
  	<div class="navbar-header">
  	  <a class="navbar-brand" href="<@spring.url "/home"/>">Home</a>
    </div>
  	<ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome, ${Session.currentUser.firstName}&nbsp;${Session.currentUser.lastName}&nbsp;<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<@spring.url "/logout"/>">Logout</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>