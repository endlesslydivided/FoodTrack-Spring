package com.divided.foodtrack.security.jwt;

import com.divided.foodtrack.logging.Loggable;
import com.divided.foodtrack.models.Users;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    @Loggable
    public static JwtUser create(Users user) {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add(user.isAdmin() ? "ADMIN" : "USER");
        return new JwtUser(
                user.getId(),
                user.getUserLogin(),
                user.getUserPassword(),
                mapToGrantedAuthorities(authorities),
                true
        );
    }

    @Loggable
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role)
                ).collect(Collectors.toList());
    }
}
