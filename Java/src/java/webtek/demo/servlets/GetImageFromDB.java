package webtek.demo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetImageFromDB", urlPatterns = {"/dbimages/*"})
public class GetImageFromDB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = this.getServletContext();

        Connection dbConn = (Connection) context.getAttribute("dbconn");

        String prodId = request.getPathInfo().substring(1);
        
        String query = "SELECT sideview " +
                       "FROM products " ;
        
        try {
            PreparedStatement ps = dbConn.prepareStatement(query);
            ps.setString(1, prodId);
            
            ResultSet rs = ps.executeQuery();
            rs.first();
            byte[] imageData = rs.getBytes("image");
            
            rs.close();
            ps.close();
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("image");
            ServletOutputStream sos = response.getOutputStream();
            sos.write(imageData);
            sos.close();
            
        } catch (Exception ex) {
            Logger.getLogger(GetImageFromDB.class.getName()).log(Level.SEVERE, null, ex);
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
