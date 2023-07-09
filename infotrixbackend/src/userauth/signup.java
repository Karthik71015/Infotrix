package userauth;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "signup", value = "/signup")
public class signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        String name=request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dbName = "jdbc:mysql:// localhost:3306/todolist";
        String dbDriver = "com.mysql.jdbc.Driver";
        String userName = "root";
        System.out.println(name + ","+ email + "," + password );
        try{
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbName, userName, "5256MAKK");
            Statement stmt = conn.createStatement();
            String query = "select * from user where Email='" + email + "';";
            ResultSet rs=stmt.executeQuery(query);
            boolean check = rs.next();
            if(check)
            {
                obj.put("success","user already exists");
            }
            else {
                PreparedStatement insertquery=conn.prepareStatement("insert into user(Username,Email,Password) values(?,?,?)");
                insertquery.setString(1,name);
                insertquery.setString(2,email);
                insertquery.setString(3,password);
                insertquery.executeUpdate();
                obj.put("success","successfully registered");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        out.println(obj);
    }
}
