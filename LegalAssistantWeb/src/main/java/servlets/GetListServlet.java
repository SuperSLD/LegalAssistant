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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("windows-1251");

        JSONObject object = new JSONObject();
        PrintWriter writer = resp.getWriter();

        try {
            JSONArray array = new JSONArray();
            ResultSet rs = DBConnector.executeQuery("SELECT id, title, subtitle, text, pdf, link FROM legal_assistiant.legal_support");
            while (rs.next()) {
                JSONObject subItem = new JSONObject();
                subItem.put("id", rs.getString("id"));
                subItem.put("title", rs.getString("title"));
                subItem.put("subtitle", rs.getString("subtitle"));
                subItem.put("text", rs.getString("text").replaceAll("@ln", ""));
                subItem.put("pdf", rs.getString("pdf"));
                subItem.put("link", rs.getString("link"));
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
