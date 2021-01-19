import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@WebServlet(name = "StatisticServlet", urlPatterns = {"/singleStatistic", "/fullStatistic"})
public class StatisticServlet extends javax.servlet.http.HttpServlet {

    private static final File SINGLE_STATISTIC = new File("c:\\Users\\user\\Documents\\GitHub\\prog.kiev.uaJavaPro\\web\\single_statistic.html");
    private static final File FULL_STATISTIC = new File("c:\\Users\\user\\Documents\\GitHub\\prog.kiev.uaJavaPro\\web\\full_statistic.html");
    private static Map<String, Map<String, Integer>> answers = new ConcurrentHashMap<>();
    static{
        answers.put("admin", new ConcurrentHashMap<>());
        answers.put("user", new ConcurrentHashMap<>());
        answers.put("cat", new ConcurrentHashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        showSingleStatistic(req, resp);
    }

    private void showSingleStatistic(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        String user = (String) session.getAttribute("user_login");
        Map<String, Integer> currentAnswers = answers.get(user);

        String answer1 = "answer1 = " + req.getParameter("question1");
        Optional<Integer> n = Optional.ofNullable(currentAnswers.get(answer1));
        currentAnswers.put(answer1, n.orElse(0) + 1);

        String answer2 = "answer2 = " + req.getParameter("question2");
        n = Optional.ofNullable(currentAnswers.get(answer2));
        currentAnswers.put(answer2, n.orElse(0) + 1);

        printSingleStatistics(user, currentAnswers, resp);
    }

    private void printSingleStatistics(String user, Map<String, Integer> currentAnswers, HttpServletResponse resp) {
        try (BufferedReader br = new BufferedReader(new FileReader(SINGLE_STATISTIC))){
            String str;
            StringBuilder sb = new StringBuilder();
            while( (str = br.readLine()) != null) {
                sb.append(str);
            }
            resp.getWriter().printf(sb.toString(),
                    user,
                    Optional.ofNullable(currentAnswers.get("answer1 = Yes")).orElse(0),
                    Optional.ofNullable(currentAnswers.get("answer2 = Yes")).orElse(0),
                    Optional.ofNullable(currentAnswers.get("answer1 = No")).orElse(0),
                    Optional.ofNullable(currentAnswers.get("answer2 = No")).orElse(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkRights(req, resp))
            return;
        if ("/singleStatistic".equals(req.getServletPath()))
            showSingleStatistic(req, resp);
        else
            showFullStatistic(resp);
    }

    private void showFullStatistic(HttpServletResponse resp) throws IOException {
        Map<String, Integer> fullStats = answers.entrySet()
                .stream()
                .flatMap(innerMapEntry -> innerMapEntry.getValue().entrySet().stream())
                .collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.summingInt(entry -> entry.getValue())));

        printFullStatistic(fullStats, resp);
    }

    private boolean checkRights(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = (String)req.getSession().getAttribute("user_login");
        if (login == null) {
            resp.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }

    private void printFullStatistic(Map<String, Integer> fullStats, HttpServletResponse resp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FULL_STATISTIC))){
            String str;
            StringBuilder sb = new StringBuilder();
            while( (str = br.readLine()) != null) {
                sb.append(str);
            }

            resp.getWriter().printf(sb.toString(),
                    "admin",
                    Optional.ofNullable(answers.get("admin").get("answer1 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("admin").get("answer2 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("admin").get("answer1 = No")).orElse(0),
                    Optional.ofNullable(answers.get("admin").get("answer2 = No")).orElse(0),
                    "user",
                    Optional.ofNullable(answers.get("user").get("answer1 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("user").get("answer2 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("user").get("answer1 = No")).orElse(0),
                    Optional.ofNullable(answers.get("user").get("answer2 = No")).orElse(0),
                    "cat",
                    Optional.ofNullable(answers.get("cat").get("answer1 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("cat").get("answer2 = Yes")).orElse(0),
                    Optional.ofNullable(answers.get("cat").get("answer1 = No")).orElse(0),
                    Optional.ofNullable(answers.get("cat").get("answer2 = No")).orElse(0),
                    //full stats:
                    Optional.ofNullable(fullStats.get("answer1 = Yes")).orElse(0),
                    Optional.ofNullable(fullStats.get("answer2 = Yes")).orElse(0),
                    Optional.ofNullable(fullStats.get("answer1 = No")).orElse(0),
                    Optional.ofNullable(fullStats.get("answer2 = No")).orElse(0)
                    );
        }
    }
}
