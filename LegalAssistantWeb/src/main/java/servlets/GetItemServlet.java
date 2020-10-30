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

public class GetItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject object = new JSONObject();
        PrintWriter writer = resp.getWriter();

        try {
            ResultSet rs = DBConnector.executeQuery("SELECT id, title, subtitle, text, pdf, link FROM legal_assistiant.legal_support" +
                    " WHERE id = '"+req.getParameter("id")+"'");
            if (rs.next()) {
                object.put("id", rs.getString("id"));
                object.put("title", rs.getString("title"));
                object.put("subtitle", rs.getString("subtitle"));
                object.put("text", rs.getString("text"));
                object.put("pdf", rs.getString("pdf"));
                object.put("link", rs.getString("link"));
            }
            object.put("success", true);
        } catch (Exception ex) {
            object.put("success", false);
        }

        writer.print(object.toString());
    }
}
