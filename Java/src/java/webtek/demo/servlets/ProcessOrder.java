package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessOrder", urlPatterns = {"/ProcessOrder"})
public class ProcessOrder extends HttpServlet {
    
    private int orderSlipNo = 0;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] sizes = request.getParameterValues("size");
        String[] colors = request.getParameterValues("color");
        String name = request.getParameter("custname");
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        
        PrintWriter out = response.getWriter();
        
        out.printf("Order Slip No: %d\n\n", ++orderSlipNo);
        out.printf("Customer Name: %s\n\n", name);
        out.printf("Sizes Ordered: %s\n", sizes == null ? "None" : String.join(", ", sizes));
        out.printf("Colors Ordered: %s\n", colors == null ? "None" : String.join(", ", colors));
        
        out.close();
    }
}
