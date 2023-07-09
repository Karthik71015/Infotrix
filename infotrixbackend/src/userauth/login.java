package userauth;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");;
        response.addHeader("Access-Control-Allow-Origin","*");
        PrintWriter out=response.getWriter();
        String dbName = "jdbc:mysql:// localhost:3306/todolist";
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "5256MAKK";
        String mail=request.getParameter("mail");
        String pass=request.getParameter("pass");
        System.out.println(mail);
        System.out.println(password);
        JSONObject obj = new JSONObject();
        try{
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbName,userName,password);
            Statement stmt=con.createStatement();
            String sql="SELECT * FROM user where email='"+mail+"'and "+"password='"+pass+"'";
            ResultSet rs = stmt.executeQuery(sql);
            Boolean ifExist=rs.next();
            if(ifExist) {
                obj.put("success","login successfully");
                int id = rs.getInt(1);
                obj.put("userid",id);

            }
            else{
                obj.put("success","login failed incorrect username or password");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.println(obj);
    }
}
