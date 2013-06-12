<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
    
<div>
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

<div>
    <label for="from">Data di inizio</label>
	    <input type="text" id="from" name="date_in" value="${requestScope.date_in}"/>
    <label for="to">Data di fine</label>
	    <input type="text" id="to" name="date_out" value="${requestScope.date_out}"/>
</div>

<!-- fine DATEPICKER -->

<div class="control-group">
    <div class="controls">
      <button type="submit">SUBMITTAMI</button>
	</div>
</div>
</html:form>
</div>