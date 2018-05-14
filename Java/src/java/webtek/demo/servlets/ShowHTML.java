package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webtek.demo.beans.Product;

@WebServlet(name = "ShowHTML", urlPatterns = {"/ShowHTML"})
public class ShowHTML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pagefragments/header.html");
        rd.include(request, response);
        
        out.println("    <h1>Product List</h1>");
        
        if (products.size() == 0) {
            out.println("    <h2>No products available.</h2>");
        } else {
            out.println("    <table>");
            out.println("        <thead>");
            out.println("            <tr>");
            out.println("                <th>ProdID</th>");
            out.println("                <th>Name</th>");
            out.println("                <th>Description</th>");
            out.println("                <th>Price</th>");
            out.println("                <th>Frontview</th>");
            out.println("                <th>Sideview</th>");
            out.println("                <th>Backview</th>");
            out.println("            </tr>");
            out.println("        </thead>");
            out.println("        <tbody>");
            
            for (Product product : products) {
                String prodId = product.getProdId();
                String name = product.getName();
                String description = product.getDescription();
                String frontview = product.getFrontview();
                String backview = product.getBackview();
                BigDecimal price = product.getcom_id();
                
                out.println("            <tr>");
                out.printf("                <td>%s</td>\n", prodId);
                out.printf("                <td>%s</td>\n", name);
                out.printf("                <td>%s</td>\n", description);
                out.printf("                <td>%s</td>\n", price);
                out.printf("                <td><img alt='frontview' src='dbimages/%s'></td>\n", prodId);
                out.printf("                <td><img alt='sideview' src='dbimages/%s'></td>\n", prodId);
                out.printf("                <td><img alt='backviewtview' src='dbimages/%s'></td>\n", prodId);
                out.println("            </tr>"); 
            }
            
            out.println("        </tbody>\n    </table>");
        }
        
        rd = request.getRequestDispatcher("/WEB-INF/pagefragments/footer.html");
        rd.include(request, response);
        
        out.close();
    }
}
