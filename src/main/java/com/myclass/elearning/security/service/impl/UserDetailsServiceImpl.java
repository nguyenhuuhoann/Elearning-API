package com.myclass.elearning.security.service.impl;

import com.myclass.elearning.entity.UserEntity;
import com.myclass.elearning.exception.ResourceNotFoundException;
import com.myclass.elearning.repository.UserRepository;
import com.myclass.elearning.security.vo.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String NOT_FOUND_BY_USERNAME_FORMAT = "Not found by %s";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_BY_USERNAME_FORMAT, username)));
        return new UserPrincipal(user);
    }
}
