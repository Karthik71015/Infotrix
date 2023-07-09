package Task;

import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "AddTask", value = "/AddTask")
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("id");
        String note = request.getParameter("note");
        int uid = Integer.parseInt(userid);
        String dbName = "jdbc:mysql:// localhost:3306/todolist";
        String dbDriver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "5256MAKK";
        JSONObject obj = new JSONObject();
        try{
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbName,userName,password);
            PreparedStatement insertquery = con.prepareStatement("insert into task(Userid,Note) values(?,?);");
            insertquery.setInt(1,uid);
            insertquery.setString(2,note);
            insertquery.executeUpdate();
            obj.put("success","task added");

        }
        catch(Exception e)
        {
            e.printStackTrace();
            obj.put("success","not added");
        }
        out.println(obj);
    }
}
