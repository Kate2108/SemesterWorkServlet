package services.validators;

import models.User;
import services.MessageForUser;
import services.UserService;
import services.db.DBConnector;

import javax.servlet.http.HttpServletRequest;

public class ValidatorImplAuth implements Validator{
    @Override
    public MessageForUser validate(UserService service, DBConnector connector, HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = service.findUserEmail(email, connector.getConnection());
        if(user == null){
            return new MessageForUser(false, "There is no user with this email. Please try again.");
        }

        else{
            if(service.checkUserPassword(user, password)){
                request.getSession().setAttribute("id",user.getId());
                request.getSession().setAttribute("username",user.getUsername());
                if (user.getStatus().equals("admin")){
                    request.getSession().setAttribute("status", "admin");
                }
                else {
                    request.getSession().setAttribute("status", "user");
                }
                return new MessageForUser(true);
            }
            else {
                return new MessageForUser(false,"Incorrect password. Please try again." );
            }
        }
    }
}
