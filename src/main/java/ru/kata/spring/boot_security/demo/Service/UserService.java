package ru.kata.spring.boot_security.demo.Service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();


    List<Role> getRoles();

    User getUserById(Long id);


    @Transactional
    void save(User user);

    @Transactional
    void update(Long id, User updateUser);

    @Transactional
    void deleteById(Long id);
}
