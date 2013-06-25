<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var wpass = "${requestScope.wpass}";
		if (wpass == "true" ) {
			alert("Password vecchia errata!");
		}		
	});
</script>


<div class="hero-unit">
<html:errors/>
<html:form styleClass="form-horizontal" action="${requestScope.action}">
<html:hidden property="oid"/>
<html:hidden property="password"/>
<div class="control-group">
    <label class="control-label" for=oldPass><bean:message key="password.oldPass"/></label>
    <div class="controls">
    	<html:password styleId="oldPass" property="oldPass"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="newPass"><bean:message key="password.newPass"/></label>
    <div class="controls">
    	<html:password styleId="newPass" property="newPass"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="confirmPass"><bean:message key="password.confirmPass"/></label>
    <div class="controls">
    	<html:password styleId="confirmPass" property="confirmPass"/>
    </div>
</div>

<div class="control-group">
    <div class="controls">
      <button type="submit">Invia</button>
    </div>
</div>
</html:form>
</div>