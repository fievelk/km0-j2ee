<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<tiles:importAttribute scope="request"/>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="utf-8">
    <title><bean:message key="common.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- CSS e JS della cartella resuources. Manca da importare Bootstrap -->
    
    <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.min.css"> --%>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/css/ui-lightness/jquery-ui-1.10.2.custom.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/css/jquery.dataTables_themeroller.css" />

    <%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/bootstrap/img/favicon.ico"> --%>
    
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery-ui/js/jquery-ui-1.10.2.custom.min.js"></script>
    
    <%-- <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/resources/datatables/js/my.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>
    
    
<!-- Collegamenti per il jQuery datepicker -->    
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->

  </head>

  <body>
  
	<tiles:insert attribute="menu"/>
    <div>
      <tiles:insert attribute="body"/>
      <hr>
      <tiles:insert attribute="footer"/>
    </div>
  </body>
</html>