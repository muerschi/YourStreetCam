<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>     
    <title>User hinzufgueen</title>
  </head>
  <body>
	<form name="edit" action="edit" method="post">
		<table border="1">
			<tbody>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value=""></td>		
					</tr><tr>		
					<td>Password:</td>	
					<td><input type="text" name="password" value=""></td>
					</tr><tr>	
					<td>Role:</td>
					<td><input type="text" name="role" value=""></td>		
					</tr><tr>		
					<td colspan="3"><input type="submit" name="btnSave" value="Save"></td>
				</tr>
			</tbody>
		</table>
	</form>
  </body>
</html>
