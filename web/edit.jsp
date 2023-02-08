<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.classes.News" %>
<%@ page import="kz.bitlab.javaee.classes.Category" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.108.0">
    <title>NEWS PORTAL</title>

    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="blog.css" rel="stylesheet">
</head>
<body>

<%@include file="navbar.jsp"%>

<%
    News news = (News) request.getAttribute("news");
    %>

<div class="container">

    <form action="/edit" method="post">
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input hidden name="id" value="<%=news.getId()%>">
            <input type="text" class="form-control" name="title" value="<%=news.getTitle()%>">
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea class="form-control" name="description" ><%=news.getDescription()%></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Content</label>
            <textarea type="text" class="form-control" name="content" ><%=news.getContent()%></textarea>
        </div>


        <label class="form-label">Category</label>
        <select class="form-select mb-3" name="category">

            <%ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                for(Category category : categories){%>

            <option value="<%=category.getId()%>" <%=(news.getCategory().getId()==category.getId()?"selected":"")%>><%=category.getName()%></option>

            <%
                }
            %>
        </select>

        <div class="mb-3">
            <button class="btn btn-success">Edit news</button>
        </div>

    </form>

</div>

<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>
