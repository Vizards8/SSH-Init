package com.example.SSH_Init.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank(message = "Username is mandatory")
    String username;

    @Email
    String email;

    @NotBlank(message = "Password is mandatory")
    String password;
}
