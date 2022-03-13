package com.getir.project.bookretail.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    private String status;
    private String message;
    private List<String> error;

    public BaseResponse (){
        error = new ArrayList<>();
    }
}
