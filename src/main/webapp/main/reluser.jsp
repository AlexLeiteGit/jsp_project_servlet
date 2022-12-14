<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                    <div class="page-body">
                                        <div class="row">
										<div class="col-sm-12">
										<div class="card">
										<div class="card-block">
												<h4 class="sub-title">Relat?rio de Usu?rios</h4>
												
												<div class="row">
										<div class="col-sm-12">
										<div class="card">
										<div class="card-block">
												<h4 class="sub-title">Relat?rio de Usu?rios</h4>
												
												<form class="form-material" action="<%= request.getContextPath()%>/ServletUsuarioController?acao=imprimirRelatorioUser" method="get" id="formUser">

														<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao" value="imprimirRelatorioUser">

															<div class="form-row align-items-center">
																
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataInicial">Data Inicial</label>
																	<input value="${dataInicial}" type="text" class="form-control" id="dataInicial" name="dataInicial">
																</div>
																
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataFinal">Data Final</label>
																	<input value="${dataFinal}" type="text" class="form-control" id="dataFinal" name="dataFinal">
																</div>

																<div class="col-auto my-1">
																	<button type="button" onclick="mostrarHtml();" class="btn btn-primary">Mostrar Relat?rio</button>
																	<button type="button" onclick="imprimirPdf();" class="btn btn-primary">Imprimir Relat?rio</button>
																	<button type="button" onclick="imprimirExcel();" class="btn btn-primary">Imprimir em Excel</button>
																</div>
															</div>

												</form>
	
														<div style="height: 300px; overflow: scroll">
															<table class="table" id="tabelaresultadosview">
																<thead>
																	<tr>
																		<th scope="col">#ID</th>
																		<th scope="col">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items='${listaUser}' var='ml'>
																	<tr>
																	<td><c:out value="${ml.id}"></c:out></td>
																	<td><c:out value="${ml.nome}"></c:out></td>				
																	</tr>
																	<c:forEach items="${ml.telefones}" var="fone">
																	</tr>
																	<td/>				
																	<td><c:out value="${fone.numero}"></c:out></td>
																	</tr>
																	</c:forEach>
																	</c:forEach>
																</tbody>
															</table>
														</div>
	
													</div>
										</div>
										</div>	
</div>
										</div>
										</div>
										</div>	
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
    
    <script type="text/javascript">

	$( function() {
		  
		  $("#dataInicial").datepicker({
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

	$( function() {
		  
		  $("#dataFinal").datepicker({
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
	
	function mostrarHtml() {
		
		document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioUser';
		$("#formUser").submit();
		
	}
	
	function imprimirPdf() {
		
		document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioPDF';
		$("#formUser").submit();
		return false;
		
	}

	function imprimirExcel() {
		
		document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioExcel';
		$("#formUser").submit();
		return false;
		
	}

</script>
    
</body>
</html>