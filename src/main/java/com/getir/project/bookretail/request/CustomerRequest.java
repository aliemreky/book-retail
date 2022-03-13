package com.getir.project.bookretail.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
public class CustomerRequest {

    private Long id;

    @NotBlank(message = "fullName" + NOT_BLANK_FIELD)
    public String fullName;

    @NotBlank(message = "email" + NOT_BLANK_FIELD)
    public String email;

    @NotBlank(message = "password" + NOT_BLANK_FIELD)
    public String password;
}
