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
        String result = LoginDao.checkUser(username, pass);
        if(result.equals("allow"))
        {  
            session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("home");
                 
        } else{
         String transactionDisplay = "    <script>"
                + "        alert('"+result+"')"
                + "        </script>";
                out.println(transactionDisplay);
                  request.getRequestDispatcher("/WEB-INF/login.html").include(request, response);
        }
    }  
}