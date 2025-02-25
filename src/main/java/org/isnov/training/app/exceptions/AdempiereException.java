package org.isnov.training.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdempiereException extends RuntimeException {
  private String messageTrl;
    public AdempiereException(String message) {
        super(message);
    }
}
