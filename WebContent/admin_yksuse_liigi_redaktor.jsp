<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin üksuse liigi redaktor</title>
</head>
<body>

<form method="POST">
<input type="hidden" name="id" value="${ yksuseLiik.riigiAdminYksuseLiikId }">
Kood: <input name="kood" value="${ yksuseLiik.kood }"><br/>
Nimetus: <input name="nimetus" value="${ yksuseLiik.nimetus }"><br/>
Kommentaar: <textarea name="kommentaar" cols="30" rows="5">${ yksuseLiik.kommentaar }</textarea><br/>
Allub: <select name="allub"></select>
</form>

</body>
</html>