package com.example.SSH_Init.exception;

import com.example.SSH_Init.dto.BaseResponse;
import com.example.SSH_Init.dto.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = {BusinessException.class})
    public <T> BaseResponse<T> businessExceptionHandler(BusinessException e) {
        logger.error("BusinessException", e);
        return BaseResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public BaseResponse<?> authenticationExceptionHandler(AuthenticationException e) {
        logger.error("AuthenticationException", e);
        return BaseResponse.error(ErrorCode.INVALID_CREDENTIALS_ERROR);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public BaseResponse<?> authenticationExceptionHandler(AccessDeniedException e) {
        logger.error("AccessDeniedException", e);
        return BaseResponse.error(ErrorCode.FORBIDDEN_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException", e);
        return BaseResponse.error(ErrorCode.PARAMS_ERROR);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        logger.error("RuntimeException", e);
        return BaseResponse.error(ErrorCode.SYSTEM_ERROR);
    }
}
