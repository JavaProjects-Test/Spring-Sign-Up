<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="../serviceverification/${token}" method="post">

		Date Of Birth<br> <input type="text" name="dob"><br>
		Password<br> <input type="password" name="pass"><br>
		Confirm Password<br> 
		Image<br> <input type="file" name="img"><br> <input
			type="submit" value="submit" />
	</form>


</body>
</html>