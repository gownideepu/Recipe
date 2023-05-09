package com.example.Recipe.Description.Exception;

import com.example.Recipe.Description.Dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class Exception {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String>errorMessage=errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto responseDto=new ResponseDto("Exception While handling Rest Api call ,",errorMessage);
        return  new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> HttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ResponseDto responseDto=new ResponseDto("The Date should be in dd-mm-yyyy formate",exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ResponseDto> IllegalArgumentException(CustomException exception){
//        ResponseDto responseDto=new ResponseDto("Exception for ,",exception.getMessage());
//        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleNullPointerExceptions(CustomException exception){
        ResponseDto responseDto=new ResponseDto("Exception for ,",exception.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}