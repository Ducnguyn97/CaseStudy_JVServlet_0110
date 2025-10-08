<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 01/10/2025
  Time: 11:01 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="main-content">
    <div class="container">
        <h1>Register Form</h1>
        <form action="<%=request.getContextPath()%>/register" method="post">

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstname" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastname" class="form-control" required>
            </div>

            <div class="form-group">
                <label>User Name:</label>
                <input type="text" name="username" class="form-control" required>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-success">Sign up</button>
        </form>
    </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
