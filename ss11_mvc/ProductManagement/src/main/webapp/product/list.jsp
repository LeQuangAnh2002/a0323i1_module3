<%--
  Created by IntelliJ IDEA.
  User: ANH
  Date: 10/1/2023
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
  <style>
    table{
      border-collapse: collapse;
      border: 1px solid gray;
      font-family: Arial,sans-serif;
    }
    th,tr,td{
      padding: 15px;
      border: 1px solid black;
    }
    th{
      text-align: left;
    }
    a{
      text-decoration: none;

    }
  </style>
</head>
<body>
<p>
<H1>List Products</H1>
</p>
<p>
    <a href="/products?action=create">Create new product</a>
</p>
<table >
    <tr >
        <td colspan="7" >
            <form method="post" action="/products">
                <label>Product Name:</label>
                <input type="text" name="searchName">
                <input type="submit" value="Search">
                <input type="hidden" name="action" value="search">
            </form>
        </td>
    </tr>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>View</th>
        <th>Edit</th>
        <th>Update</th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td><a href="/products?action=view&id=${p.id}">VIEW</a></td>
            <td><a href="/products?action=update&id=${p.id}">EDIT</a></td>
            <td><a href="/products?action=delete&id=${p.id}">DELETE</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
