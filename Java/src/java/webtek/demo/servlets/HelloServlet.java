package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userAgentString = request.getHeader("User-Agent");
        Date currentDate = new Date();
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Type", "text/html");
        // or, alternatively..
        //   response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <title>Servlet Demo: Hello, Servlet!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <h1>Hello, Servlet!</h1>");
        out.printf ("  <h2>The server date and time is: %s.</h2>\n", currentDate);
        out.printf ("  <h2>Your browser UA string is: %s</h2>\n", userAgentString);
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
