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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        String date = request.getParameter("date");
        
        if(presyo.equals("")){
            presyo="";
        } else{
            presyo="and products.price <="+presyo;
        }
        if(date.equals("")){
            date="";
        } else{
            date =" and start_date <= "+date+" and end_date >= "+date;
        }
        if(ret.equals("any")){
            ret="";
        }else{
            ret=" and company.name =\""+ret+"\"";
        }
        if(categ.equals("any")){
            categ="";
        }else{
            categ=" and products.categories=\""+categ+"\"";
        }
        Connection c = null;
        ArrayList<Product> products = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();

        PrintWriter out = response.getWriter();
        String username = null;
        Calendar currenttime = Calendar.getInstance();               //creates the Calendar object of the current time
        Date sqldate = new Date((currenttime.getTime()).getTime());
        System.out.println("select products.price, products.prod_id, products.name, "
                    + "products.description, products.sideview, products.frontview, products.backview, products.categories,company.name, products.availability"
                    + " from Products join company on products.comp_id = company.comp_id where availability = 1 "
                    + ret +categ+presyo);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            String sql ="select * from request where status=1 "+date;
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("prod_id");
                list.add(product_id);
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
        System.out.println("select products.price, products.prod_id, products.name, "
                    + "products.description, products.sideview, products.frontview, products.backview, products.categories,company.name, products.availability"
                    + " from Products join company on products.comp_id = company.comp_id where availability = 1 "
                    + ret +categ+presyo);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select products.price, products.prod_id, products.name, "
                    + "products.description, products.sideview, products.frontview, products.backview, products.categories,company.name, products.availability"
                    + " from Products join company on products.comp_id = company.comp_id where availability = 1 "
                    + ret +categ+presyo);

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
                    int count = rs.getInt("availability");
                    Product product = new Product(product_id, name, description, frontview, sideview, backview, price, comp, cat, count);
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
        request.getRequestDispatcher("Filter").include(request, response);

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
            out.println("  <form method='POST' action='rentMe' id=\"rentform\">");
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
            out.println(" Rent Period:");
            out.println(" <input type='number' name='rentPeriod' min='1' max='30' required>");
            out.println("   <p><button onclick=\"myFunction()\" value='" + prod + "' name='rent' class='button'>Rent</button></p>");
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
            out.println(" <script>");
            out.println(" function myFunction() {");
            out.println(" confirm(\"Rent this item?\");");
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
