<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.Camera" %>
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
  	<table border="1">
  		<tbody>
	  		<tr>
	  			<td>id</td>
	  			<td>name</td>				
				<td>path</td>
				<td>&nbsp;</td>
			</tr>			
			<c:forEach var="camera" items="${cameras}">
				<tr>
					<td><c:out value="${camera.id}"/></td>					
					<td><c:out value="${camera.name}"/></td>
					<td><c:out value="${camera.path}"/></td>
					<td><a href="camera?action=delete&id=${camera.id}">Kamera l�schen</a></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  	<form name="camera" action="cameraupdate" method="post">
		<input type="submit" name="btnCameraListUpdate" value="CameraListUpdate"></td>
	</form>
</body>
</html>