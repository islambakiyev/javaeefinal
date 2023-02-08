package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.classes.Comments;
import kz.bitlab.javaee.classes.DBManager;
import kz.bitlab.javaee.classes.News;
import kz.bitlab.javaee.classes.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));
        News news = DBManager.getOneNews(id);
        request.setAttribute("news", news);

        ArrayList<Comments> comments = DBManager.getALlComments(id);
        request.setAttribute("comments", comments);

        request.getRequestDispatcher("details.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        int newsId = Integer.parseInt(request.getParameter("newsId"));

        Comments comments = new Comments();
        comments.setComment(comment);
        comments.setNewsId(newsId);

        User user = (User) request.getSession().getAttribute("currentUser");
        comments.setUser(user);

        DBManager.addComment(comments);

        response.sendRedirect("/details?id="+newsId);
    }
}
