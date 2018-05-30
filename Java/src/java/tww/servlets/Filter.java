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
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "Filter", urlPatterns = {"/Filter"})
public class Filter extends HttpServlet {

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
        /* TODO output your page here. You may use following sample code. */
        PrintWriter out = response.getWriter();
        Connection c = null;
        Set<String>  companies= new TreeSet();
        Set<String>  categories= new TreeSet();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select * from company ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String comp = rs.getString("name");
                companies.add(comp);
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
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select * from Products ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cat = rs.getString("categories");
                categories.add(cat);
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
        request.getRequestDispatcher("/WEB-INF/filter.html").include(request, response);
        out.println("Category");
        out.println("   <select name='cat'>");
        out.println("   <option value='any'>Any</option>");
        for(String cat : categories){
            out.println("   <option value='"+cat+"'>"+cat+"</option>");
        }
        out.println("   </select>");

        out.println("Proprietor");
        out.println("   <select name='ret'>");
        out.println("   <option value='any'>Any</option>");
        for(String comp : companies){
            out.println("   <option value='"+comp+"'>"+comp+"</option>");
        }
        out.println("   </select>");
        out.println("<button type='submit' class='searchbttn' >Search</button>");
        out.println("</form>");
        out.println("<script>");

        out.println("    var today = new Date();");
        out.println(" var dd = today.getDate();");
        out.println(" var mm = today.getMonth()+1; //January is 0!");
        out.println("var yyyy = today.getFullYear();");

        out.println("  if(dd<10) {");
        out.println("      dd = '0'+dd");
        out.println("  } ");

        out.println("if(mm<10) {");
        out.println("   mm = '0'+mm");
        out.println(" } ");

        out.println("  today = yyyy + '-' + mm + '-' + dd  ;");
        out.println(" document.getElementById('datefield').setAttribute(\"min\", today);");
        out.println("</script>");
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
