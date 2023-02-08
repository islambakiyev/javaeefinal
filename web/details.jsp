<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.classes.News" %>
<%@ page import="kz.bitlab.javaee.classes.Comments" %>
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

<div class="container mt-5">

    <%
    if(user!=null && user.getRole()==1){
    %>
    <a href="/edit?id=<%=news.getId()%>">Edit</a>
    <a href="/delete?id=<%=news.getId()%>">Delete</a>
    <%
    }
    %>
    <article class="blog-post">
        <h2 class="blog-post-title mb-1"><%=news.getTitle()%></h2>
        <p class="blog-post-meta"><%=news.getCategory().getName()%></p>
        <p class="blog-post-meta"><%=news.getPostDate()%></p>
        <p><%=news.getContent()%></p>
    </article>

    <%if(user!=null){
        %>
    <form action="/details" method="post">
        <div class="mb-3">
            <input hidden name="newsId" value="<%=news.getId()%>">
            <label class="form-label">Add comment</label>
            <textarea name="comment" class="form-control" rows="3"></textarea>
            <button name="button" class="mt-3 btn btn-success">Add</button>
        </div>
    </form>
    <%
    }%>



    <%

        ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("comments");

        if(comments.isEmpty()){
    %>
            <h5>No comments</h5>

    <%
        }else { %>
    <h5>All comments </h5>

    <%
    for(Comments comment : comments){
    %>

    <div class="card mb-3">
        <div class="card-body">
            <span><%=comment.getUser().getFullName()%></span>
            <span><%=comment.getPostDate()%></span>
            <p><%= comment.getComment()%></p>
        </div>
    </div>



    <%
    }
        }
    %>


</div>

<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>
