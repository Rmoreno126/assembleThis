package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON output
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    // Default constructor for frameworks
    public ApiResponse() {
    }

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // Convenience constructors
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, null, message);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
