package com.test.pruebaGestioLogistica.auth;


import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.ErrorMessage;
import com.test.pruebaGestioLogistica.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceoptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundException(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequestException(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            ForbiddenException.class
    })
    @ResponseBody
    public ErrorMessage forbiddenException(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }



    /*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage internalServerErrorException(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }*/
}
