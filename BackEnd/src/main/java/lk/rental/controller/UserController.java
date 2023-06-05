package lk.rental.controller;

import lk.rental.dto.UserDTO;
import lk.rental.entity.User;
import lk.rental.service.UserService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseUtil saveUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return new ResponseUtil("200", " Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllUsers() {
        ArrayList<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseUtil("200", " Success.!", allUsers);
    }

    @PostMapping("/signin")
    public ResponseUtil signinUser(@RequestBody UserDTO userDTO) {
        User user = userService.findUser(userDTO);
        System.out.println(user);
        if (user == null) {
            return new ResponseUtil("200", " Added.!", null);
        }  else {
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setUsername(user.getUsername());
            userDTO1.setUserCategory(user.getUserCategory());
            return new ResponseUtil("200", " Added.!", userDTO1);
        }

    }
}
