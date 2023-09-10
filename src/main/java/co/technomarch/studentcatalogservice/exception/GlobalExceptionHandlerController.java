package co.technomarch.studentcatalogservice.exception;

import java.net.ConnectException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.mongodb.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    // MongoDB connection exceptions


    @ExceptionHandler(MongoSocketOpenException.class)
    public ResponseEntity<Object> mongoSocketOpenException(MongoSocketOpenException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MongoSocketClosedException.class)
    public ResponseEntity<Object> mongoSocketClosedException(MongoSocketClosedException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MongoSocketException.class)
    public ResponseEntity<Object> mongoSocketException(MongoSocketException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MongoServerUnavailableException.class)
    public ResponseEntity<Object> mongoServerUnavailableException(MongoServerUnavailableException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MongoTimeoutException.class)
    public ResponseEntity<Object> mongoTimeoutException(MongoTimeoutException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.REQUEST_TIMEOUT);
    }


    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> connectionException(ConnectException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> unsupportedOperation(UnsupportedOperationException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> studentNotFound(StudentNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setTimestamp(new Date().getTime());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponse, null, HttpStatus.NOT_FOUND);
    }

}
