package com.myclass.elearning.service;

import com.myclass.elearning.dto.response.UserResponseDto;
import com.myclass.elearning.security.vo.UserPrincipal;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResponseDto> findAll(Integer page, Integer size);

    UserResponseDto findByUsername(String username, UserPrincipal currentUser);

}
