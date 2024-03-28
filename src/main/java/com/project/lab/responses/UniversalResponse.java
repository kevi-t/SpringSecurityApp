package com.project.lab.responses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UniversalResponse  {
    private String message;
    private String status;
    private Object data;
}