package org.isnov.training.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserDTO {
    private long groupId;
    private String name;
    private String last_name;
    private String description;
}
