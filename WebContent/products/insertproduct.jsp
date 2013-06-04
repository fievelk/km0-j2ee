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

<%--

<div>
    <label for="date_in">Data IN</label>
    <div>
    	<html:text styleId="date_in" property="date_in"/>
    </div>
</div>

<div>
    <labelfor="date_out">Data OUT</label>
    <div>
    	<html:text styleId="date_out" property="date_out"/>
    </div>
</div>
--%>


<div>
    <label for="categoryId">Categoria</label>
    <div>
		<select id="categoryId" name="categoryId">
			<c:forEach items="${requestScope.titleKinds}" var="titleKind">
				<option value="${titleKind.id}">${titleKind.name}</option>
			</c:forEach>
		</select>
	</div>
</div>



<div class="control-group">
    <div class="controls">
      <button type="submit">SUBMITTAMI</button>
	</div>
</div>
</html:form>
</div>