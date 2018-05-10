package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webtek.demo.beans.Product;

@WebServlet(name = "ShowJSON", urlPatterns = {"/ShowJSON"})
public class ShowJSON extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
        
        String json = "", separator = "";
        for (Product product : products) {
            String prodId = product.getProdId();
            String name = product.getName();
            String description = product.getDescription();
            String frontview = product.getFrontview();
            String sideview = product.getSideview();
            String backview = product.getBackview();
            BigDecimal price = product.getcom_id();
            
            json += separator +
                    String.format("  {\"prod_id\": \"%s\", " +
                                  "\"name\": \"%s\", " +
                                  "\"description\": \"%s\", " +
                                  "\"frontview\": \"%s\", " +
                                  "\"sideview\": \"%s\", " +
                                  "\"backview\": \"%s\", " +
                                  "\"com_id\": %s}",
                            prodId, name, description, frontview, sideview, backview, price);
            separator = ",\n";
        }
        
        json = "[\n" + json + "\n]";
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        out.println(json);
        
        out.close();
    }
}
