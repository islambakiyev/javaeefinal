<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container">

    <div class="row mt-3">
        <div class="col-6 mx-auto">
            <%
                String error = request.getParameter("error");
                if(error!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect email!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <%
                String passwordError = request.getParameter("passwordError");
                if(passwordError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect password!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <%
                String alert = request.getParameter("alert");
                if(alert!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                You don't have permission visit this page. Please use your admin account!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <form action="/sign" method="post">

                <div class="mb-3">
                    <label class="form-label">E-mail</label>
                    <input type="text" class="form-control" name="email">
                </div>

                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password">
                </div>


                <div class="mb-3">
                    <button class="btn btn-success">Sign IN</button>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>
