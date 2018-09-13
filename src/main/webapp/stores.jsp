<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stores</title>
</head>
<body>
    <h1>Store list page</h1>
    <table>
        <thead>
            <th>
                Name
            </th>
            <th>
                Address
            </th>
        </thead>
        <c:forEach items="${stores}" var="store">
            <tr>
                <td><a href="/products?storeId=${store.id}">${store.name}</a></td>
                <td><a>${store.address}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
