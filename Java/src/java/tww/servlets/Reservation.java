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
import java.util.Date;
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
@WebServlet(name = "reservation", urlPatterns = {"/reservation"})
public class Reservation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String stats = (String) session.getAttribute("stats");
        response.setContentType("text/html;charset=UTF-8");

        Connection c = null;
        PrintWriter out = response.getWriter();
        String reservationDisplay = "    <form action=\"CancelReq\" method=\"POST\">"
                + "    <table id=\"reservation\" style=\"width:80%; margin:auto; margin-bottom:50px;\">"
                + "        <thead>"
                + "            <tr>"
                + "                <th></th>"
                + "                <th>Product</th>"
                + "                <th>Date Requested</th>"
                + "                <th>Rent Start Period</th>"
                + "                <th>Rent End Period</th>"
                + "                <th>Price</th>"
                + "                <th>Status</th>"
                + "            </tr>"
                + "        </thead>";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://192.168.43.64:3306/database", "root", "");
            PreparedStatement ps = c.prepareStatement("select * from request join products on request.prod_id  = products.prod_id join client on request.client_id = client.client_id");
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                reservationDisplay = "<p> No reservations</p>";
            }
            rs.previous();
            while (rs.next()) {
                if (rs.getString("client.username").equalsIgnoreCase(username)) {
                    String productName = rs.getString("products.name");
                    Date dateReq = rs.getDate("request_date");
                    Date dateStart = rs.getDate("start_date");
                    Date dateEnd = rs.getDate("end_date");
                    int price = rs.getInt("price");
                    String status = rs.getString("status");
                    String prod = rs.getString("prod_id");
                    reservationDisplay += "        <tbody>"
                            + "            <tr>"
                            + "                <td><button onclick=\"myFunction()\" value='" + prod + "' name='prod' class='button'>Cancel</button></td>"
                            + "                <td>" + productName + "</td>"
                            + "                <td> " + dateReq + "</td>"
                            + "                <td> " + dateStart + "</td>"
                            + "                <td> " + dateEnd + "</td>"
                            + "                <td> &#8369;" + price + "</td>";
                    if (status==null) {
                        status = "pending..";
                        reservationDisplay += " <td> " + status + "</td>"
                                + "            </tr>"
                                + "        </tbody>";
                    } else if (status.equals("0")) {
                        status = "Request was rejected";
                        reservationDisplay += " <td> " + status + "</td>"
                                + "            </tr>"
                                + "        </tbody>";
                    } else if(status.equals("2")) {
                        status = "Cancelled Request";
                        reservationDisplay += " <td>" + status + "</td>"
                                + "            </tr>"
                                + "        </tbody>";
                    } else if(status.equals("3")) {
                        status = "Cancelled Accepted Request";
                        reservationDisplay += " <td>" + status + "</td>"
                                + "            </tr>"
                                + "        </tbody>";
                    } else {
                        status = "Request was accepted";
                        reservationDisplay += " <td>" + status + "</td>"
                                + "            </tr>"
                                + "        </tbody>";
                    }

                }
            }
            reservationDisplay += "        </table>"    
                    + "    </form>";
            rs.close();

        } catch (Exception e) {
            reservationDisplay += "<p>" + e.getClass() + "</p><br><p>" + e.getMessage() + "</p>";
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
        
        out.println("    <br><h1>Requests</h1>");
        
        out.println(reservationDisplay);
        out.println(" <script>");

                out.println(" function myFunction() {");
                out.println(" confirm(\"Cancel Request?\");");
                out.println(" var stats=\""+stats+"\";");
                
                out.println(" if(stats != null)");
                out.println(" alert(\""+stats+" \");");
                out.println(" }");
        out.println(" </script>");
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
