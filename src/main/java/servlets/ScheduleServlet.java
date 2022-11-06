package servlets;

import models.Schedule;
import services.GroupService;
import services.MessageForUser;
import services.ScheduleService;
import services.db.DBConnector;
import services.validators.ValidatorImplSchedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        ScheduleService scheduleService = (ScheduleService) getServletContext().getAttribute("sch-service");

        Collection<Schedule> collection = scheduleService.getUserSchedule(connector.getConnection(), (Integer) req.getSession().getAttribute("id"));
        req.getSession().setAttribute("schedule", collection);
        req.getRequestDispatcher("WEB-INF/pages/schedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnector connector = (DBConnector) getServletContext().getAttribute("dbConnector");
        ScheduleService scheduleService = (ScheduleService) getServletContext().getAttribute("sch-service");
        GroupService groupService = (GroupService) getServletContext().getAttribute("group-service");
        ValidatorImplSchedule validator = new ValidatorImplSchedule();

        if (!(req.getParameter("day") == null) &&
                !(req.getParameter("groupName") == null) &&
                !(req.getParameter("hourS").equals("")) &&
                !(req.getParameter("minuteS").equals("")) &&
                !(req.getParameter("hourE").equals("")) &&
                !(req.getParameter("minuteE").equals(""))){
            int hs = Integer.parseInt(req.getParameter("hourS"));
            int ms = Integer.parseInt(req.getParameter("minuteS"));
            int he = Integer.parseInt(req.getParameter("hourE"));
            int me = Integer.parseInt(req.getParameter("minuteE"));

            MessageForUser message = validator.validateSchedule(hs, ms, he, me);

            if (message.isSuccessful) {
                if (groupService.findGroup(req.getParameter("groupName"),(Integer) req.getSession().getAttribute("id"), connector.getConnection()) != null) {
                    if(!scheduleService.addUserSchedule(connector.getConnection(),
                            (Integer) req.getSession().getAttribute("id"),
                            Schedule.builder()
                                    .day(req.getParameter("day"))
                                    .group(req.getParameter("groupName"))
                                    .hourStart(Integer.parseInt(req.getParameter("hourS")))
                                    .minuteStart(Integer.parseInt(req.getParameter("minuteS")))
                                    .hourEnd(Integer.parseInt(req.getParameter("hourE")))
                                    .minuteEnd(Integer.parseInt(req.getParameter("minuteE")))
                                    .build())){
                        req.setAttribute("done", "false");
                        req.setAttribute("failedDescription", "You can add only one training for group per day. You cannot add more than one training on one period as well.");
                        req.getRequestDispatcher("WEB-INF/pages/schedule.jsp").forward(req, resp);
                    }
                    req.setAttribute("done", "true");
                    resp.sendRedirect(req.getContextPath() + "/schedule");
                }
                else {
                    req.setAttribute("done", "false");
                    req.setAttribute("failedDescription", "This group does not exist");
                    req.getRequestDispatcher("WEB-INF/pages/schedule.jsp").forward(req, resp);
                }
            }
            else{
                req.setAttribute("done", "false");
                req.setAttribute("failedDescription", message.getDescription());
                req.getRequestDispatcher("WEB-INF/pages/schedule.jsp").forward(req, resp);
            }
        }
        else if (!(req.getParameter("Dday") == null) && !(req.getParameter("groupDName") == null)){
            scheduleService.deleteUserSchedule(connector.getConnection(), (Integer) req.getSession().getAttribute("id"),
                    Schedule.builder()
                        .day(req.getParameter("Dday"))
                        .group(req.getParameter("groupDName"))
                        .build());
            resp.sendRedirect(req.getContextPath() +"/schedule");
        }
        else {
            req.getRequestDispatcher("WEB-INF/pages/schedule.jsp").forward(req, resp);
        }
    }
}
