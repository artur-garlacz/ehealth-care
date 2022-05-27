package pl.agarlacz.ehealthcare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/api/user/me")
    public ResponseEntity checkUser(HttpServletRequest request) {
        return ResponseEntity.ok(this.authenticationService.checkUser(request));
    }

    @GetMapping("/api/user/me")
    public ResponseEntity checkUser(HttpServletRequest request) {
        return ResponseEntity.ok(this.authenticationService.checkUser(request));
    }
}
