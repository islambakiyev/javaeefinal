<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.classes.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add News</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container">

    <form action="/add-news" method="post">
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" class="form-control" name="title">
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea class="form-control" name="description"></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Content</label>
            <textarea type="text" class="form-control" name="content"></textarea>
        </div>


        <label class="form-label">Category</label>
        <select class="form-select mb-3" name="category">

            <%ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
            for(Category category : categories){%>

            <option value="<%=category.getId()%>"><%=category.getName()%></option>

            <%
            }
            %>
        </select>

        <div class="mb-3">
            <button class="btn btn-success">Add news</button>
        </div>

    </form>

</div>

    <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>
