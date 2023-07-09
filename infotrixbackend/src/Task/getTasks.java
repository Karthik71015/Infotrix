package Task;

import org.json.JSONArray;
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

@WebServlet(name = "getTasks", value = "/getTasks")
public class getTasks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("ACCESS-CONTROL-ALLOW-ORIGIN","*");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String dbName = "jdbc:mysql:// localhost:3306/todolist";
        String dbDriver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "5256MAKK";
        JSONObject obj = new JSONObject();
        String id = request.getParameter("id");
        try{
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbName,userName,password);
            Statement stmt = con.createStatement();
            String query = "select * from task where Userid = '"+id+"';";
            ResultSet rs = stmt.executeQuery(query);
            JSONArray tasksArray = new JSONArray();
            while(rs.next())
            {
                JSONObject taskdetails = new JSONObject();

                String note=rs.getString(3);
                int taskid = rs.getInt(1);
                taskdetails.put("note",note);
                taskdetails.put("taskid",taskid);

                tasksArray.put(taskdetails);

            }
            obj.put("result",tasksArray);
            query="select Email from user where Userid="+id+";";
            rs=stmt.executeQuery(query);
            rs.next();
            String Email=rs.getString(1);
            obj.put("Email",Email);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.println(obj);
    }
}
