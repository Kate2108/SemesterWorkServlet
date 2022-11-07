package servlets;


import models.User;
import services.AchievementService;
import services.UserService;
import services.db.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/stats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        UserService userService = (UserService) getServletContext().getAttribute("user-service");
        AchievementService achievementService = (AchievementService) getServletContext().getAttribute("ach-service");
        try {
            User user = userService.findUserId((Integer) req.getSession().getAttribute("id"), connector.getConnection());

            if (!req.getParameter("newCategory").equals("")) {
                if (user.getCategory().equals("no category")) {
                    achievementService.updateAch(connector.getConnection(), (Integer) req.getSession().getAttribute("id"), 4);
                }
                userService.updateUserStat(
                        user.getId(),
                        "category",
                        req.getParameter("newCategory"),
                        connector.getConnection());
            }
            if (!req.getParameter("exp").equals("")) {
                userService.updateUserStat(
                        user.getId(),
                        "workexp",
                        req.getParameter("exp"),
                        connector.getConnection());
            }
            if (!req.getParameter("newClass").equals("")) {
                userService.updateUserStat(
                        user.getId(),
                        "class",
                        req.getParameter("newClass"),
                        connector.getConnection());
            }
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
