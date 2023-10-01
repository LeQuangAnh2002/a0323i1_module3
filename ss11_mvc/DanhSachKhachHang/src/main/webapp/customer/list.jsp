<%--
  Created by IntelliJ IDEA.
  User: ANH
  Date: 9/27/2023
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>

  <style>
    *{
      padding: 0;
      margin: 0;
      box-sizing: border-box;
      font-family: 'Poppisn', sans-serif;
    }
    html{
      background: #f5f5f5;
    }
    .customers{
      min-height: 350px;
      margin: 50px;
      padding: 20px;
        background-color: #fff;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }
    .heading{
      display: flex;
      justify-content: space-between;
      align-items: center;
      color: #444;
    }
    .btn{
      color: #fff;
      text-decoration: none;
      padding: 5px 10px;
      background: coral;
      text-align: center;
    }
    table{
      margin-top: 10px;
      width: 100%;
      border-collapse: collapse;

    }
    table tr{
      border-bottom: 1px solid rgba(0,0,0,0.2);
    }table tbody tr:last-child{
       border-bottom: none;
     }
    table thead td{
      color: #333;
      font-weight: 600;
    }
    table tbody tr:hover{
      background: #333;
      color: #fff;
        cursor: pointer;
    }
    table td{
      padding: 9px 5px;
    }
    td i{
      padding: 7px;
      color: #fff;
      border-radius: 50px;
    }
    .customers table tbody td{
      white-space: nowrap;
    }

    td .fa-eye{
      background: #32bea6;
    }
    td .fa-edit{
      background: #63b4f4;
    }
    td .fa-trash-alt{
      background: #ed5564;
    }
    @media screen and (max-width: 768px){
      .customers td:nth-child(3){
        display: none;
      }
    }
  </style>
</head>
<body>
<div class="customers">
    <div class="heading">
        <h2>Customer</h2>
        <a href="/customers?action=create" class="btn">Create new customer</a>
    </div>
    <table class="appointment">
        <thead>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Action</td>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="c">
        <tr>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.address}</td>
            <td>
                <a href="#"><i class="far fa-eye"></i></a>
               <a href="/customers?action=edit&id=${id}"> <i class="far fa-edit"></i></a>
                <a href="/customers?action=delete&id=${id}"><i class="far fa-trash-alt"></i></a>
            </td>
        </tr>
        </c:forEach>

        </tbody>
    </table>

</div>
</body>
</html>
