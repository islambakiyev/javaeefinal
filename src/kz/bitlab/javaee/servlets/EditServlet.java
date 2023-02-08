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

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null&&user.getRole()==1){
            Long id = Long.valueOf(request.getParameter("id"));
            News news = DBManager.getOneNews(id);
            request.setAttribute("news", news);

            ArrayList<Category> categories = DBManager.getAllCategories();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("edit.jsp").forward(request,response);
        }else response.sendRedirect("sign?alert");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");

        if(user!=null&&user.getRole()==1){
            Long newsId = Long.valueOf(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String content = request.getParameter("content");
            Long id  = Long.valueOf(request.getParameter("category"));

            News news = new News();
            news.setId(newsId);
            news.setTitle(title);
            news.setDescription(description);
            news.setContent(content);
            Category category = DBManager.getCategory(id);
            news.setCategory(category);

            DBManager.updateNews(news);

            response.sendRedirect("/homepage");
        }else response.sendRedirect("sign?alert");

    }
}
