package tww.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Header", urlPatterns = {"/WEB-INF/header"})
public class Header extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        PrintWriter out = response.getWriter();

        
        request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

        if (session == null) {
            out.print("<p>You are not logged in.</p>");
            
        } else {
            String user = (String) session.getAttribute("username");
            String status = (String) session.getAttribute("status");
            
            if(status==null){
                status = "not null";
                out.print("<p>You are not logged in.</p>");
            } else{
                out.printf("<p>Welcome to BrendoRENT <span>%s</span>.", user);
            }
            if(status.equals("blocked")){
              out.printf("Warning! You are blocked by the admin, contact the admin now!");  
            }
            out.printf("</p>");
        }
        
    }
}
