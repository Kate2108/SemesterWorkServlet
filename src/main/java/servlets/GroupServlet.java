package servlets;

import models.Group;
import services.AchievementService;
import services.GroupService;
import services.db.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        GroupService groupService = (GroupService) getServletContext().getAttribute("group-service");

        Collection<Group> collection = groupService.findGroups(connector.getConnection(),(Integer) req.getSession().getAttribute("id") );
        req.setAttribute("groups", collection);
        req.getRequestDispatcher("WEB-INF/pages/groups.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        AchievementService achievementService = (AchievementService) getServletContext().getAttribute("ach-service");
        GroupService groupService = (GroupService) getServletContext().getAttribute("group-service");
        try {
            if (!(req.getParameter("addGroup") == null) && !(req.getParameter("addCount").equals(""))) {
                groupService.addGroup((Integer) req.getSession().getAttribute("id"), Group.builder()
                        .name(req.getParameter("addGroup"))
                        .countOfMembers(Integer.parseInt(req.getParameter("addCount")))
                        .build(), connector.getConnection());
                achievementService.updateAch(connector.getConnection(), (Integer) req.getSession().getAttribute("id"), 2);
                achievementService.updateAch(connector.getConnection(), (Integer) req.getSession().getAttribute("id"), 3);
            }
            if (!(req.getParameter("deleteName") == null)) {
                groupService.deleteGroup(req.getParameter("deleteName"), (Integer) req.getSession().getAttribute("id"), connector.getConnection());
                achievementService.updateAch(connector.getConnection(), (Integer) req.getSession().getAttribute("id"), 5);
            }

            if (!(req.getParameter("name") == null) && !(req.getParameter("newCount").equals(""))) {
                groupService.updateGroup(Group.builder()
                        .name(req.getParameter("name"))
                        .countOfMembers(Integer.parseInt(req.getParameter("newCount")))
                        .build(), (Integer) req.getSession().getAttribute("id"), req.getParameter("newCount"), connector.getConnection());
            }

            resp.sendRedirect(req.getContextPath() + "/groups");
        }
        catch (IllegalArgumentException e){
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req,resp);
        }
    }
}
