<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var del = "${requestScope.delete}"; 
		if (del == "true" ) {
			$(":input[type='text'],select[id='categoryId']").each(function () { $(this).attr('readonly',true); });				
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
	<h5 class="title">insert or modify product </h5>
	
	<div class="form form-small">
	
	  <html:form action="${requestScope.action}">
		<html:hidden property="oid"/>
		<div>
		    <label for="name">Nome</label>
		    <div>
		    	<html:text styleId="name" property="name"/>
		    </div>
		</div>
		
		<div>
		    <label for="description">Descrizione</label>
		    <div>
		    	<html:text styleId="description" property="description"/>
		    </div>
		</div>
		
		
		<div>
		    <label for="price">Prezzo</label>
		    <div>
		    	<html:text styleId="price" property="price"/>
		    </div>
		</div>
		
		<div>
		    <label for="CategoryId">Categoria</label>
		    <div>
				<html:select styleId="categoryId" property="categoryId">
					<html:options collection="categories" property="oid" labelProperty="name"/>
				</html:select>
		    </div>
		</div>
		
		
		<%-- <div>
		    <label for="categoryId">Categoria</label>
		    <div>
				<select id="categoryId" name="categoryId">
					<c:forEach items="${requestScope.categories}" var="category">
						<option value="${category.oid}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		
		<!-- inizio DATEPICKER from-to -->
		
		 <script>
		$(function() {
			$( "#from" ).datepicker({
				defaultDate: "+1w",
				changeMonth: true,
				dateFormat: 'dd/mm/yy',
				numberOfMonths: 3,
				onClose: function( selectedDate ) {
					$( "#to" ).datepicker( "option", "minDate", selectedDate );
				}
			});
			$( "#to" ).datepicker({
				defaultDate: "+1w",
				changeMonth: true,
				dateFormat: 'dd/mm/yy',
				numberOfMonths: 3,
				onClose: function( selectedDate ) {
					$( "#from" ).datepicker( "option", "maxDate", selectedDate );
				}
			});
		});
		</script>
		
<%-- 		<div>
		    <label for="from">Data di inizio form</label>
		    <div>
		    	<html:text styleId="from" property="date_in"/>
		    </div>
		</div>
		
		<div>
		    <label for="to">Data di fine form</label>
		    <div>
		    	<html:text styleId="to" property="date_out"/>
		    </div>
		</div> --%>
		
 		<div>
		    <label for="from">Data di inizio</label>
			    <input type="text" id="from" name="date_in" value="${requestScope.date_in}"/>
		    <label for="to">Data di fine</label>
			    <input type="text" id="to" name="date_out" value="${requestScope.date_out}"/>
		</div>
		
		<!-- fine DATEPICKER -->
		
		<div class="control-group">
		    <div class="controls">
		      <button type="submit">
		      	<c:choose>
		      		<c:when test="${requestScope.delete eq 'true'}">
						DELETE
		      		</c:when>
		      		<c:otherwise>
		      			SUBMIT
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