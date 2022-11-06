package services.validators;

import services.MessageForUser;
import services.UserService;
import services.db.DBConnector;

import javax.servlet.http.HttpServletRequest;

public class ValidatorImplRegistration implements Validator{
    @Override
    public MessageForUser validate(UserService service, DBConnector connector, HttpServletRequest request) {
        String email = request.getParameter("email");
        if (service.findUserEmail(email, connector.getConnection()) != null){
            return new MessageForUser(false,"User with this email has already exist");
        }
        return new MessageForUser(true);
    }
}
