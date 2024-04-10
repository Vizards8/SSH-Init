package com.example.SSH_Init.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "Request parameter error"),
    NOT_LOGIN_ERROR(40100, "Not logged in"),
    NO_AUTH_ERROR(40101, "Unauthorized access"),
    INVALID_CREDENTIALS_ERROR(40102, "Incorrect credentials, please try again."),
    ACCOUNT_LOCKED_ERROR(40103, "User account is locked"),
    FORBIDDEN_ERROR(40300, "Access forbidden"),
    NOT_FOUND_ERROR(40400, "Requested data not found"),
    USERNAME_ALREADY_EXISTS_ERROR(40901, "Username already exists"),
    EMAIL_ALREADY_EXISTS_ERROR(40902, "Email already exists"),
    SYSTEM_ERROR(50000, "Internal system exception"),
    OPERATION_ERROR(50001, "Operation failed");

    private final int code;

    private final String message;
}
