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
                                    <form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController" method="post">
                                    	<div class="form-group form-default form-static-label">
                                            <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Id:</label>
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
                                        	<input type="password" name="senha" id="senha" class="form-control" required="requered" autocomplete="off" value="${modelLogin.senha}">
                                       		<span class="form-bar"></span>
                                          	<label class="float-label">Senha:</label>
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <input type="email" name="email" id="email" name="email" id="email" class="form-control" required="requered" autocomplete="off" value="${modelLogin.email}">
                                            <span class="form-bar"></span>
                                            <label class="float-label">Email:</label>
                                        </div>
                                        
                                        <button class="btn btn-out-dashed waves-effect waves-light btn-primary btn-square">Novo</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-success btn-square">Salvar</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-info btn-square">Info Button</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-warning btn-square">Warning Button</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-danger btn-square">Danger Button</button>
            							<button class="btn btn-out-dashed waves-effect waves-light btn-inverse btn-square">Inverse Button</button>

                                      </form>
                                      </div>
                                      </div>
                                      </div>
                                      
                                      <span>${msg}</span>
                                    
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
    
    <h4>Mas nada será para agora neste momento.</h4>
    
    <!-- Warning Section Ends -->
    
    <jsp:include page="/layouts/javascriptFile.jsp"></jsp:include>
    
</body>

</html>