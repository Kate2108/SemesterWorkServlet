package servlets;

import models.User;
import services.UserService;
import services.db.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        UserService userService = (UserService) getServletContext().getAttribute("user-service");

        try {
            User user = userService.findUserId((Integer) req.getSession().getAttribute("id"), connector.getConnection());

            req.setAttribute("email", user.getEmail());
            req.setAttribute("country", user.getCountry());
            req.setAttribute("gender", user.getGender());
            req.setAttribute("sport", user.getSport());
            req.setAttribute("category", user.getCategory());
            req.setAttribute("class", user.getAClass());
            req.setAttribute("exp", user.getExp());
            req.setAttribute("countOfGroups", user.getCountOfGroups());
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
