package com.g10.demo.type.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessApiResponse {
    private String status;
    private final Date timestamp = new Date();
    private Object data;
}
