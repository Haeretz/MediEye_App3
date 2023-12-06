package kr.co.dgall.medieye_app3.dto;

import lombok.Getter;

/**
 * REST API 공통응답
 * @param <T>
 */
@Getter
public class ApiResponse<T> {
    private static final String SUCCESS_STATUS = "200";
    private static final String ERROR_STATUS = "400";
    
    private String status;
    private String message;
    private T data;
   

    public static <T> ApiResponse<T> createSuccess(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null);
    }
    
    public static <T> ApiResponse<T> createSuccess(T data, String message) {
        return new ApiResponse<>(SUCCESS_STATUS, data, message);
    }
    

    public static ApiResponse<?> createSuccessWithNoContent() {
        return new ApiResponse<>(SUCCESS_STATUS, null, null);
    }

    public static ApiResponse<?> createError(String message) {
        return new ApiResponse<>(ERROR_STATUS, null, message);
    }

    private ApiResponse(String status, T data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
        
    }
}
