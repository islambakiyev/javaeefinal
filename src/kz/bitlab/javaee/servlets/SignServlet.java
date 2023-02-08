package kz.bitlab.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.bitlab.javaee.classes.DBManager;
import kz.bitlab.javaee.classes.User;

import java.io.IOException;

@WebServlet(value = "/sign")
public class SignServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sign.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/sign?error";
        String email = request.getParameter("email");

        User user = DBManager.getUser(email);
        if(user!=null){
            redirect = "/sign?passwordError";
            String password = request.getParameter("password");
            if(user.getPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",user);
                redirect = "/";
            }
        }

        response.sendRedirect(redirect);
    }
}
