package com.myclass.elearning.dto.response;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserSignInResponseDto extends UserResponseDto {

    private String jwtToken;

}
