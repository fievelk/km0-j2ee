<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<tiles:importAttribute scope="request"/>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><bean:message key="common.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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