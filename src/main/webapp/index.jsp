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

<form action="ServletLoginController" method="post">

<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

<input name="login" type="text">
<input name="senha" type="password">

<input type="submit" value="ENVIAR">

</form>

<h3> ${msg}</h3>

</body>
</html>