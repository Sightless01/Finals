package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GreetMe", urlPatterns = {"/GreetMe"})
public class GreetMe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("yourname");
        String color = request.getParameter("favcolor");
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <title>Servlet Demo: Handling Form Data Submission (GET)</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf ("  <h1 style='color: %s'>Hello, %s!</h1>", color, name);
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
