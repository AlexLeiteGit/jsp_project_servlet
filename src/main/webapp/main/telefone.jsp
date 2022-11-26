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
                                    <div class="page-body">
                                        <div class="row">
                                            
                                        <div class="col-sm-12">
										<div class="card">
										<div class="card-block">
										
										<h4 class="sub-title">Cadastro de Telefone</h4>
										
										<form class="form-material" action="<%= request.getContextPath()%>/ServletTelefoneController" method="post" id="formFone">
											
											<div class="form-group form-default form-static-label">
												<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLoginTelefone.id}"> 
												<span class="form-bar"></span> 
												<label class="float-label">ID User:</label>
											</div>

											<div class="form-group form-default form-static-label">
												<input readonly="readonly" type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLoginTelefone.nome}"> 
												<span class="form-bar"></span> 
												<label class="float-label">Nome:</label>
											</div>
														
											<div class="form-group form-default form-static-label">
												<input type="text" name="numero" id="numero" class="form-control" required="required"> 
												<span class="form-bar"></span> 
												<label class="float-label">Número:</label>
											</div>
														
											<button class="btn btn-success waves-effect waves-light">Salvar</button>
													
										</form>
										
										</div>
										</div>
                                            
                                        </div>
                                    </div>
                                    <span id="msg">${msg}</span>
                                    
                                    <div style="height: 300px; overflow: scroll">
											<table class="table" id="tabelaresultadosview">
												<thead>
													<tr>
														<th scope="col">#ID</th>
														<th scope="col">Número</th>
														<th scope="col">Excluir</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${listaModelTelefones}' var='f'>
														<tr>
															<td><c:out value="${f.id}"></c:out></td>
															<td><c:out value="${f.numero}"></c:out></td>
															<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletTelefoneController?acao=excluir&id=${f.id}&userpai=${modelLoginTelefone.id}">Excluir</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
                                    
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
<jsp:include page="/layouts/javascriptFile.jsp"></jsp:include>
    
</body>

</html>