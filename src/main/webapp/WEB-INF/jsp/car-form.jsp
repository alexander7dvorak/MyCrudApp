<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <title>Car Detail Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color:SteelBlue">
        <div>
            <a href="http://localhost:8085/manager/html/" class="navbar-brand"> Car Detail Management Application </a>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${car != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${car == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${car != null}">
                                Edit Car
                            </c:if>
                            <c:if test="${car == null}">
                                Add New Car
                            </c:if>
                        </h2>
                    </caption>


                    <c:choose>
                        <c:when test="${car != null}">
                        <input type="hidden" name="id" value="<c:out value='${car.getId()}' />" />
                        </c:when>
                        <c:otherwise>
                        <input type="hidden" name="id" value="<c:out value='0' />" />
                        </c:otherwise>
                    </c:choose>


                    <fieldset class="form-group">
                        <label>Manufacturer</label>
                        <select type="number" value="<c:out value='${car.getManufacturerId()}' />" class="form-control" name="manufacturer_id" required="required">
                            <c:forEach items="${listManufacturers}" var="manufacturer">
                                    <option value="${manufacturer.getId()}">${manufacturer.getName()}</option>
                                </c:forEach>
                                </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Engine</label>
                        <select type="number" value="<c:out value='${car.getEngineId()}' />" class="form-control" name="engine_id" required="required">
                                                    <c:forEach items="${listEngines}" var="engine">
                                                        <option value="${engine.getId()}">${engine.getName()}</option>
                                                    </c:forEach>
                                                    </select>
                    </fieldset>


                    <fieldset class="form-group">
                        <label>Name</label> <input type="text" value="<c:out value='${car.getName()}' />" class="form-control" name="name" required="required" minlength="1">
                    </fieldset>


                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>