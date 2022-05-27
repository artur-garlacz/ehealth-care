package pl.agarlacz.ehealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.agarlacz.ehealthcare.model.entity.UserEntity;
import pl.agarlacz.ehealthcare.repository.UserRepository;
import pl.agarlacz.ehealthcare.response.statics.GetItemResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public GetItemResponse<UserEntity> getUser(HttpServletRequest request) {
        Optional<UserEntity> loggedUser = authenticationService.getLoggedUser(request);

        HttpSession session = request.getSession();
        if (this.isUserLogged(session)) {
            return new UserActionResponse(false, "Login operation failed (You are already logged in).");
        }
        Optional<UserEntity> userOptional = this.usersRepository.findByEmailAndPassword(data.getEmail(), data.getPassword());
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            session.setAttribute("LOGGED_USER_ID", userEntity.getId());
            return new UserActionResponse(true, "Login operation succeed.");
        }
        return new UserActionResponse(false, "Login operation failed (Incorrect user credentials).");
    }
}
