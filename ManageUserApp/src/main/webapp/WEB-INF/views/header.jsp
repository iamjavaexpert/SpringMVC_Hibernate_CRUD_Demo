<%@ include file="./init.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>${pageTitle}</title>
	
	<c:url value="/resources/css/jquery-ui.min.css" var="jquery_ui_css"></c:url>
	<c:url value="/resources/css/bootstrap.min.css" var="bootstrap_css"></c:url>
	<c:url value="/resources/css/datatables.min.css" var="datatables_css"></c:url>
	<c:url value="/resources/css/main.css" var="main_css"></c:url>
	
	<link rel="stylesheet" type="text/css" href="${jquery_ui_css}">
	<link rel="stylesheet" type="text/css" href="${bootstrap_css}">
	<link rel="stylesheet" type="text/css" href="${datatables_css}">
	<link rel="stylesheet" type="text/css" href="${main_css}">
</head>
	