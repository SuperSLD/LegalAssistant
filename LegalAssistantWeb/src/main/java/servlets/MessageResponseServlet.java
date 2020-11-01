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
import java.util.ArrayList;

public class MessageResponseServlet extends HttpServlet {
    private static FindResponse FIND_RESPONSE = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (FIND_RESPONSE == null) FIND_RESPONSE = new FindResponse();

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
                    object.put("response_text", FIND_RESPONSE.checkMessage(req.getParameter("text")));
            }

            object.put("success", true);
        } catch (Exception ex) {
            object.put("success", false);
            ex.printStackTrace();
        }

        writer.print(object.toString());
    }

    // Пароль 4237

    /**
     * Попытки выцепить что-то похожее на ответ.
     */
    private static class FindResponse {
        private ArrayList<String> title = new ArrayList<>();
        private ArrayList<String> response = new ArrayList<>();
        private ArrayList<WordsDate> wordsDates = new ArrayList<>();

        private static class WordsDate {
            private ArrayList<String> words = new ArrayList<>();
            private ArrayList<Integer> count = new ArrayList<>();;

            public void addWord(String word) {
                if (words.indexOf(word) < 0 ) {
                    words.add(word);
                    count.add(1);
                } else {
                    count.set(words.indexOf(word), count.get(words.indexOf(word)) + 1);
                }
            }

            public int getCount(String word) {
                if (words.indexOf(word) >= 0) {
                    return count.get(words.indexOf(word));
                } else {
                    return 0;
                }
            }
        }

        public FindResponse() {
            try {
                ResultSet rs = DBConnector.executeQuery("SELECT title, subtitle, text FROM legal_assistiant.legal_support");
                while (rs.next()) {
                    title.add(rs.getString("title"));
                    response.add(rs.getString("subtitle"));
                    WordsDate wordsDate = new WordsDate();
                    for (String str : rs.getString("text").split(" ")) {
                        wordsDate.addWord(str);
                    }
                    wordsDates.add(wordsDate);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public String checkMessage(String message) {
            int[] sum = new int[wordsDates.size()];

            int max = -1;
            int index = -1;

            for (int i = 0 ; i < wordsDates.size(); i++) {
                for (String str : message.split(" ")) {
                    sum[i] += wordsDates.get(i).getCount(str);
                }
                if (sum[i] > max) {
                    max = sum[i];
                    index = i;
                }
            }

            return "Возможно вас интересует раздел \"" + title.get(index) +
                    "\" В нем информация на тему: " + response.get(index);
        }
    }
}
