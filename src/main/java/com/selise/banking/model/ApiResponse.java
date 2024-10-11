package com.selise.banking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private HttpStatus status;

    private String message;

    private T success;

    public ApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> buildSuccessRestResponse(HttpStatus httpStatus, String message, T klass) {
        return new ApiResponse<>(httpStatus, message, klass);
    }

    public static <T> ApiResponse<T> buildErrorRestResponse(HttpStatus httpStatus, String message) {
        return new ApiResponse<>(httpStatus, message);
    }
}
