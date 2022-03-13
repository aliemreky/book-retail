package com.getir.project.bookretail.exception;

import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.util.ServiceUtils;
import com.getir.project.bookretail.util.constant.ErrorConstant;
import com.getir.project.bookretail.util.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = {Exception.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleSystemException(final Exception ex) {
        log.error(ServiceUtils.getStackTrace(ex));
        BaseResponse response = new BaseResponse();
        response.setStatus(StatusEnum.FAILED.getValue());
        response.setMessage(ErrorConstant.SYSTEM_ERROR);
        return response;
    }

    @ExceptionHandler(value = {DateTimeParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleDateTimeParseException(final DateTimeParseException ex) {
        log.error(ServiceUtils.getStackTrace(ex));
        BaseResponse response = new BaseResponse();
        response.setStatus(StatusEnum.FAILED.getValue());
        response.setMessage(ErrorConstant.DATE_NOT_VALID);
        response.getError().add(ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleUsernameNotFoundException(final UsernameNotFoundException ex) {
        log.error(ServiceUtils.getStackTrace(ex));
        BaseResponse response = new BaseResponse();
        response.setStatus(StatusEnum.FAILED.getValue());
        response.setMessage(ErrorConstant.USER_NOT_FOUND);
        response.getError().add(ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public BaseResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.error(ServiceUtils.getStackTrace(ex));
        BaseResponse response = new BaseResponse();

        BindingResult result = ex.getBindingResult();
        for (FieldError f : result.getFieldErrors()) {
            response.getError().add(f.getField() + " : " + f.getDefaultMessage());
        }

        response.setStatus(StatusEnum.FAILED.getValue());
        response.setMessage(ErrorConstant.METHOD_ARG_NOT_VALID);
        return response;
    }


}
