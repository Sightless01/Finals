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
@WebServlet(name = "transaction", urlPatterns = {"/transaction"})
public class Transaction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        response.setContentType("text/html;charset=UTF-8");

        Connection c = null;
        PrintWriter out = response.getWriter();
        String transactionDisplay = "    <table id=\"transaction\" style=\"width:80%; margin:auto; margin-bottom:50px;\">"
                + "        <thead>"
                + "            <tr>"
                + "                <th>Product</th>"
                + "                <th>Date Paid</th>"
                + "                <th>Date Returned</th>"
                + "                <th>Price</th>"
                + "            </tr>"
                + "        </thead>";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            PreparedStatement ps = c.prepareStatement("select * from transaction join products on transaction.prod_id  = products.prod_id join client on transaction.client_id = client.client_id");
            ResultSet rs = ps.executeQuery();
            if(rs.next()==false){
                transactionDisplay = "<p> No transactions</p>";
            }
            while (rs.next()) {
                if (rs.getString("client.username").equalsIgnoreCase(username)) {
                    String productName = rs.getString("products.name");
                    String datePaid = rs.getString("date_paid");
                    String dateReturned = rs.getString("date_returned");
                    int price = rs.getInt("price");
                    if(dateReturned==null){
                        dateReturned="Not yet returned";
                     }
                    transactionDisplay += "        <tbody>"
                            + "            <tr>"
                            + "                <td>" + productName + "</td>"
                            + "                <td> " + datePaid + "</td>"
                            + "                <td> " + dateReturned + "</td>"
                            + "                <td> &#8369;" + price + "</td>"
                            + "            </tr>"
                            + "        </tbody>";
                }
            }
            transactionDisplay += "        </table>";
            rs.close();

        } catch (Exception e) {
            transactionDisplay += "<p>" + e.getClass() + "</p><br><p>" + e.getMessage() + "</p>";
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
        out.println("    <br><h1>Transaction</h1>");
        
        out.println(transactionDisplay);
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
