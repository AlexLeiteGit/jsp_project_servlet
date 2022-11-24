<%@page import="model.ModelLogin"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<!-- Inclusão do layout head.jsp -->
<jsp:include page="/layouts/head.jsp"></jsp:include>

  <body>
<!-- Pre-loader start -->

<jsp:include page="/layouts/theme-loader.jsp"></jsp:include>

  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="/layouts/navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  <jsp:include page="/layouts/navbarMyMenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="/layouts/page-header-start.jsp"></jsp:include>
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    
                                    <div class="card">
                                    <div class="card-header">
                                   	<div class="card-block">
                                    
                                    <h4 class="sub-title">Cadastro de Usuários</h4>
                                    
                                    <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
                                    
                                    <input type="hidden" name="acao" value="">
                                    
                                    	<div class="form-group form-default form-static-label">
                                            <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Id:</label>
                                        </div>

										<div class="form-group form-default form-static-label">
										 	<select class="form-control" name="perfil">
												
												<option disabled="disabled">[SELECIONE O PERFIL DESEJADO]</option>
												
												<option value="ADMIN" <%
												
												ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												
												if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}%>>ADMIN</option>
												
												<option value="SECRETARIA" <%
												
												modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												
												if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}%>>SECRETARIA</option>
												
												<option value="AUXILIAR" <%
												
												modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												
												if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}%>>AUXILIAR</option>
												
												<option value="ANALISTA" <%
												
												
												modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												
												if (modelLogin != null && modelLogin.getPerfil().equals("ANALISTA")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}%>>ANALISTA</option>
												
												<option value="ESPECIALISTA" <%					
												
												modelLogin = (ModelLogin) request.getAttribute("modelLogin");
												
												if (modelLogin != null && modelLogin.getPerfil().equals("ESPECIALISTA")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}%>>ESPECIALISTA</option>
												
												</select>
												
												<span class="form-bar"></span>
												<label class="float-label">Perfil:</label>
												</div>

												<div class="form-group form-default form-static-label">
                                            	<input type="text" name="login" id="login" class="form-control" required="requered" autocomplete="off" value="${modelLogin.login}">
                                           		<span class="form-bar"></span>
                                            	<label class="float-label">Login:</label>
                                        		</div>
                                        
                                        <div class="form-group form-default form-static-label">
                                            <input type="text" name="nome" id="nome" class="form-control" required="requered" value="${modelLogin.nome}">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Nome:</label>
                                        </div>
                                        
                                        <div class="form-group form-default form-static-label">
											<input type="radio" name="sexo" value="MASCULINO" <% 
													
											modelLogin = (ModelLogin) request.getAttribute("modelLogin");
													
											if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
											out.print(" ");
											out.print("checked=\"checked\"");
											out.print(" ");
											}%>>MASCULINO</>
											
											<input type="radio" name="sexo" value="FEMININO" <% 
													
											modelLogin = (ModelLogin) request.getAttribute("modelLogin");
													
											if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
											out.print(" ");
											out.print("checked=\"checked\"");
											out.print(" ");
											}%>>FEMININO</>
										</div>
                                        
                                        <div class="form-group form-default form-static-label">
                                        	<input type="password" name="senha" id="senha" class="form-control" required="requered" autocomplete="off" value="${modelLogin.senha}">
                                       		<span class="form-bar"></span>
                                          	<label class="float-label">Senha:</label>
                                        </div>
                                        
                                        <div class="form-group form-default form-static-label">
                                            <input type="email" name="email" id="email" name="email" id="email" class="form-control" required="requered" autocomplete="off" value="${modelLogin.email}">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Email:</label>
                                        </div>
                                        
                                        <button class="btn btn-out-dashed waves-effect waves-light btn-primary btn-square" onclick="limparForm();">Novo</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-success btn-square">Salvar</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-info btn-square" onclick="criarDelete()">Excluir com Post</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-warning btn-square" onclick="criarDeleteComAjax()">Excluir com Ajax</button>
            							<button type="button" class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square" data-toggle="modal" data-target="#ModalConsultaUsuario">Pesquisar</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square">Danger Button</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-inverse btn-square">Inverse Button</button>

                                      </form>
                                      
                                      </div>
                                      </div>
                                      </div>
                                      
                                      <span id="msg">${msg}</span>
                                      
                                      <div style="height: 300px; overflow: scroll">
										<table class="table" id="tabelaResultadosView">
										<thead>
										<tr>
											<th scope="col">#ID</th>
											<th scope="col">Nome</th>
											<th scope="col">E-mail</th>
											<th scope="col">Detalhe</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items='${listaModel}' var='ml'>
											<tr>
												<td><c:out value="${ml.id}"></c:out></td>
												<td><c:out value="${ml.nome}"></c:out></td>
												<td><c:out value="${ml.email}"></c:out></td>
												<td><a class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}" >Ver</a></td>
											</tr>
										</c:forEach>
										</tbody>
										</table>
											</div>
                                    
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Warning Section Starts -->
    
    <!-- Warning Section Ends -->
    
    <jsp:include page="/layouts/javascriptFile.jsp"></jsp:include>
    
    <!-- Começo do Modal -->
    
	<div class="modal fade" id="ModalConsultaUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        	<div class="input-group mb-3">
  					<input type="text" class="form-control" placeholder="Nome do Usuário" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
  					<div class="input-group-append">
    					<button class="btn btn-outline-secondary" type="button" onclick="buscarUsuario();">Buscar</button>
  					</div>
				</div>
				
				<div style="height: 300px; overflow: scroll">
				<table class="table" id="tabelaResultados">
  					<thead>
    					<tr>
      						<th scope="col">#ID</th>
     						<th scope="col">Nome</th>
      						<th scope="col">E-mail</th>
      						<th scope="col">Detalhe</th>
    					</tr>
  					</thead>
  				<tbody>

  				</tbody>
				</table>
				</div>
				
				<div>
				<span id="totalResultadosBusca"></span>
				</div>
				
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
     <!-- Final do Modal -->
    
<script type="text/javascript">

	<!-- Método de Limpar o formulário-->
	function limparForm(){
			var elementos = document.getElementById("formUser").elements; /*retorna os elementos html dentro do form*/
			
			for (var p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	
	<!-- Método de deletar o usuário com Post-->
	function criarDelete() {
		
		if(confirm('Deseja realmente excluir?')){
			var form = document.getElementById("formUser");
			form.method = 'get';
			document.getElementById("acao").value = 'deletar';
			form.submit();				
		}
	}
	
	<!-- Método de deletar o usuário com Ajax-->
	function criarDeleteComAjax(){
		
		if(confirm('Deseja realmente excluir?')){
			
			var urlAction = document.getElementById('formUser').action;
			var idUser = document.getElementById('id').value;
			
			$.ajax({
				
				method: "get",
				url : urlAction,
				data : "id=" + idUser + '&acao=deletarAjax',
				success: function (response){
					
				limparForm();
				document.getElementById('msg').textContent = response;	
					
				}
				
			}).fail(function(xhr, status, errorThrown){
				alert('Erro ao deletar usuário por ID: ' + xhr.responseText);
			});
		}
		
	}
	
	<!-- Método de Busca de usuários com Ajax através do Modal -->
	function buscarUsuario(){
		
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		if(nomeBusca != null && nomeBusca !='' && nomeBusca.trim() != ''){
		
		var urlAction = document.getElementById('formUser').action;

		$.ajax({
				
				method: "get",
				url : urlAction,
				data : "nomeBusca=" + nomeBusca + '&acao=buscarUsuarioAjax',
				success: function (response){
					
					var json = JSON.parse(response);
					
					$('#tabelaResultados > tbody > tr').remove();
					
					for(var p = 0 ; p < json.length ; p++){
						$('#tabelaResultados > tbody').append('<tr><td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td>' + json[p].email + '</td> <td><button onclick="buscarParaEditar(' +json[p].id+ ')" class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square">Detalhe</button></td></tr>');
					}	
					
					document.getElementById('totalResultadosBusca').textContent = 'Resultados: ' + json.length;
					
				}
				
			}).fail(function(xhr, status, errorThrown){
				alert('Erro ao buscar usuário pelo nome: ' + xhr.responseText);
			});
		}
		
	}
	
	<!-- Método de Carregar Usuário do Modal para Edição -->
	function buscarParaEditar(id){
		
		var urlAction = document.getElementById('formUser').action;
		
		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		
	}

</script>
    
</body>

</html>