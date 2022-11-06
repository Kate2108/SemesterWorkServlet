package services.validators;

import services.MessageForUser;
import services.UserService;
import services.db.DBConnector;
import services.repository.UsersRepository;

import javax.servlet.http.HttpServletRequest;


public interface Validator {
    MessageForUser validate(UserService service, DBConnector connector, HttpServletRequest request);
}
