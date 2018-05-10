package webtek.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
public class UploadFile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("yourname");
        
        Part filePart = request.getPart("file");
        String submittedFilename = filePart.getSubmittedFileName();
        long size = filePart.getSize();
        
        ServletContext context = this.getServletContext();
        filePart.write(context.getRealPath("/WEB-INF/images") + "/" + submittedFilename);
        
        PrintWriter out = response.getWriter();
        
        out.printf("<h1>Hi, %s!</h1>\n\n", name);
        out.println("<h2>Your file has been uploaded.</h2>\n\n");
        out.printf("<h3>File Name: %s</h3>\n", submittedFilename);
        out.printf("<h3>File Size: %d byte(s)</h3>", size);
        
        out.close();
    }
}
