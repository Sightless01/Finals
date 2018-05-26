package tww.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
public class Login extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
    //    pass=CryptWithMD5.cryptWithMD5(pass);
        if(LoginDao.checkUser(username, pass))
        {  
            session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("home");
                 
        } else{
         response.sendRedirect("index");
        }
    }  
}