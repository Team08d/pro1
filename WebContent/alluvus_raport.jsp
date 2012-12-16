<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administratiivüksuste alluvusraport</title>
</head>
<body>
	<form method="POST">
		Kuupäev <input name="date" value="">
		Liik 
		<select name=liik>
		
			<c:forEach var="liik" items="${liigid}">
				<option value="${liik.riigiAdminYksuseLiikId}">${liik.nimetus}</option>
			</c:forEach>
			
      </select>
      <input type="submit" value="Värskenda">
   
   </form>
</body>
</html>