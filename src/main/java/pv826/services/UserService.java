package pv826.services;

import net.javaguides.springbootsecurity.dto.UserRegistrationDto;
import pv826.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}