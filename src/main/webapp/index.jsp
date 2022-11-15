<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP Project Servlet</title>
</head>
<body>

<h1>TESTE DE CONTEÚDO</h1>

<% out.println("OUTRO TESTE DE CONTEÚDO"); %>

<form action="receber-nome.jsp">

<input name="nome">
<input name="idade">

<input type="submit" value="ENVIAR">

</form>

</body>
</html>