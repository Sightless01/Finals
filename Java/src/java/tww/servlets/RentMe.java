/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tww.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "rentMe", urlPatterns = {"/rentMe"})
public class RentMe extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String dateStart = request.getParameter("startdate");
        String days = request.getParameter("rentPeriod");
        int day = Integer.parseInt(days);
        Date date1 = Date.valueOf(dateStart);
        System.out.println(date1);
        Date date2 = addDays(date1,day);
        String prod = request.getParameter("rent");
        int prod_id = Integer.parseInt(prod);
        HttpSession session = request.getSession();
        Connection c = null;
        String username = (String) session.getAttribute("username");
        String status = (String) session.getAttribute("status");
        if (status==null){
            status = "not null";
        }
        if (username != null && !status.equals("blocked")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://192.168.43.64:3306/database", "root", "");
                PreparedStatement ps = c.prepareStatement("insert into request(client_id, prod_id,start_date ,end_date,request_date) values((SELECT client_id FROM client WHERE username=?),?,?,?,?)");
                ps.setString(1, username);
                ps.setInt(2, prod_id);
                ps.setDate(3, date1);
                ps.setDate(4, date2);
                Calendar currenttime = Calendar.getInstance();               //creates the Calendar object of the current time
                Date sqldate = new Date((currenttime.getTime()).getTime());  //creates the sql Date of the above created object
                ps.setDate(5, (java.sql.Date) sqldate);
                ps.executeUpdate();
                request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

                request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
                out.println("Request completed");

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
        } else if(status.equals("blocked")){
            request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);

            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);
            out.println("You were blocked by the admin. Please contact the admin first.");
        }else {
            request.getRequestDispatcher("/WEB-INF/banner.html").include(request, response);
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);
            out.println("You are not login into an account. Please login or register first.");
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

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
}
