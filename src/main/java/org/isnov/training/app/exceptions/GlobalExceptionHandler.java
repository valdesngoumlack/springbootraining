package org.isnov.training.app.exceptions;

import org.isnov.training.app.dto.AppExceptionDTO;
import org.isnov.training.app.dto.AppResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public AppResponseDTO handleException(Exception exception){
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        appResponseDTO.setStatus(-1);
        appResponseDTO.setError(new AppExceptionDTO("code", exception.getMessage()));
        appResponseDTO.setContent(appResponseDTO.getError());

        if(exception instanceof AdempiereException){
            appResponseDTO.setError(new AppExceptionDTO("AD", exception.getMessage()));
        }

        return appResponseDTO;
    }

    @ExceptionHandler(AdempiereException.class)
    public AppResponseDTO handleException(AdempiereException exception){
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        appResponseDTO.setStatus(-1);
        appResponseDTO.setError(new AppExceptionDTO("AD", exception.getMessage()));
        appResponseDTO.setContent(appResponseDTO.getError());

        return appResponseDTO;
    }
}
