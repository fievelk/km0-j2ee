<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var wpass = "${requestScope.wrongpass}";
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
    <label class="control-label" for=oldPass><bean:message key="password.old"/></label>
    <div class="controls">
    	<html:text styleId="oldPass" property="oldPass"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="newPass"><bean:message key="password.new"/></label>
    <div class="controls">
    	<html:text styleId="newPass" property="newPass"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="confirmPass"><bean:message key="password.confirm"/></label>
    <div class="controls">
    	<html:text styleId="confirmPass" property="confirmPass"/>
    </div>
</div>

<div class="control-group">
    <div class="controls">
      <button type="submit" class="btn btn-primary">Invia</button>
    </div>
</div>
</html:form>
</div>