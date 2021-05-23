package com.myclass.elearning.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserSignUpRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String useName;

    private String email;

    private String password;

    private String passwordVerification;


}
