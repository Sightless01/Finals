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
@WebServlet(name = "RentMe", urlPatterns = {"/RentMe"})
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
        String prod = request.getParameter("rent");
        HttpSession session = request.getSession();
        String client_id = null;
        String comp_id = null;
        String trans_id = null;
        Connection c = null;
        if(session!=null){
            String username = session.getAttribute("username").toString();
            try {
            Class.forName("com.mysql.jdbc.Driver");
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            
            PreparedStatement ps = c.prepareStatement("select * from client where name=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                client_id = rs.getString("client_id");
            }
            rs.close();

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
            
            PreparedStatement ps = c.prepareStatement("select * from client where name=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                client_id = rs.getString("client_id");
            }
            rs.close();

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
            
            PreparedStatement ps = c.prepareStatement("select * from transaction");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                trans_id = rs.getString("trans_id");
            }
            rs.close();

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
            
            PreparedStatement ps = c.prepareStatement("insert into transaction values(?,?,?,?,?,?,?)");
            ps.setString(1, trans_id);
            ps.setString(2, null);
            ps.setString(3, null);
            ps.setString(4, null);
            ps.setString(5, comp_id);
            ps.setString(6, client_id);
            ps.setString(7, prod);
            ResultSet rs = ps.executeQuery();
           
             out.println("Transaction added");
                
            rs.next();
            rs.close();

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
        } else{
            request.getRequestDispatcher("/WEB-INF/header").include(request, response);
        
        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);
            
        } else {
             request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
        }
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

}
