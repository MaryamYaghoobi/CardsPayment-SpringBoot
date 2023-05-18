package ir.co.isc.exception.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseClient {

    private String errorMessage;
    private Integer errorCode;
    private String exceptionMessage;

}
