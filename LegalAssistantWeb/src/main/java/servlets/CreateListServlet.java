package servlets;

import connectors.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CreateListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("err","Еще не засейвлино ничего");
        req.getRequestDispatcher("createlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            DBConnector.executeUpdate("INSERT legal_assistiant.legal_support " +
                    "VALUE('"+id+"', '"+req.getParameter("title")+"', '"+req.getParameter("subtitle")+"'," +
                    " '"+req.getParameter("text")+"', '"+req.getParameter("pdf")+"', '"+req.getParameter("link")+"')");
        } catch (Exception ex) {
            req.setAttribute("err", "Что-то пошло не так, давай поновой");
        }
        req.setAttribute("err", "Все ок, красава");
        req.getRequestDispatcher("createlist.jsp").forward(req, resp);
    }
}
