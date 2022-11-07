package servlets;

import models.Achievement;
import services.AchievementService;
import services.db.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/my_achievements")
public class UserAchievementsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        AchievementService achievementService = (AchievementService) getServletContext().getAttribute("ach-service");
        try {
            Collection<Achievement> collection = achievementService.getUserAchievements(connector.getConnection(), (Integer) req.getSession().getAttribute("id"));
            req.setAttribute("achievements", collection);
            req.getRequestDispatcher("/WEB-INF/pages/user_achievements.jsp").forward(req, resp);
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
