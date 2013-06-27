<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var del = "${requestScope.delete}"; 
		if (del == "true" ) {
			$(":input[type='text']").each(function () { $(this).attr('readonly',true); });				
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
				<h5 class="title">
					<c:choose>
			      		<c:when test="${requestScope.delete eq 'true'}">
							<bean:message key="user.delete"/>
			      		</c:when>
			      		<c:otherwise>
			      			<bean:message key="user.edit"/>
			      		</c:otherwise>
			      	</c:choose>	
		      	</h5>
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
					    <label class="control-label" for="date_of_birth"><bean:message key="user.date_of_birth"/></label>
						<div class="controls">
							<html:text styleId="date_of_birth" property="date_of_birth"/>
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
					      <button type="submit" class="btn">
					      	<c:choose>
					      		<c:when test="${requestScope.delete eq 'true'}">
									<bean:message key="common.delete"/>
					      		</c:when>
					      		<c:otherwise>
					      			<bean:message key="common.submit"/>
					      		</c:otherwise>
					      	</c:choose>	
					      	
					      </button>
					    </div>
					</div>
					</html:form>
				</div>
			</div>
		</div>
	</div>
</div>