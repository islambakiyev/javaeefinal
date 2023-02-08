package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.classes.DBManager;
import kz.bitlab.javaee.classes.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/homepage")
public class HomePageServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<News> news = DBManager.getAllNews();
        request.setAttribute("news", news);
        request.getRequestDispatcher("homepage.jsp").forward(request,response);
    }
}
