package org.isnov.training.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lName;
    private String resume;
    private UserGroupDTO group;
    private List<UserPropertyResponseDTO> properties;
}
