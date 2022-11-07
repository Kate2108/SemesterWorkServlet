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
import java.util.Collection;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("user-service");
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        try {
            Collection<User> collection = userService.getUsers(connector.getConnection());
            req.setAttribute("users", collection);
            req.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(req, resp);
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("user-service");
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        try {
            if (!req.getParameter("deleteId").equals("")) {
                if(!((Integer) req.getSession().getAttribute("id") == Integer.parseInt(req.getParameter("deleteId")))) {
                    userService.deleteUser(Integer.parseInt(req.getParameter("deleteId")), connector.getConnection());
                }
            }
            if (!req.getParameter("countryId").equals("") && !req.getParameter("newCountry").equals("")) {
                userService.updateUserCountry(Integer.parseInt(req.getParameter("countryId")), req.getParameter("newCountry"), connector.getConnection());
            }
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
