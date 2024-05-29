package com.g10.demo.type.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessApiResponse {
    private String status;
    private Object data;
}
