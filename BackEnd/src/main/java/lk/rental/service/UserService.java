package lk.rental.service;

import lk.rental.dto.UserDTO;
import lk.rental.entity.User;

import java.util.ArrayList;

public interface UserService {

    void addUser(UserDTO userDTO);

    ArrayList<UserDTO> getAllUsers();

    String getPasswordByUsername(String username);

    User findUser(UserDTO userDTO);
}
