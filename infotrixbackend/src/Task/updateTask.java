package Task;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name = "updateTask", value = "/updateTask")
public class updateTask extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String dbName = "jdbc:mysql:// localhost:3306/todolist";
        String dbDriver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "5256MAKK";
        String Taskid=request.getParameter("id");
        String note = request.getParameter("note");
        int Tid = Integer.parseInt(Taskid);
        JSONObject obj = new JSONObject();
        try{
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbName,userName,password);
            Statement stmt=con.createStatement();
            String query="update task set  Note= '"+note+"' where Taskid="+Tid+";";
            stmt.executeUpdate(query);
            obj.put("response","updated");

        }
        catch (Exception e)
        {
//            obj.put("response","not updated");
            e.printStackTrace();
        }
        out.println(obj);
    }
}
