package lk.rental.service.impl;

import lk.rental.dto.UserDTO;
import lk.rental.entity.User;
import lk.rental.repo.UserRepo;
import lk.rental.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<ArrayList<UserDTO>>() {}.getType());
    }

    @Override
    public String getPasswordByUsername(String username) {
        System.out.println(username);
        User user = userRepo.findByUsername(username);
        return user.getPassword();
    }

    @Override
    public User findUser(UserDTO userDTO) {
        User user = userRepo.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        return user;
    }
}
