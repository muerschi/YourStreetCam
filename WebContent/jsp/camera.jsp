<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.Camera" %>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="design.css" rel="stylesheet">

<title>KameraPage</title>
</head>
<body >
	<h1 style="font-family: Calibri" >Hier k�nnen Kameras hinzugef�gt werden</h1>
	<p style="font-family: Calibri">Der Administrator kann f�r den Nutzer neue Kameras hinzuf�gen oder l�schen</p>
  	<br>
  	<form name="cameraToUser" action="cameraToUser" method="post">
	  	<input type="hidden" name="name" value="${user.name}">
	  	<c:forEach var="camera" items="${cameras}">
	  		<input type="checkbox" name="cameraName" value="${camera.name}" checked>${camera.name} <br>
	  	</c:forEach>    
	  	<br>
		<input type="submit" name="btnCameraListUpdate" value="CameraListUpdate"></td>
	</form>
</body>
</html>