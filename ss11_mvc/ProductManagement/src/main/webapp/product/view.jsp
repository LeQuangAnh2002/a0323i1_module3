<%--
  Created by IntelliJ IDEA.
  User: ANH
  Date: 10/1/2023
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
<H1>View Product </H1>
</p>
<p>
    <a href="/products?action=list">Back to list</a>
</p>
<form method="post" >


    <table border="1">
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" id="name" value="${product.productName}" readonly></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" id="email" value="${product.price}" readonly></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>
                <input type="text" name="desc" id="desc" value="${product.description}" readonly></td>

        </tr>

    </table>

</form>
</body>
</html>
