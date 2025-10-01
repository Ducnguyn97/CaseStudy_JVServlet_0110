<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 01/10/2025
  Time: 11:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Login Form</h1>
    <form action="<%=request.getContextPath()%>/login" method="post">

        <div class="form-group">
            <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
        </div>

        <div class="form-group">
            <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
        </div>


        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
