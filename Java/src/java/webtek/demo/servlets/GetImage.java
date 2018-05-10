package webtek.demo.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetImage", urlPatterns = {"/images/*"})
public class GetImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = this.getServletContext();
        String pathToImagesFolder = context.getRealPath("/WEB-INF/images");
        
        String filename = request.getPathInfo() + ".jpg";
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("image/jpg");
        
        FileInputStream file = new FileInputStream(pathToImagesFolder + filename);
        ServletOutputStream sos = response.getOutputStream();
        
        byte[] dataBuffer = new byte[100000];
        
        int bytesRead;
        do {
            bytesRead = file.read(dataBuffer);
            sos.write(dataBuffer, 0, bytesRead);
        } while (bytesRead > 0);
        
        file.close();
        sos.close();
    }
}
