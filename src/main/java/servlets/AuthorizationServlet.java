package servlets;

import services.AchievementService;
import services.MessageForUser;
import services.UserService;
import services.db.DBConnector;
import services.validators.Validator;
import services.validators.ValidatorImplAuth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/log_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        UserService userService = (UserService) getServletContext().getAttribute("user-service");
        AchievementService achievementService = (AchievementService) getServletContext().getAttribute("ach-service");
        Validator validator = new ValidatorImplAuth();

        try{
            MessageForUser message = validator.validate(userService, connector, req);
            if (message.isSuccessful()) {
                achievementService.addUserAchievements(connector.getConnection(),(Integer)req.getSession().getAttribute("id"));
                achievementService.updateAch(connector.getConnection(), (Integer)req.getSession().getAttribute("id"), 1);
                req.getSession().setAttribute("is_authorized","true");
                resp.sendRedirect(req.getContextPath());
            }
            else {
                req.getSession().setAttribute("is_authorized", "false");
                req.setAttribute("is_authorized", "false");
                req.setAttribute("failedDescription", message.getDescription());
                req.getRequestDispatcher("/WEB-INF/pages/log_in.jsp").forward(req, resp);
            }
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
