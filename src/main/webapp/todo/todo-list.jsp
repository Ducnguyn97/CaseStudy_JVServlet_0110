<%--
  Created by IntelliJ IDEA.
  User: DUKEI
  Date: 10/2/2025
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo-List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-primary shadow-sm px-4">
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/" class="navbar-brand d-flex align-items-center">
        <img src="${pageContext.request.contextPath}/img/logo_codegym.jpg"
             alt="Logo" width="30" height="30" class="mr-2 rounded-circle">
        <span>Todo App</span>
    </a>


    <ul class="navbar-nav ml-3">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/todos?action=list" class="nav-link">Todos</a>
        </li>
    </ul>


    <ul class="navbar-nav ml-auto">
        <c:if test="${not empty sessionScope.username}">
            <li class="nav-item d-flex align-items-center text-white mr-3">
                Welcome, <strong class="ml-1">${sessionScope.username}</strong>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a>
            </li>
        </c:if>
    </ul>
</nav>
</header>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of Todos</h3>
        <hr>
        <div class="container text-left">
            <a href="${pageContext.request.contextPath}/todos?action=create" class="btn btn-success">Add Todo</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Target Date</th>
                <th>Assign</th>
                <th>Todo Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="todos" items="${listTodo}">
                <tr>
                    <td>${todos.title}</td>
                    <td>${todos.targetDate}</td>
                    <td>${todos.assign}</td>
                    <td>${todos.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/todos?action=edit&id=${todos.id}"
                           class="btn btn-success">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/todos?action=delete&id=${todos.id}"
                           class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
