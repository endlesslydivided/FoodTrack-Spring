package com.divided.foodtrack.security;


import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.Users;
import com.divided.foodtrack.repositories.UsersRepository;
import com.divided.foodtrack.security.jwt.JwtUserFactory;
import com.divided.foodtrack.services.impl.AuthAndRegServiceImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUserDetailsService implements UserDetailsService {
    UsersRepository usersRepository;

    @Autowired
    public JwtUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @SneakyThrows
    @Override
    @Loggable
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.getByName(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        return JwtUserFactory.create(user.get());
    }
}
