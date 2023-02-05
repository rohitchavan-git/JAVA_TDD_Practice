package com.rohit.umtdd.api;

import com.eclipsesource.json.JsonObject;
import com.rohit.umtdd.domain.RegistrationData;
import com.rohit.umtdd.domain.User;
import com.rohit.umtdd.exception.UserAlreadyInUseException;
import com.rohit.umtdd.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    public String createUser(RegistrationData registrationData ,
                           HttpServletResponse response) {

        try {
            User user = userService.createUser(registrationData);
            response.setStatus(201);
            response.setContentType("application/json");
            return jsonFor(user);
        } catch (UserAlreadyInUseException e) {
            response.setStatus(400);
            return "Username already in use.";
        }
    }

    private static String jsonFor(User user) {
        return new JsonObject()
                .add("username" , user.getUsername())
                .add("about" , user.getAbout())
                .toString();
    }


}
