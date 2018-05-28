package tww.servlets;

import java.sql.*;

public class LoginDao {

    public static String checkUser(String name, String pass) {
        String st = "";
        Connection c = null;
        Statement stmt = null;
        int status = 2;
        int block = 2;
        int clientid = 0;
        String dbPass="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            PreparedStatement ps = c.prepareStatement("select * from Client where username=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 status = rs.getInt("status");
                 block = rs.getInt("block");
                 dbPass = rs.getString("password");
            }
            if (status==0 ){
                st = "You are not allowed to Sign in! Contact the administrator now!";
            } else if (status==2){
                st = "Incorrect password or username!";
            } else if (block==1){
                st = "blocked";
            }
            else if (BCrypt.checkpw(pass, dbPass ) && status==1) {
                st = "allow";
            } 
            rs.close();
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
         return st;
    }
    
    public static void main(String[] args) {
    
        
    }
}
