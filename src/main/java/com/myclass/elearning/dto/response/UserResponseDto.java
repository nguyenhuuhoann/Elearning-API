package com.myclass.elearning.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class UserResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String email;

    private String fullName;

    private String phoneNumber;

    private Boolean enabled;

}
