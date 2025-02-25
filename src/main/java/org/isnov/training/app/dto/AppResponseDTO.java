package org.isnov.training.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppResponseDTO {
    private Integer status;
    private Object content; //data
    private AppExceptionDTO error;

    public AppResponseDTO(){
        setStatus(1);
        setError(new AppExceptionDTO());
    }
}
