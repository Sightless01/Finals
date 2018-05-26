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
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en-US'>");
        out.print("<head>");
        out.print("<meta charset='UTF-8'>");
	out.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
	out.print("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
	out.print("<title>Webtech2018</title>");
	out.print("<link rel='stylesheet' href='styles.css'>");
	out.print("<link async href='http://fonts.googleapis.com/css?family=Anton' data-generated='http://enjoycss.com' rel='stylesheet' type='text/css'//>");
	out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js'></script>");
	out.print("<script src='js/jquery-3.3.1.min.js'></script>");
        out.print("</head><body>");
        
        request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

        if (session == null) {
            out.print("<p>You are not logged in.</p>");
            
        } else {
            String user = (String) session.getAttribute("username");
            out.printf("<p>Welcome <span>%s</span>.</p>", user);
        }
        
    }
}
