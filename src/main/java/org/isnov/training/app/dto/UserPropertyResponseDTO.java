package org.isnov.training.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPropertyResponseDTO {
    private Long id;
    private String name;
    private String value;
}
