<%@page import="model.ModelLogin"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<!-- Inclus?o do layout head.jsp -->
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
                                    
                                    <h4 class="sub-title">Cadastro de Usu?rios</h4>
                                    
                                    <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
                                    
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
											<input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required="required" value="${modelLogin.dataNascimento}"> 
											<span class="form-bar"></span>
											<label class="float-label">Nascimento:</label>
										</div>
                                        
                                        <div class="form-group form-default input-group mb-4">
											<div class="input-group-prepend">
												<c:if test="${modelLogin.fotouser != '' && modelLogin.fotouser != null}">
													<a href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
													<img alt="Imagem User" id="fotoembase64" src="${modelLogin.fotouser}" width="70px">
													</a>
												</c:if>
												
												<c:if test="${modelLogin.fotouser == '' || modelLogin.fotouser == null}">
													<img alt="Imagem User" id="fotoembase64" src="assets/images/usuario_padrao.png" width="70px">
												</c:if>
											</div>
											<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
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
                                        
                                        <div class="form-group form-default form-static-label">
											<input onblur="pesquisaCep();" type="text" name="cep" id="cep" class="form-control" required="required" autocomplete="off" value="${modelLogin.cep}"> 
											<span class="form-bar"></span> 
											<label class="float-label">CEP:</label>
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="logradouro" id="logradouro" class="form-control" required="required" autocomplete="off" value="${modelLogin.logradouro}"> 
											<span class="form-bar"></span> 
											<label class="float-label">Logradouro:</label>
										</div>
																						
										<div class="form-group form-default form-static-label">
											<input type="text" name="numero" id="numero" class="form-control" required="required" autocomplete="off" value="${modelLogin.numero}"> 
											<span class="form-bar"></span> 
											<label class="float-label">N?mero:</label>
										</div>
																						
										<div class="form-group form-default form-static-label">
											<input type="text" name="complemento" id="complemento" class="form-control" required="required" autocomplete="off" value="${modelLogin.complemento}"> 
											<span class="form-bar"></span> 
											<label class="float-label">Complemento:</label>
										</div>
																						
										<div class="form-group form-default form-static-label">
											<input type="text" name="bairro" id="bairro" class="form-control" required="required" autocomplete="off" value="${modelLogin.bairro}"> 
											<span class="form-bar"></span> 
											<label class="float-label">Bairro:</label>
										</div>
																						
										<div class="form-group form-default form-static-label">
											<input type="text" name="localidade" id="localidade" class="form-control" required="required" autocomplete="off" value="${modelLogin.localidade}"> 
											<span class="form-bar"></span> 
											<label class="float-label">Localidade:</label>
										</div>
																						
										<div class="form-group form-default form-static-label">
											<input type="text" name="uf" id="uf" class="form-control" required="required" autocomplete="off" value="${modelLogin.uf}"> 
											<span class="form-bar"></span> 
											<label class="float-label">UF:</label>
										</div>
										
										<div class="form-group form-default form-static-label">
											<input type="text" name="rendaMensal" id="rendaMensal" class="form-control" required="required" value="${modelLogin.rendaMensal}"> 
											<span class="form-bar"></span>
											<label class="float-label">Renda Mensal:</label>
										</div>
                                           
                                        <button class="btn btn-out-dashed waves-effect waves-light btn-primary btn-square" onclick="limparForm();">Novo</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-success btn-square">Salvar</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-info btn-square" onclick="criarDelete()">Excluir com Post</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-warning btn-square" onclick="criarDeleteComAjax()">Excluir com Ajax</button>
            							<button type="button" class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square" data-toggle="modal" data-target="#ModalConsultaUsuario">Pesquisar</button>
            							<c:if test="${modelLogin.id > 0}">
            							<a href="<%= request.getContextPath() %>/ServletTelefoneController?idUser=${modelLogin.id}" class="btn btn-info waves-effect waves-light">Telefone</a>
										</c:if>
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
										
										<nav aria-label="Page navigation example">
											<ul class="pagination">
												<%
													int totalPagina = (int) request.getAttribute("totalPaginas");
													for(int p = 0 ; p < totalPagina ; p++){
													String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);//neste caso, a multiplica??o por 5 se deve pela nossa escolha de limite de pagina??o.
													out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (p+1) + "</a></li>");
													}
								
												%>
											</ul>
										</nav> 
                                    
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
    
    <!-- Come?o do Modal -->
    
	<div class="modal fade" id="ModalConsultaUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usu?rio</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        	<div class="input-group mb-3">
  					<input type="text" class="form-control" placeholder="Nome do Usu?rio" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
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
				
				<nav aria-label="Page navigation example">
					<ul class="pagination" id="ulPaginacaoUserAjax">
					
					
					
					</ul>
				
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

	var dataNascimento = $("#dataNascimento").val();
	
	if(dataNascimento != null && dataNascimento !=''){
	
		var dateFormat = new Date(dataNascimento);
		
		$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR',{timeZone: 'UTC'}));
	
	}
	
	$("#nome").focus();

	<!-- M?todo de Formata??o de Data de Nascimento -->
	$( function() {
		  
		  $("#dataNascimento").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Ter?a','Quarta','Quinta','Sexta','S?bado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S?b','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Mar?o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Pr?ximo',
			    prevText: 'Anterior'
			});
	} );
	
	$("#numero").keypress(function(event) {
	    return /\d/.test(String.fromCharCode(event.keyCode));
	});
	    	
	$("#cep").keypress(function(event) {
	    return /\d/.test(String.fromCharCode(event.keyCode));
	});

	<!-- M?todo de Limpar o formul?rio-->
	function limparForm(){
			var elementos = document.getElementById("formUser").elements; /*retorna os elementos html dentro do form*/
			
			for (var p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	
	<!-- M?todo de deletar o usu?rio com Post-->
	function criarDelete() {
		
		if(confirm('Deseja realmente excluir?')){
			var form = document.getElementById("formUser");
			form.method = 'get';
			document.getElementById("acao").value = 'deletar';
			form.submit();				
		}
	}
	
	<!-- M?todo de deletar o usu?rio com Ajax-->
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
				alert('Erro ao deletar usu?rio por ID: ' + xhr.responseText);
			});
		}
		
	}
	
	<!-- M?todo de Busca de usu?rios com Ajax atrav?s do Modal -->
	function buscarUsuario(){
		
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		if(nomeBusca != null && nomeBusca !='' && nomeBusca.trim() != ''){
		
		var urlAction = document.getElementById('formUser').action;

		$.ajax({
				
				method: "get",
				url : urlAction,
				data : "nomeBusca=" + nomeBusca + '&acao=buscarUsuarioAjax',
				success: function (response, textStatus, xhr){
					
					var json = JSON.parse(response);
					
					$('#tabelaResultados > tbody > tr').remove();
					$("#ulPaginacaoUserAjax > li").remove();
					
					for(var p = 0 ; p < json.length ; p++){
						$('#tabelaResultados > tbody').append('<tr><td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td>' + json[p].email + '</td> <td><button onclick="buscarParaEditar(' +json[p].id+ ')" class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square">Detalhe</button></td></tr>');
					}	
					
					document.getElementById('totalResultadosBusca').textContent = 'Resultados: ' + json.length;
					
					var totalPaginas = xhr.getResponseHeader("totalPaginas");
					
					for (var p = 0 ; p < totalPaginas ; p++){
						var url = urlAction + "?nomeBusca="+ nomeBusca + "&acao=buscarUsuarioAjaxPage&pagina=" + (p * 5);/* este par?metros foram tirados da linha 487 acima*/
						$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p+1) + '</a></li>');
						
					}
					
				}
				
			}).fail(function(xhr, status, errorThrown){
				alert('Erro ao buscar usu?rio pelo nome: ' + xhr.responseText);
			});
		}
		
	}
	
	<!-- M?todo de Carregar Usu?rio do Modal para Edi??o -->
	function buscarParaEditar(id){
		
		var urlAction = document.getElementById('formUser').action;
		
		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		
	}
	
	<!-- M?todo para carregar foto do perfi?l do usu?rio-->
	function visualizarImg(fotoembase64, fileFoto){
		
		var preview = document.getElementById(fotoembase64);/*Campo IMG do HTML*/
		var fileUser = document.getElementById(fileFoto).files[0];
		var reader = new FileReader();
		
		reader.onloadend = function(){
			preview.src = reader.result;/*carrega a foto na tela*/
		};
		
		if(fileUser){
			reader.readAsDataURL(fileUser);/*preview da imagem*/
		} else {
			preview.src = "";
		}			
	}
	
	<!-- M?todo de retorno de consulta por CEP na utiliza??o de um webservice ViaCEP-->
	function pesquisaCep(){
		var cep = $("#cep").val();
		
		//Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

            if (!("erro" in dados)) {
                //Atualiza os campos com os valores da consulta.
                $("#cep").val(dados.cep);
                $("#logradouro").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#localidade").val(dados.localidade);
                $("#uf").val(dados.uf);
            }
            
        });	
	}
	
	<!-- M?todo de Pagina??o no Modal por Ajax-->
	function buscaUserPagAjax(url){
		
		var urlAction = document.getElementById('formUser').action;
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		$.ajax({

			method : "get",
			url : urlAction,
			data : url,
			success : function(response, textStatus, xhr) {

			var json = JSON.parse(response);

			$('#tabelaresultados > tbody > tr').remove();
			$("#ulPaginacaoUserAjax > li").remove();

			for (var p = 0; p < json.length; p++) {
			$('#tabelaresultados > tbody')
				.append(
				'<tr><td>'
						+ json[p].id
						+ '</td> <td>'
						+ json[p].nome
						+ '</td> <td>'
						+ json[p].email
						+ '</td> <td><button onclick="buscarParaEditar('
						+ json[p].id
						+ ')" type="button" class="btn btn-info btn-round waves-effect waves-light">Detalhe</button></td></tr>');
			}

			document.getElementById('totalResultadosBusca').textContent = 'Resultados: ' + json.length;
			
			var totalPaginas = xhr.getResponseHeader("totalPaginas");
			
			for (var p = 0 ; p < totalPaginas ; p++){
				
				var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUsuarioAjaxPage&pagina=' + (p * 5);/* este par?metros foram tirados da linha 487 acima*/
				
				$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p+1) + '</a></li>');
				
			}
			
		}

		}).fail(function(xhr, status, errorThrown) {
			alert('Erro ao buscar usu?rio pelo nome: ' + xhr.responseText);
		});
		
	}

</script>
    
</body>

</html>