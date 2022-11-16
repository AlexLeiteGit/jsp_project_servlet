<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<!-- Inclus�o do layout head.jsp -->
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
                                            <!-- task, page, download counter  start -->
                                            
                                            <div><h1>Conte�do das P�ginas do Systema.</h1></div>
                                            
                                            <!-- task, page, download counter  end -->
    
                                            <!--  sale analytics start -->
                                            
                                            <div><h2>Iremos implementar depois uma funcionalidade principal.</h2></div>                                        

                                            <!--  sale analytics end -->
    
                                            <!--  project and team member start -->
                                            
                                            <div><h3>Provavelmente ser� o comsumo de uma API gratu�ta.</h3></div>
                                            
                                            <!--  project and team member end -->
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
    <!-- Warning Section Starts -->
    
    <h4>Mas nada ser� para agora neste momento.</h4>
    
    <!-- Warning Section Ends -->
    
    <jsp:include page="/layouts/javascriptFile.jsp"></jsp:include>
    
</body>

</html>