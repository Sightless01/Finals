package tww.servlets;

import java.io.IOException;
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

@WebServlet(name = "CancelReq", urlPatterns = {"/CancelReq"})
public class CancelReq extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection c = null;
        Statement stmt = null;
        HttpSession session = request.getSession();
        Calendar currenttime = Calendar.getInstance();               //creates the Calendar object of the current time
        Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
        Date dateToday = subDays((java.sql.Date) sqldate);
        String name = (String) session.getAttribute("username");
        String prod = request.getParameter("prod");
        String client_id = "";
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
            PreparedStatement ps = c.prepareStatement("update request set status = 2 where client_id=? and prod_id=? and (start_date >= ? or status is null);");
            ps.setString(1, client_id);
            ps.setString(2, prod);
            ps.setDate(3, (java.sql.Date) dateToday);
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
         try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://192.168.43.64:3306/database", "root", "");
            PreparedStatement ps = c.prepareStatement("select * from request where client_id=? and prod_id=? and (start_date >= ? or status is null);");
            ps.setString(1, client_id);
            ps.setString(2, prod);
            ps.setDate(3, (java.sql.Date) dateToday);
            ResultSet rs = ps.executeQuery();
            if(rs.next()==false){
                session.setAttribute("stats", "Can't Cancel request.");
            }
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
        
        response.sendRedirect("reservation");
    }

    public static java.sql.Date subDays(java.sql.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 2);
        return new java.sql.Date(c.getTimeInMillis());
    }
}
