<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .table{
            width: 80%;
            margin: 0 auto;
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        }
        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #fff;


        }
        th, td {
            border: 1px solid black;
        }
        h1{
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Danh sách khách hàng </h1>
<br>
<div class="table">
    <table>
        <tr>
            <th>Tên</th>
            <th>Ngày Sinh</th>
            <th>Địa Chỉ</th>
            <th>Ảnh</th>
        </tr>
        <c:forEach items="${customers}" var="c">
            <tr>
                <td>${c.name}</td>
                <td><fmt:formatDate value="${c.birthDate}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                <td>${c.address}</td>
                <td><img src="${c.image}" width="80px" height="80px"></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>