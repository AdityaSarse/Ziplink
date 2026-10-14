package service;


import jakarta.transaction.Transactional;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import repository.UserReository;

import java.util.Optional;

@Service
public class UserServicesDetailsImpl {

    @Autowired
    UserReository repository ;

    @Override
    @Transactional
    public UserDetails loadUserByUserId(String username) throws UsernameNotFoundException{
        User user = repository.findByUsername(username)
                .orElseThrow(() ->new UsernameNotFoundException(" User Not found with username"+username));

        return UserDetailsImpl.buils(user);
    }
}
