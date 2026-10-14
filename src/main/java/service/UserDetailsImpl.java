package service;


import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;
import org.jspecify.annotations.Nullable;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {


    private static final long serialVersionUID = 1L;
    private final String name;
    private Long id ;
    private String username ;
    private String email;

    private String password ;

    public UserDetailsImpl(Long id, String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl buils(User user){

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
        Collections.singletonList(authority)

        );
    }
    private Collection<? extends  GrantedAuthority> authorities ;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
