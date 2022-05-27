package pl.agarlacz.ehealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pl.agarlacz.ehealthcare.model.entity.UserEntity;
import pl.agarlacz.ehealthcare.repository.UserRepository;
import pl.agarlacz.ehealthcare.request.user.UserLoginRequest;
import pl.agarlacz.ehealthcare.request.user.UserRegisterRequest;
import pl.agarlacz.ehealthcare.response.UserActionResponse;
import pl.agarlacz.ehealthcare.response.UserCheckResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthenticationService {

    @Autowired
    private UserRepository usersRepository;

    public boolean isUserLogged(HttpSession session) {
        Long loggedUserId = this.getLoggedUserId(session);
        return loggedUserId != null;
    }

    public boolean isUserLogged(HttpServletRequest request) {
        return this.isUserLogged(request.getSession());
    }

    public Long getLoggedUserId(HttpSession session) {
        return  (Long) session.getAttribute("LOGGED_USER_ID");
    }

    public Long getLoggedUserId(HttpServletRequest request) {
        return this.getLoggedUserId(request.getSession());
    }

    public Optional<UserEntity> getLoggedUser(HttpServletRequest request) {
        Long loggedUserId = getLoggedUserId(request);

        if(loggedUserId == null) return null;

        Optional<UserEntity> userOptional = this.usersRepository.findById(loggedUserId);
        if(userOptional == null) return null;

        return userOptional;
    }

    public UserCheckResponse checkUser(HttpServletRequest request) {
        Long loggedUserId = getLoggedUserId(request);
        if (loggedUserId == null) {
            return new UserCheckResponse(false, "Currently, You are not logged in.", null);
        }
        Optional<UserEntity> userOptional = this.usersRepository.findById(loggedUserId);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            return new UserCheckResponse(true, "Currently, You are logged in.", userEntity);
        } else {
            return new UserCheckResponse(false, "Logged in user doesn't exists.", null);
        }
    }

    public UserActionResponse loginUser(HttpServletRequest request, UserLoginRequest data) {
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

    public UserActionResponse logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (isUserLogged(session)) {
            session.removeAttribute("LOGGED_USER_ID");
            return new UserActionResponse(true, "Logout operation succeed.");
        }
        return new UserActionResponse(false, "Logout operation failed (Currently, You are not logged in).");
    }

    public UserActionResponse registerUser(UserRegisterRequest data) {

    if(data.getPassword() != data.getPasswordConfirmation()){
        return new UserActionResponse(false, "Register operation failed (passwords are not the same).");
    }

        UserEntity userEntity = new UserEntity(this.usersRepository.count() +1, data.getEmail(), data.getFirstName(),data.getLastName() ,data.getPassword());
        try {
            this.usersRepository.save(userEntity);
            return new UserActionResponse(true, "Register operation succeed.");
        } catch (Throwable ex) {
            if (ex instanceof DataIntegrityViolationException) {
                return new UserActionResponse(false, "Register operation failed (email is used already by someone).");
            }
            return new UserActionResponse(false, "Register operation failed.");
        }
    }
}
