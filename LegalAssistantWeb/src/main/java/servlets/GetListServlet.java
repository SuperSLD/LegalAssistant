package servlets;

import connectors.DBConnector;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class GetListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject object = new JSONObject();
        PrintWriter writer = resp.getWriter();

        try {
            JSONArray array = new JSONArray();
            ResultSet rs = DBConnector.executeQuery("SELECT id, title FROM legal_assistiant.legal_support");
            while (rs.next()) {
                JSONObject subItem = new JSONObject();
                subItem.put("id", rs.getString("id"));
                subItem.put("title", rs.getString("title"));
                array.put(subItem);
            }
            object.put("list", array);
            object.put("success", true);
        } catch (Exception ex) {
            object.put("success", false);
        }

        writer.print(object.toString());
    }
}
