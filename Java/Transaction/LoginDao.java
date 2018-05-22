package tww.servlets;

import java.sql.*;

public class LoginDao {

    public static boolean checkUser(String name, String pass) {
        boolean st = false;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            PreparedStatement ps = c.prepareStatement("select * from Client where username=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (BCrypt.checkpw(pass, rs.getString("password"))) {
                st = true;
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
        System.out.println(checkUser("twistafries", "twistafries"));
        
    }
}
