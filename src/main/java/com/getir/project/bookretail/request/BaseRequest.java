package com.getir.project.bookretail.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.getir.project.bookretail.util.ResponseMessage.EMAIL_NOT_VALID;
import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
public class BaseRequest {

    @NotBlank(message = "userEmail" + NOT_BLANK_FIELD)
    @Email(message = EMAIL_NOT_VALID)
    private String userEmail;
}
