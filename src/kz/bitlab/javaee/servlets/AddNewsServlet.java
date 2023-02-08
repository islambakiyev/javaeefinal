package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.classes.Category;
import kz.bitlab.javaee.classes.DBManager;
import kz.bitlab.javaee.classes.News;
import kz.bitlab.javaee.classes.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null && user.getRole()==1){
            ArrayList<Category> categories = DBManager.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("add-news.jsp").forward(request,response);
        }else response.sendRedirect("sign?alert");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null && user.getRole()==1){
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String content = request.getParameter("content");
            Long categoryId = Long.valueOf(request.getParameter("category"));

            Category category = DBManager.getCategory(categoryId);

            News news = new News();
            news.setTitle(title);
            news.setDescription(description);
            news.setContent(content);
            news.setCategory(category);

            DBManager.addNews(news);
            response.sendRedirect("/homepage");
        }else response.sendRedirect("sign?alert");


    }
}
