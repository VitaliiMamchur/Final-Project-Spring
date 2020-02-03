package ua.mamchur.springproject.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.mamchur.springproject.model.Role;
import ua.mamchur.springproject.model.User;
import ua.mamchur.springproject.repository.RoleRepository;
import ua.mamchur.springproject.repository.UserRepository;
import ua.mamchur.springproject.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    public User findByUserDetails(UserDetails user) {
        return userRepository.getByUsername(user.getUsername());
    }

    @Override
    public User create(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {

            return null;
        }

        user.setActive(true);
        user.getRoles().add(roleRepository.findByRole(Role.Names.ROLE_USER));

        return userRepository.save(user);
    }

    @Override
    public void edit() {

    }
}