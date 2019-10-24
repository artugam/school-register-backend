package com.artur.engineer.payload;

import com.artur.engineer.engine.views.ApiResponseView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class ApiResponse {

    @JsonView({ApiResponseView.class})
    private Boolean success;

    @JsonView({ApiResponseView.class})
    private String message;

    @JsonView({ApiResponseView.class})
    private Integer status;


    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
        this.status = 200;
    }

    public ApiResponse(Boolean success, String message, Integer status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
