package com.xuantoan.springboot.controller.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomReponse {
    private HttpStatus status;
    private String message;
}
