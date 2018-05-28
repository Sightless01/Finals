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
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tww.beans.Product;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String presyo = request.getParameter("price");
        String categ = request.getParameter("cat");
        String ret = request.getParameter("ret");
        int p = 10000000;
        if(presyo!=null){
             p = Integer.parseInt(presyo);
        }
        Connection c = null;
        ArrayList<Product> products = new ArrayList<>();
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select * from Products join company on products.comp_id = company.comp_id where availability != 0 and categories=? and company.name=? and price<?");
            ps.setString(1, categ);
            ps.setString(2, ret);
            ps.setInt(3, p);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double price = rs.getInt("price");

                int product_id = rs.getInt("prod_id");
                String name = rs.getString("products.name");
                String description = rs.getString("description");
                String frontview = rs.getString("frontview");
                String sideview = rs.getString("sideview");
                String backview = rs.getString("backview");
                String cat = rs.getString("categories");
                String comp = rs.getString("company.name");
                Product product = new Product(product_id, name, description, frontview, sideview, backview, price, comp, cat);
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
            out.printf("<p>Welcome to BrendoRENT <span>%s</span>.</p>", user);
        }

        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
        }
        out.println("    <h1>Product List</h1>");
       request.getRequestDispatcher("/WEB-INF/filter.html").include(request, response);

        if (products.size() == 0) {
            out.println("    <h2>No products available.</h2>");
        }
   for (Product product : products) {
       int prod = product.getProdId();
            String name = product.getName();
            String description = product.getDescription();
            String frontview = product.getFrontview();
            String backview = product.getBackview();
            String sideview = product.getSideview();
            double price = product.getPrice();
            String comp_id = product.getcom();
            out.println("  <div class='cont'>");
            out.println("  <form method='POST' action='rentMe'>");
            out.println("  <div class='row'>");
            out.println("  <div class='column'>");
            out.println("  <div class='card'>");
            out.println("   <img src='" + frontview.substring(1) + "' id='imahe' style='width:200px;height:425px'>");
            if (!backview.equals("not")) {
                out.println("   <img src='" + backview.substring(1) + "' id='imahe' style='width:200px;height:425px'>");
            }
            if (!sideview.equals("not")) {
                out.println("   <img src='" + sideview.substring(1) + "' id='imahe' style='width:200px;height:425px'>");
            }

            out.println("    <div class='containero'>");
            out.println("    <h2>" + name + "</h2>");
            out.println("      <p class='title'>Price: &#8369;" + price + "</p>");
            out.println("    <p>" + description + "</p>");
            out.println("    <p>Proprietor: " + comp_id + "</p>");
            out.println(" Request Date:");
            out.println(" <input class='datafield' type='date' name='startdate' required>");
            out.println("   <p><button value='" + prod + "' name='rent' class='button'>Rent</button></p>");
            out.println("  </div>");
            out.println("  </div>");
            out.println("  </div>");
            out.println("  </form>");
            out.println("  </div>");
            out.println(" <script>");

            out.println(" var today = new Date();");
            out.println(" var dd = today.getDate();");
            out.println(" var mm = today.getMonth()+1;");
            out.println(" var yyyy = today.getFullYear();");

            out.println("  if(dd<10) {");
            out.println("    dd = '0'+dd");
            out.println(" } ");

            out.println(" if(mm<10) {");
            out.println("     mm = '0'+mm");
            out.println(" } ");

            out.println(" today = yyyy + '-' + mm + '-' + dd  ;");
            out.println(" var y = document.getElementsByClassName('datafield');");
            out.println(" for (var i = 0; i < y.length; i++) {");
            out.println(" y[i].setAttribute('min', today);");
            out.println(" }");
            out.println(" </script>");

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
