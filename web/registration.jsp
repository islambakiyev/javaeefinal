<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container">

    <form action="/registration" method="post">
        <div class="mb-3">
            <label class="form-label">Full name</label>
            <input type="text" class="form-control" name="name">
        </div>

        <div class="mb-3">
            <label class="form-label">E-mail</label>
            <input type="text" class="form-control" name="email">
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="password">
        </div>


        <div class="mb-3">
            <button class="btn btn-success">Register</button>
        </div>

    </form>

</div>

<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>
