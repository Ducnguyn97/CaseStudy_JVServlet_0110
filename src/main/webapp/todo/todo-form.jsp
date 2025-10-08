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
    <title>Form-Edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-primary shadow-sm px-4">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/" class="navbar-brand d-flex align-items-center">
            <img src="${pageContext.request.contextPath}/img/logo_codegym.jpg"
                 alt="Logo" width="30" height="30" class="mr-2 rounded-circle">
            <span>Todo App</span>
        </a>

        <!-- Menu trái -->
        <ul class="navbar-nav ml-3">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/todos?action=list" class="nav-link">Todos</a>
            </li>
        </ul>

        <!-- Menu phải -->
        <ul class="navbar-nav ml-auto">
            <!-- Nếu đã đăng nhập -->
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

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="todos?action=edit" method="post">
                </c:if>
                <c:if test="${todo == null}">
                <form action="todos?action=create" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${todo != null}">
                                Edit Todo
                            </c:if>
                            <c:if test="${todo == null}">
                                Add New Todo
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${todo != null}">
                        <input type="hidden" name="id" value="<c:out value = '${todo.id}'/>"/>
                    </c:if>
                    <fieldset class="form-group">
                        <label>Todo Tile</label>
                        <input type="text" value="<c:out value = '${todo.title}'/>"
                               class="form-control" name="title" required="required"
                               minlength="5"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Assign</label>
                        <input type="text" value="<c:out value='${todo.assign}'/>"
                               class="form-control" name="assign" required="required"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Todo Description</label>
                        <input type="text" value="<c:out value = '${todo.description}'/>"
                               class="form-control" name="description" required="required"
                               minlength="5"/>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Todo Status</label><select class="form-control" name="status">
                        <option value="false">In Progress</option>
                        <option value="true">Completed</option>
                    </select>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Todo Target Date</label>
                        <input type="date" value="<c:out value = '${todo.targetDate}'/>"
                               class="form-control" name="targetDate" required="required"/>
                    </fieldset>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
