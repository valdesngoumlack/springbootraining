package org.isnov.training.app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppExceptionDTO {
    private String code;
    private String message;
}
