<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 01/10/2025
  Time: 11:01 CH
  To change this template use File | Settings | File Templates.
--%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-primary shadow-sm px-4">
        <div>
            <a href="${pageContext.request.contextPath}/" class="navbar-brand d-flex align-items-center">
                <img src="${pageContext.request.contextPath}/img/logo_codegym.jpg"
                     alt="Logo" width="30" height="30" class="mr-2 rounded-circle">
                <span>Todo App</span>
            </a>
        </div>

        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a>
            </li>
            <li class="nav-item">
                <a href="<%= request.getContextPath() %>/register" class="nav-link">Sign up</a>
            </li>
        </ul>
    </nav>
</header>