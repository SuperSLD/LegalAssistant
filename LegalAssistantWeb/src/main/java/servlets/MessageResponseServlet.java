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

public class MessageResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("windows-1251");

        JSONObject object = new JSONObject();
        PrintWriter writer = resp.getWriter();

        try {

            switch (req.getParameter("text")) {
                case "Привет":
                    object.put("response_text", "Здравствуйте");
                    break;
                case "Что на счет питсы?":
                    object.put("response_text", "Ха ха ха. Я хочу питсу.");
                    break;
                default:
                    object.put("response_text", "Попробуйте выразиться яснее.");
            }

            object.put("success", true);
        } catch (Exception ex) {
            object.put("success", false);
        }

        writer.print(object.toString());
    }
}
