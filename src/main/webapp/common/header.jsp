<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 01/10/2025
  Time: 11:01 CH
  To change this template use File | Settings | File Templates.
--%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Todo App</a>
        </div>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
            <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Sign up</a></li>
        </ul>
    </nav>
</header>