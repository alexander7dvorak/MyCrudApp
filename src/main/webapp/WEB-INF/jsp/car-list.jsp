<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ page import="java.util.ArrayList"%>
         <%@ page import="java.util.List"%>
         <%@ page import="com.example.WebApplication.Models.Car"%>

<html>

<head>
    <title>Car Detail Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: SteelBlue">
        <div>
            <a href="http://localhost:8085/manager/html" class="navbar-brand"> Car Detail Management Application </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/engine/" class="nav-link">Car Engines</a></li>
        </ul>
        <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/manufacturer/" class="nav-link">Manufacturers</a></li>
                </ul>
        <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/car/" class="nav-link">Cars</a></li>
                </ul>

    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Cars</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/car/new" class="btn btn-success">Add
                New Car</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Manufacturer Id</th>
                <th>Engine Id</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
                        <jsp:useBean id="listCars" scope="request" type="java.util.List"/>
            <c:forEach var="car" items="${listCars}">
                <tr>
                    <td>
                        <c:out value="${car.getId()}" />
                    </td>
                    <td>
                        <c:out value="${car.getManufacturerId()}" />
                    </td>
                    <td>
                        <c:out value="${car.getEngineId()}" />
                    </td>
                    <td>
                        <c:out value="${car.getName()}" />
                    </td>

                    <td>
                         <a href="<%=request.getContextPath()%>/car/edit?id=<c:out value='${car.getId()}' />">Edit</a>
                           &nbsp;&nbsp;&nbsp;&nbsp;
                         <a href="<%=request.getContextPath()%>/car/delete?id=<c:out value='${car.getId()}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>