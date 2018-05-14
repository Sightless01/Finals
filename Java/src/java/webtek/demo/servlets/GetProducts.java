package webtek.demo.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webtek.demo.beans.Product;

@WebServlet(name = "ShowProducts", urlPatterns = {"/GetProducts"})
public class GetProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("cat");
        String view = request.getParameter("view");
        
        ServletContext context = this.getServletContext();
        Connection dbConn = (Connection) context.getAttribute("dbconn");
        
        String query = "SELECT * FROM produktos ";
        
        try {
            
            PreparedStatement ps = dbConn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            
            ArrayList<Product> products = new ArrayList<>();
            
            if (rs.first()) {
                do {
                    String product_id = rs.getString("product_id");
                    name = rs.getString("name");
                    String description = rs.getString("description");
                    String frontview = rs.getString("frontview");
                    String sideview = rs.getString("sideview");
                    String backview = rs.getString("backview");
                    BigDecimal price = rs.getBigDecimal("price");
                    
                    Product product = new Product(product_id, name, description, frontview, sideview, backview, price);
                    products.add(product);
                } while (rs.next());
            }
            
            rs.close();
            ps.close();
            
            request.setAttribute("products", products);
            
            RequestDispatcher rd = null;
            
            if (view.equalsIgnoreCase("html")) {
                rd = request.getRequestDispatcher("ShowHTML");
            } else if (view.equalsIgnoreCase("json")) {
                rd = request.getRequestDispatcher("ShowJSON");
            }
            
            if (rd == null) {
                response.sendError(400);
            } else {
                rd.forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GetProducts.class.getName()).log(Level.SEVERE, null, ex);
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
