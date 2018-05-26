/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tww.servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("psw");
        String email = request.getParameter("email");
        password=CryptWithMD5.cryptWithMD5(password);
        if(!RegisterDao.checkUser(username)){
            response.sendRedirect("registration");
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client","root","");
            PreparedStatement st = con.prepareStatement("SELECT MAX(user_id) AS id FROM client.users");
            ResultSet rs =st.executeQuery();
            int id = 0;
            if(rs.next()){
                id = rs.getInt("id")+1;
             }
            PreparedStatement ps = con.prepareStatement("insert into client.users values(?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, password);
            ps.setString(6, email);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("index");
            } else{
                response.sendRedirect("register");
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}
