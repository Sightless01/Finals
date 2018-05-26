/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tww.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tww.beans.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
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
@WebServlet(name = "ShowProducts", urlPatterns = {"/ShowProducts"})
public class ShowProducts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Connection c = null;
        ArrayList<Product> products = new ArrayList<>();
        PrintWriter out = response.getWriter();
        String username =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select * from Products join company on products.comp_id = company.comp_id where availability = 1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double price = rs.getInt("price");
               
                String product_id = rs.getString("prod_id");
                String name = rs.getString("products.name");
                String description = rs.getString("description");
                String frontview = rs.getString("frontview");
                String sideview = rs.getString("sideview");
                String backview = rs.getString("backview");
                String cat = rs.getString("categories");
                String event = rs.getString("event");
                 String comp = rs.getString("company.name");
                Product product = new Product(product_id, name, description, frontview, sideview, backview, price, comp, event, cat);
                products.add(product);
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

        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/header").include(request, response);

        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
        }
        out.println("    <h1>Product List</h1>");
        out.println(" <form method='POST' action='search' style:'display:inline;'>");
        out.println("        <p>Filter:</p>");
        out.println("        <p>Price below: </p>");
        out.println("        <input type='number' name='price' value='1000000' placeholder='Enter Price' >");
        out.println("        <p>Category</p>");
        out.println("<select name='cat'>");
        out.println("<option value='tops'>Tops</option>>");
        out.println("<option value='jacket'>Jacket</option>>");
        out.println("<option value='slipper'>Slipper</option>>");
        out.println("<option value='shoes'>Shoes</option>>");
        out.println("<option value='dress'>Dress</option>>");
        out.println("<option value='longsleeve'>Longsleeve</option>>");
        out.println("<option value='tuxedo'>Tuxedo</option>>");
        out.println("</select>");
        out.println("        <p>Retailer</p>");
        out.println("<select name='ret'>");
        out.println("<option value='champion'>Champion</option>>");
        out.println("<option value='adidas'>Adidas</option>>");
        out.println("<option value='hiit'>Hiit</option>>");
        out.println("<option value='nautica'>Nautica</option>>");
        out.println("<option value='montague burton'>Montague Burton</option>>");
        out.println("</select>");
        out.println("       <div class='clearfix'>");
        out.println("           <button type='submit' class='searchbttn' >Search</button>");
        out.println("       </div>");
        out.println("       </form>");

        if (products.size() == 0) {
            out.println("    <h2>No products available.</h2>");
        }
        if(session!=null){
            username = session.getAttribute("username").toString();
        }
        for (Product product : products) {
            String name = product.getName();
            String description = product.getDescription();
            String frontview = product.getFrontview();
            String backview = product.getBackview();
            String sideview = product.getSideview();
            double price = product.getPrice();
            String comp_id = product.getcom();
            out.println("  <div class='row'>");
            out.println("  <div class='column'>");
            out.println("  <div class='card'>");
            out.println("   <img src='"+ frontview.substring(1)+"' id='imahe' style='width:60% ;height:70%'>");
            out.println("    <div class='containero'>");
            out.println("    <h2>"+name+"</h2>");
            out.println("      <p class='title'>"+price+"</p>");
            out.println("    <p>"+description+"</p>");
            out.println("    <p> "+comp_id+"</p>");

            out.println("   <p><button class='button'>Rent</button></p>");
            out.println("  </div>");
            out.println("  </div>");
            out.println("  </div>");

        }

    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
