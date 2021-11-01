<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         <%@ page import="java.util.ArrayList"%>
         <%@ page import="java.util.List"%>
         <%@ page import="com.example.WebApplication.Models.CarEngine"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
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
        <h3 class="text-center">List of Manufacturers</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/manufacturer/new" class="btn btn-success">Add
                New Manufacturer</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <jsp:useBean id="listManufacturers" scope="request" type="java.util.List"/>
            <c:forEach var="manufacturer" items="${listManufacturers}">
                <tr>
                    <td>
                        <c:out value="${manufacturer.getId()}" />
                    </td>
                    <td>
                        <c:out value="${manufacturer.getName()}" />
                    </td>
                    <td><a href="<%=request.getContextPath()%>/manufacturer/edit?id=<c:out value='${manufacturer.getId()}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/manufacturer/delete?id=<c:out value='${manufacturer.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>