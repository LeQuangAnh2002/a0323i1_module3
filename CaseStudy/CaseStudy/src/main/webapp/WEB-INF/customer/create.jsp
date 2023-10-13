<%--
  Created by IntelliJ IDEA.
  User: ANH
  Date: 10/5/2023
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>
<style>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppisn', sans-serif;
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


    .wrapper{
        max-width: 500px;
        width: 100%;
        background: #fff;
        margin: 100px auto;
        padding: 30px;
        box-shadow: 1px 1px 2px rgba(0,0,0,0.25);
    }
    .wrapper .title{
        font-size:  25px;
        font-weight: 700;
        color: #fec107 ;
        text-transform: uppercase;
        text-align: center;
        margin-bottom: 25px;
    }
    .wrapper .form{
        width: 100%;
    }
    .wrapper .form .input_field{
        margin-bottom: 15px;
        display: flex;
        align-items: center;
    }
    .wrapper .form .input_field label{
        width: 150px;
        color: #757575;
        margin-right: 10px;
        font-size: 14px;
    }
    .wrapper .form .input_field .input{
        width: 100%;
        outline: none;
        border: 1px solid #d5dbd9;
        font-size: 15px;
        padding: 8px 10px;
        transition: all 0.3s ease;
    }
    .wrapper .form .input_field .btn{
        width: 100%;
        padding: 8px 10px;
        font-size: 15px;
        border: 0;
        background: #fec107;
        color: #fff;
        cursor: pointer;
        border-radius: 3px;
        outline: none;
    }
    .wrapper .form .input_field .customer_select{
        position: relative;
        width: 100%;
        height: 37px;

    }
    .wrapper .form .input_field .customer_select select{
        width: 100%;
        height: 100%;
        border: 1px solid #d5dbd9;
        padding: 8px 10px;
        border-radius: 3px;
        outline: none;
    }
    .wrapper .form .input_field .btn:hover{
        background: #ffd658;
    }
    .wrapper .form .input_field .input:focus,
    .wrapper .form .input_field .customer_select select:focus{
        border: 1px solid #fec107;
    }
    @media only screen and (max-width: 768px) {
        .wrapper .form .input_field{
            flex-direction: column;
            align-items: flex-start;
        }
        .wrapper .form .input_field label{
            margin-bottom: 5px;
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

        <div class="wrapper">
            <c:if test="${errorMessages != null}">
                <div style=" color: red; background-color: lightblue; padding: 20px;" >
                    <ul>
                        <c:forEach items="${errorMessages}" var="e">
                            <li>${e}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <div class="title">Form Input User </div>
            <form class="form" action="/customer" method="post">
                <div class="input_field">
                    <label>User Name</label>
                    <input type="text" name="name" class="input" value="${customer.customerName}" required>
                </div>
                <div class="input_field">
                    <label>Birthday</label>
                    <input type="date" name="birthday" class="input" value="${customer.customerBirthday}" required>

                </div>
                <div class="input_field">
                    <label>Gender</label>
                    <div class="customer_select">
                        <select name="gender" required>
                            <option value="" selected>Select</option>
                            <option value="male" >Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                </div>
                <div class="input_field">
                    <label>Card ID</label>
                    <input type="text" name="cardID" class="input" value="${customer.customerIDCard}" required>
                </div>
                <div class="input_field">
                    <label>User Phone</label>
                    <input type="text" name="phone" class="input" value="${customer.customerPhone}" required>
                </div>
                <div class="input_field">
                    <label>User Email</label>
                    <input type="text" name="email" class="input" value="${customer.customerEmail}" required>
                </div>
                <div class="input_field">
                    <label>User Address</label>
                    <input type="text" name="address" class="input" value="${customer.customerAddress}" required>
                </div>
                <div class="input_field">
                    <label>Type Customer</label>
                    <div class="customer_select">
                        <select name="typeCustomer" required>
                            <c:forEach items="${customerTypes}" var="type">
                                <c:if test="${customer.customerType.customerTypeID == type.customerTypeID}">
                                    <option value="${type.customerTypeID}" selected>${type.customerTypeName}</option>
                                </c:if>
                                <c:if test="${customer.customerType.customerTypeID != type.customerTypeID}">
                                    <option value="${type.customerTypeID}" >${type.customerTypeName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="input_field">
                    <input type="submit" value="Enter" class="btn">
                    <input type="hidden" name="action" value="create">
                </div>
            </form>
        </div>


    </div>
</div>
</body>
</html>
