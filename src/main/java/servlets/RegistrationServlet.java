package servlets;

import models.User;
import services.MessageForUser;
import services.UserService;
import services.db.DBConnector;
import services.validators.Validator;
import services.validators.ValidatorImplRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        UserService userService = (UserService) getServletContext().getAttribute("user-service");
        Validator validator = new ValidatorImplRegistration();
        MessageForUser message = validator.validate(userService, connector, req);
        if (message.isSuccessful) {
                    userService.addUser(
                    User.builder()
                            .email(req.getParameter("email"))
                            .username(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .gender(req.getParameter("gender"))
                            .country(req.getParameter("country"))
                            .sport(req.getParameter("sport"))
                            .build(), connector.getConnection()
            );
            req.setAttribute("is_registered", "true");
            req.getSession().setAttribute("is_registered", "true");
            resp.sendRedirect(req.getContextPath());
        }
        else{
            req.setAttribute("is_registered", "false");
            req.getSession().setAttribute("is_registered", "false");
            req.setAttribute("failedDescription", message.getDescription());
        }
    }
}
