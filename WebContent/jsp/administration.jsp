<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<%@ page import="model.Camera" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>    
    <title>User uebersicht</title>
  </head>  
  <body>
  	<h1>Nutzer Anzeige</h1>
  	<br>
	<table border="1">
  		<tbody>
	  		<tr>
	  			<td>id</td>
	  			<td>name</td>				
				<td>cameras</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>			
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:out value="${user.id}"/></td>					
					<td><c:out value="${user.name}"/></td>
					<td><c:out value="${user.cameraAll}"/></td>
					<td><a href="edit?action=edit&id=${user.id}">User Details</a></td>
					<td><a href="camera?action=camera&id=${user.name}">Kameras editieren</a></td>
					<td><a href="edit?action=delete&id=${user.id}">User löschen</a></td>
				</tr>
			</c:forEach>
  		</tbody>
  	</table>
  	<br>
  	<a href="edit?action=add">Neuen Nutzer hinzufügen</a>
  	 <br>
  	  <br>
  	   <br>
  	 <h1>Kamera Anzeige</h1>
  	 <br>
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
					<td><a href="camera?action=delete&id=${camera.id}">Kamera löschen</a></td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>
  	<br>
  	<a href="camera?action=add">Neue Kamera hinzufügen</a>
  </body>
</html>