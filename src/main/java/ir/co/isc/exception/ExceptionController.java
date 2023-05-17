package ir.co.isc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    ResponseEntity<ErrorResponseClient> handleEntityNotFoundException(EntityNotFoundException exception) {

        ErrorResponseClient errorResponseClient = createErrorResponseClient(exception);

        return new ResponseEntity<>(errorResponseClient, HttpStatus.NOT_FOUND);

    }


    private ErrorResponseClient createErrorResponseClient(Exception exception) {

        ErrorResponseClient errorResponseClient = new ErrorResponseClient();

        errorResponseClient.setErrorMessage(Constant.INTERNAL_SERVER_ERROR_MESSAGE);
        errorResponseClient.setErrorCode(500);
        errorResponseClient.setExceptionMessage(exception.getMessage());

        return errorResponseClient;
    }


    private ErrorResponseClient createErrorResponseClient(EntityNotFoundException exception) {

        ErrorResponseClient errorResponseClient = new ErrorResponseClient();

        errorResponseClient.setErrorMessage(exception.getErrorMessage());
        errorResponseClient.setErrorCode(404);
        errorResponseClient.setExceptionMessage(exception.getMessage());

        return errorResponseClient;
    }
}
