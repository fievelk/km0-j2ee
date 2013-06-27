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


<div class="items">
	<div class="container">
		<div class="row">

	    	<div class="span3 side-menu">
	
				<!-- Sidebar navigation -->
				<h5 class="title">Menu</h5>
				<!-- Sidebar navigation -->
				  <nav>
				    <ul id="navi">
				      <li><a href="myaccount.html">Gestione Ordini</a></li>
				      <li><a href="wish-list.html">Storico Ordini</a></li>
				      <li><a href="order-history.html">Gestione Utenti</a></li>
				      <li><a href="edit-profile.html">Gestione Venditori</a></li>
				    </ul>
				  </nav>
			</div>
			
			<!-- Main content -->
			
			<div class="span9">
				<h5 class="title"><bean:message key="password.edit"/></h5>
				<div class="form form-small">
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
			</div>
		</div>
	</div>
</div>
