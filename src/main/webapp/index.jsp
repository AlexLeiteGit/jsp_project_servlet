<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>JSP Project Servlet</title>

<style type="text/css">

body{
background-image: url(https://i.imgur.com/3yUWKRl.jpg);
background-repeat: no-repeat;
background-size: cover;
background-attachment: fixed;
}

form{
position: absolute;
top: 42%;
left: 33%;
right: 33%
}

h1{
position: absolute;
top: 25%;
left: 37%;
right: 27%;
}

h2{
position: absolute;
top: 30%;
left: 40%;
right: 27%;
font-size: 20px;
color: red;
}

h3{
position: absolute;
top: 35%;
left: 39%;
right: 27%;
font-size: 20px;
color: red;
}

.mgs{
position: absolute;
top: 70%;
left: 37%;
right: 27%;
font-size: 60px;
color: red;
}

.justify-center{
  display: flex;
  justify-content: center;
}

hr{
top: 60%
margin-top: 10%;
margin-bottom: 10%;
width: 80%;
}

p{
color: #003A4D;
font-size: 14pt;
text-align: center;
}

a{
color: #372318;
font-weight: bold;
text-decoration: none;
transition: all .4s ease-out;
}

a:hover{
  color: #BEA56F;
}

.fa-eye{
  position: absolute;
  top: 16px;
  right: 10px;
  cursor: pointer;
  color: #BB6745;
}

</style>

</head>
<body>

<h1>JSP Project with Servlet</h1>

<form action="ServletLoginController" method="post" class="row g-3 needs-validation" novalidate">

<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

<div class="col-md-6">
<label>LOGIN: </label>
<input class="form-control" name="login" type="text" required>
<div class="valid-feedback">Looks good!</div>
<div class="invalid-feedback">Do it Again!</div>
</div>
		
<div class="col-md-6">
<label>SENHA: </label>
<input class="form-control" name="senha" type="password" required>
<div class="valid-feedback">Looks good!</div>
<div class="invalid-feedback">Do it Again!</div>
</div>

<input class="btn btn-primary" type="submit" value="ENVIAR">

<div class='justify-center'>
<hr>
</div>
    
<p> Não tem uma conta?
<a href='main/cadastro.jsp'> Cadastre-se </a>
</p>

</form>


<h3> ${msg}</h3>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
	'use strict'

	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms).forEach(function(form) {
		form.addEventListener('submit', function(event) {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}

			form.classList.add('was-validated')
		}, false)
			})
		})()
</script>

</body>
</html>