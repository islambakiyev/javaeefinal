<%@ page import="kz.bitlab.javaee.classes.User" %>
<%User user = (User) request.getSession().getAttribute("currentUser");%>

<div class="container">
    <header class="blog-header lh-1 py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
            <%
                if(user!=null && user.getRole()==1){
            %>

                <a class="link-secondary" href="/add-news">Add News</a>

            <%
                }
            %>
            </div>
            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="#">NEWS PORTAL</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <%
                    if(user!=null){
                %>
                <a class="btn btn-sm btn-outline-secondary" href="/logout">Logout</a>
                <%
                    }else {
                %>
                <a class="btn btn-sm btn-outline-secondary" href="/sign">Sign up</a>
                <a class="btn btn-sm btn-outline-secondary" href="/registration">Register</a>
                <%
                    }
                %>
            </div>
        </div>
    </header>

    <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
            <a class="p-2 link-secondary" href="#">World</a>
            <a class="p-2 link-secondary" href="#">Technology</a>
            <a class="p-2 link-secondary" href="#">Business</a>
            <a class="p-2 link-secondary" href="#">Politics</a>
            <a class="p-2 link-secondary" href="#">Opinion</a>
            <a class="p-2 link-secondary" href="#">Science</a>
            <a class="p-2 link-secondary" href="#">Health</a>
            <a class="p-2 link-secondary" href="#">Style</a>
            <a class="p-2 link-secondary" href="#">Travel</a>
        </nav>
    </div>
</div>