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

@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        PrintWriter out = response.getWriter();
        
        request.getRequestDispatcher("/WEB-INF/header").include(request, response);
        
        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/menu.html").include(request, response);
            
        } else {
             request.getRequestDispatcher("/WEB-INF/topicMenu.html").include(request, response);
        }

        Connection c = null;
        ArrayList<Product> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("select * from Products join company on products.comp_id = company.comp_id where availability = 1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double price = rs.getInt("price");
                String comp = rs.getString("company.name");
                int product_id = rs.getInt("prod_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String frontview = rs.getString("frontview");
                String sideview = rs.getString("sideview");
                String backview = rs.getString("backview");
                String cat = rs.getString("categories");
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
            out.printf("<div id='slider'> ");
            out.printf("<figure>");
            for (Product product : products) {
                String frontview = product.getFrontview();
                String backview = product.getBackview();
                String sideview = product.getSideview();
                if (frontview == null) {
                    
                } else {
                    out.printf("<img src='%s'>", frontview);
                }
                if (sideview.equals("")) {
                    
                } else {
                     out.printf("<img src='%s'>", backview);
                }
                 out.printf("<img src='%s'>", backview);
                 out.printf("<img src='%s'>", frontview);
                break;
            }

            out.println("</figure>");
            out.println("</div>");
         
        request.getRequestDispatcher("/WEB-INF/footer.html").include(request, response);
    
    }
}
