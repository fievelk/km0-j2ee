<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align:center;border-style:solid; border-width:1px;margin-left:auto; margin-right:auto">PRODUCTLIST page</div>

<table>
    <tr>
	    <td>Id</td>
	    <td>Name</td>
	    <td>Description</td>
	    <td>Price</td>
	    <td>Category</td>
	    <td>Seller</td>
    </tr>
	<c:forEach items="${requestScope.products}" var="product">
	<tr>
		<td>${product.oid}</td>
		<td>${product.name}</td>
		<td>${product.description}</td>
		<td>${product.price}</td>
		<td>${product.category.oid}</td>
		<td>${product.seller.oid}</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>