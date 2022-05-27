package pl.agarlacz.ehealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.agarlacz.ehealthcare.request.user.UserLoginRequest;
import pl.agarlacz.ehealthcare.request.user.UserRegisterRequest;
import pl.agarlacz.ehealthcare.response.UserActionResponse;
import pl.agarlacz.ehealthcare.response.UserCheckResponse;
import pl.agarlacz.ehealthcare.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/user/check")
    public ResponseEntity<UserCheckResponse> checkUser(HttpServletRequest request) {
        return ResponseEntity.ok(this.authenticationService.checkUser(request));
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserActionResponse> loginUser(HttpServletRequest request, @RequestBody UserLoginRequest data) {
        return ResponseEntity.ok(this.authenticationService.loginUser(request, data));
    }

    @PostMapping("/api/user/logout")
    public ResponseEntity<UserActionResponse> logoutUser(HttpServletRequest request) {
        return ResponseEntity.ok(this.authenticationService.logoutUser(request));
    }

    @PostMapping("/api/user/register")
    public ResponseEntity<UserActionResponse> registerUser(@RequestBody UserRegisterRequest data) {
        return ResponseEntity.ok(this.authenticationService.registerUser(data));
    }
}
