<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<script>
$(function() {
	$( "#datepicker" ).datepicker({
		defaultDate: "1/1/1960",
		changeMonth: true,
		changeYear: true,
		dateFormat: 'dd/mm/yy',
		yearRange: '1920:2012',
		showAnim: "slideDown"
		/* minDate: (new Date(1920, 1, 1)),
		maxDate: (new Date(2010, 1, 1)) */
	});
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
				<h5 class="title"><bean:message key="user.add"/></h5>
				<div class="form form-small">
					<html:errors/>
					<html:form styleClass="form-horizontal" action="${requestScope.action}">
					<html:hidden property="oid"/>
					<div class="control-group">
					    <label class="control-label" for="name"><bean:message key="user.name"/></label>
					    <div class="controls">
					    	<html:text styleId="name" property="name"/>
					    </div>
					</div>
					<div class="control-group">
					    <label class="control-label" for="surname"><bean:message key="user.surname"/></label>
					    <div class="controls">
					    	<html:text styleId="surname" property="surname"/>
					    </div>
					</div>
					<div class="control-group">
					    <label class="control-label" for="email"><bean:message key="user.email"/></label>
					    <div class="controls">
					    	<html:text styleId="email" property="email"/>
					    </div>
					</div>
					<div class="control-group">
					    <label class="control-label" for="password"><bean:message key="user.password"/></label>
					    <div class="controls">
					    	<html:password styleId="password" property="password"/>
					    </div>
					</div>
					<div class="control-group">
					    <label class="control-label" for="confirm_password"><bean:message key="user.confirm_password"/></label>
					    <div class="controls">
					    	<html:password styleId="confirm_password" property="confirm_password"/>
					    </div>
					</div>
					<%-- <div class="control-group">
					    <label class="control-label" for="confirm_password"><bean:message key="user.confirm_password"/></label>
					    <div class="controls">
					    	<html:text styleId="confirm_password" property="confirm_password"/>
					    </div>
					</div> --%>
					<%-- <div class="control-group">
					    <label class="control-label" for="created"><bean:message key="user.created"/></label>
					    <div class="controls">
							<html:text styleId="created_data" property="created_data" />
					    </div>
					</div>
					<div class="control-group">
					    <label class="control-label" for="date_of_birth"><bean:message key="user.date_of_birth"/></label>
					    <div class="controls">
							<html:text styleId="date_of_birth_data" property="date_of_birth_data"/>
					    </div>
					</div> --%>
					
					<div class="control-group">
					    <label class="control-label" for="date_of_birth"><bean:message key="user.date_of_birth"/></label>
						<div class="controls">
							<input type="text" name="date_of_birth" id="datepicker"/>
						</div>
					</div>
					
					<div class="control-group">
					    <label class="control-label" for="address"><bean:message key="user.address"/></label>
					    <div class="controls">
							<html:text styleId="address" property="address"/>
					    </div>
					</div>
					
					<div class="control-group">
					    <div class="controls">
					      <button class="btn" type="submit">Invia</button>
						</div>
					</div>
					</html:form>
				</div>
			</div>
		</div>
	</div>
</div>