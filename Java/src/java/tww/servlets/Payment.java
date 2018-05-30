/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tww.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static tww.servlets.CancelReq.subDays;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "Payment", urlPatterns = {"/Payment"})
public class Payment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection c = null;
        Statement stmt = null;
        HttpSession session = request.getSession();
        Calendar currenttime = Calendar.getInstance();               //creates the Calendar object of the current time
        Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
        Date dateToday = subDays((java.sql.Date) sqldate);
        String name = (String) session.getAttribute("username");
        String prod = request.getParameter("pay");
        String client_id = "";
        PrintWriter out = response.getWriter();
        if (prod !=null) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
                PreparedStatement ps = c.prepareStatement("select * from Client where username=?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                rs.next();
                client_id = rs.getString("client_id");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    try {
                        c.close(); // <-- This is important
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
                PreparedStatement ps = c.prepareStatement("update transaction set date_paid = ? where client_id=? and prod_id=?;");
                ps.setDate(1, (java.sql.Date) dateToday);
                ps.setString(2, client_id);
                ps.setString(3, prod);

                ps.executeUpdate();
                ps.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    try {
                        c.close(); // <-- This is important
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            response.setContentType("text/html");
            out.print("<html lang='en-US'>");
            out.print("<head>");
            out.print("<meta charset='UTF-8'>");
            out.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.print("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
            out.print("<title>Webtech2018</title>");
            out.print("<link rel='stylesheet' href='styles.css'>");
            out.print("</head><body>");

            request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

            if (session == null) {
                out.print("<p>You are not logged in.</p>");

            } else {
                String user = (String) session.getAttribute("username");
                out.printf("<p>Welcome to BrendoRENT <span>%s</span>.</p>", user);
            }

            if (session == null) {
                request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);

            } else {
                request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
            }
            out.println("Payment online Succesfull.");
        } else {
        out.print("<html lang='en-US'>");
        out.print("<head>");
        out.print("<meta charset='UTF-8'>");
        out.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
        out.print("<title>Webtech2018</title>");
        out.print("<link rel='stylesheet' href='styles.css'>");
        out.print("</head><body>");

        request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

        if (session == null) {
            out.print("<p>You are not logged in.</p>");

        } else {
            String user = (String) session.getAttribute("username");
            out.printf("<p>Welcome to BrendoRENT <span>%s</span>.</p>", user);
        }

        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
        }
            out.println("You can pay the product once you had receive the item you rented. Thank you.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
