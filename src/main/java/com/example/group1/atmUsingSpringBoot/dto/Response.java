package com.example.group1.atmUsingSpringBoot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success;
    private String message;
    private Object data;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
