<%--
  Created by IntelliJ IDEA.
  User: ANH
  Date: 10/4/2023
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>
  <style>
    *{
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Poppisn', sans-serif;
    }
    body{
      overflow-x: hidden;


    }
    .container{
      width: 100%;
      position: relative;
    }
    .sidebar{
      position: fixed;
      width: 300px;
      height: 100vh;
      background-color: #4478bf;
      overflow-x: hidden;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
      z-index: 2;
      transition: 0.3s ease;
    }
    .sidebar ul li {
      width: 100%;
      list-style-type: none;
      margin-bottom: 15px;

    }
    .sidebar ul li:hover{
      background: #444;
    }
    .sidebar ul li:first-child{
      line-height: 60px;
      color: #fff;
      font-weight: 700;
      margin-bottom: 20px;
      border-bottom: 1px solid #fff;
    }
    .sidebar ul li:first-child:hover{
      background: none;
    }
    .sidebar ul li a{
      width: 100%;
      text-decoration: none;
      color: #fff;
      line-height: 60px;
      display: flex;
      align-items: center;
    }
    .sidebar ul li a i{
      min-width: 60px;
      font-size: 24px;
      text-align: center;
    }
    .sidebar ul li a .title{
      padding: 0 10px;
      font-size: 20px;
      opacity: 1;
    }
    .main{
      position: absolute;
      width: calc(100% - 300px);
      min-height: 100vh;
      left: 300px;
      background: #f5f5f5;
    }
    .main .top-bar{
      position: fixed;
      width: calc(100% - 300px);
      height: 60px;
      background: #fff;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
      display: grid;
      color: #444;
      grid-template-columns: 2fr 1fr;
      padding: 0 20px;
      align-items: center;
      z-index: 1;
    }
    .main .top-bar .search{
      position: relative;
      max-width: 400px;

    }
    .main .top-bar .search input{
      width: 100%;
      height: 40px;
      padding: 0 10px;
      font-size: 16px;
      background:#f5f5f5;
      border: none;
      outline: none;

    }
    .main .top-bar .search i{
      position: absolute;
      top: 10px;
      right: 12px;
      font-size: 18px;
      cursor: pointer;
    }
    .main .top-bar .user i{
      float: right;
      font-size: 22px;
      font-weight: 700;
      padding-right: 20px;

    }
    .tables{
      width: 95%;
      padding: 35px 40px;
      margin: 100px auto;
      background: #fff;
      overflow-x: auto;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }
    .tables .heading{
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      color: #444;
    }
    .tables .heading h2{
      font-size: 32px;
      font-weight: 600;
    }
    .btn{
      color: #fff;
      background: coral;
      padding: 8px 10px;
      text-decoration: none;
      text-align: center;
      line-height: 22px;
    }
    .tables table{
      width: 100%;
      border-collapse: collapse;
    }
    .tables table tr{
      border-bottom: 1px solid rgba(0,0,0,0.2);

    }
    .tables table tbody tr:last-child{
      border-bottom: none;
    }
    table thead td{
      color: #333;
      font-weight: 600;
      text-transform: uppercase;
    }
    table tbody tr:hover{
      background: #333;
      color: #fff;
      cursor: pointer;
    }
    table  td{
      padding: 10px 5px;
      white-space: nowrap;
    }
    table td i{
      color: #fff;
      padding: 7px;
      border-radius: 50px;
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
      .container td:nth-child(3),.container td:nth-child(4){
        display: none;
      }
    }
    @media only screen and (max-width: 1090px) {
      .sidebar{
        width: 60px;

      }
      .main{
        width: calc(100% - 60px);
        left: 60px;

      }
      .main .top-bar{
        width: calc(100% - 60px);

      }
      .sidebar ul li a .title{
        opacity: 0;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <div class="sidebar">
    <ul>
      <li>
        <a href="#">
          <i class="fas fa-clinic-medical"></i>
          <div class="title">Dashboard</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-users"></i>
          <div class="title">Employee</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-user"></i>
          <div class="title">Customer</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-business-time"></i>
          <div class="title">Service</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-file-contract"></i>
          <div class="title">Contract</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-cog"></i>
          <div class="title">Settings</div>
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-question"></i>
          <div class="title">Help</div>
        </a>
      </li>
    </ul>
  </div>
  <div class="main">
    <div class="top-bar">
      <div class="search">
        <input type="text" name="search" id="search" placeholder="Search here">
        <label for="search"><i class="fas fa-search"></i></label>
      </div>
      <div class="user">
        <i class="fas fa-user"></i>
      </div>
    </div>
    <div class="tables">
      <div class="heading">
        <h2>User List</h2>
        <a href="#" class="btn">Create user</a>
      </div>
      <div class="content">
        <table class="users">
          <thead>
          <td>ID</td>
          <td> Name</td>
          <td>Birthday</td>
          <td>Gender</td>
          <td>Id Card</td>
          <td>Phone</td>
          <td>Email</td>
          <td>Address</td>
          <td>Customer Type</td>
          <td>Action</td>
          </thead>
          <tbody>


          <c:forEach items="${listCustomers}" var="c">
            <tr>
              <td>${c.customerID}</td>
              <td>${c.customerName}</td>
              <td>${c.customerBirthday}</td>
              <td>${c.customerGender}</td>
              <td>${c.customerIDCard}</td>
              <td>${c.customerPhone}</td>
              <td>${c.customerEmail}</td>
              <td>${c.customerAddress}</td>
              <td>${c.customerType.customerTypeName}</td>
              <td>
                <a href="/customer?action=detail&id=${c.customerID}"><i class="far fa-eye"></i></a>
                <a href="/customer?action=edit&id=${c.customerID}"> <i class="far fa-edit"></i></a>
                <a href="/customer?action=delete&id=${c.customerID}"><i class="far fa-trash-alt"></i></a>



              </td>
            </tr>
          </c:forEach>


          </tbody>
        </table>

      </div>
    </div>

  </div>
</div>
</body>
</html>
