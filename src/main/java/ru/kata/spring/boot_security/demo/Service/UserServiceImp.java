package ru.kata.spring.boot_security.demo.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{

    private final UserRepository userDao;
    private final RoleRepository roleRepository;
    private final Role defaultRole;


    public UserServiceImp(UserRepository userDao, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
        this.defaultRole = roleRepository.getById(1L);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }


    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Set<Role> roleSet = user.getRoles();
        roleSet.add(defaultRole);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        updateUser.setPassword(new BCryptPasswordEncoder().encode(updateUser.getPassword()));
        Set<Role> roleSet = updateUser.getRoles();
        roleSet.add(defaultRole);
        updateUser.setId(id);
        userDao.save(updateUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email);
    }
}
