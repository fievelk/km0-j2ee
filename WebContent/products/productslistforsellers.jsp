<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>

<style>
	table {margin-left:auto; margin-right:auto;}
	.index {background-color:cyan;}
	td {width:150px; text-align:center;}
</style>

</head>
<body>
<div style="text-align:center;border-style:solid; border-width:1px;margin-left:auto; margin-right:auto">viewActiveProducts(): Tutti i prodotti con active=1 e anche fuori date range</div>

<table>
    <tr class="index">
	    <td>Id</td>
	    <td>Name</td>
	    <td>Description</td>
	    <td>Price</td>
	    <td>Category</td>
	    <td>Seller Company</td>
   	    <td>update</td>
	    <td>Date IN</td>
	    <td>Date OUT</td>
    </tr>
	<c:forEach items="${requestScope.products}" var="product">
	<tr>
		<td>${product.oid}</td>
		<td>${product.name}</td>
		<td>${product.description}</td>
		<td>${product.price}</td>
		<td>${product.category.name}</td>
		<td>${product.seller.company}</td>
		<td><a href="${pageContext.request.contextPath}/products/update_start.do?oid=${product.oid}">UPDATAMI</a></td>
		<%-- <td>${requestScope.date_in}</td> --%>	
		<!-- <td>${product.date_in}></td> -->
		<td><fmt:formatDate pattern="dd-MM-yyyy" value="${product.date_out.time}" /></td>
		
	</tr>
	</c:forEach>
</table>

</body>
</html>