<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h1>Product list page</h1>
    <table>
        <thead>
        <th>
            Name
        </th>
        <th>
            Producer
        </th>
        </thead>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td><a>${product.producer}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
