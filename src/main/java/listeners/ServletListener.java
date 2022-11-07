package listeners;

import services.AchievementService;
import services.GroupService;
import services.ScheduleService;
import services.UserService;
import services.db.DBConnector;
import repositories.impl.AchievementRepositoryImpl;
import repositories.impl.GroupRepositoryImpl;
import repositories.impl.ScheduleRepositoryImpl;
import repositories.impl.UsersRepositoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("dbConnector", new DBConnector());
        sce.getServletContext().setAttribute("user-service", new UserService(new UsersRepositoryImpl()));
        sce.getServletContext().setAttribute("ach-service", new AchievementService(new AchievementRepositoryImpl()));
        sce.getServletContext().setAttribute("group-service", new GroupService(new GroupRepositoryImpl()));
        sce.getServletContext().setAttribute("sch-service", new ScheduleService(new ScheduleRepositoryImpl()));
    }
}
