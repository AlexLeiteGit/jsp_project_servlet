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
background-image: url(https://i.imgur.com/sTZuLJi.jpg);
background-repeat: no-repeat;
background-size: cover;
background-attachment: fixed;
}

.card{
position: absolute;
top: 30%;
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
top: 30%
margin-top: 10%;
margin-bottom: 5%;
width: 80%;
}

p{
top: 65%
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

						<div class="card">
                           <div class="card-header">
                          	<div class="card-block">
                           
                           <h4 class="sub-title">Cadastro de Usuários</h4>
                           
                           <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
                           
                           <input type="hidden" name="acao" value="">
                           
                           	<div class="form-group form-default form-static-label">
                           			<label class="float-label">Id:</label>
                                   	<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                   	<span class="form-bar"></span>
                               </div>
                               
                               <div class="form-group form-default form-static-label">
                               		<label class="float-label">Login:</label>
                                   	<input type="text" name="login" id="login" class="form-control" required="requered" autocomplete="off" value="${modelLogin.login}">
                                   	<span class="form-bar"></span>    
                               </div>
                               
                               <div class="form-group form-default form-static-label">
                                	<label class="float-label">Nome:</label>
                                   	<input type="text" name="nome" id="nome" class="form-control" required="requered" value="${modelLogin.nome}">
                                   	<span class="form-bar"></span> 
                               </div>
                               
                               <div class="form-group form-default form-static-label">
                               		<label class="float-label">Senha:</label>
                               		<input type="password" name="senha" id="senha" class="form-control" required="requered" autocomplete="off" value="${modelLogin.senha}">
                              		<span class="form-bar"></span> 	
                               </div>
                               
                               <div class="form-group form-default form-static-label">
                               		<label class="float-label">Email:</label>
                                   	<input type="email" name="email" id="email" name="email" id="email" class="form-control" required="requered" autocomplete="off" value="${modelLogin.email}">
                                   	<span class="form-bar"></span>  
                               </div>

								<div class='justify-center'>
								<hr>
								</div>

								<button class="btn btn-out-dashed waves-effect waves-light btn-primary btn-square" onclick="limparForm();">Novo</button>
   								<button class="btn btn-out-dashed waves-effect waves-light btn-success btn-square">Salvar</button>


                             </form>
                             
                             </div>
                             </div>
                             </div>

<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script type="text/javascript">

	<!-- Método de Limpar o formulário-->
	function limparForm(){
			var elementos = document.getElementById("formUser").elements; /*retorna os elementos html dentro do form*/
			
			for (var p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
</script>

</body>
</html>