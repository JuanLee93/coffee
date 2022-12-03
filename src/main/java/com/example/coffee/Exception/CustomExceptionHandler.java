package com.example.coffee.Exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //MessageSource를 생성자로 자동주입
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request){

        //Error Object를 따로 뽑아서 List형태로 추출
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        //for문에서 메세지, 인자, local을 뽑아서 Message 클래스로 리턴
        for(ObjectError error : allErrors){
            //List에서 message를 람다식으로 출력 (c)
            String message = Arrays.stream(Objects.requireNonNull(error.getCodes())).map(c -> {
                Object[] arguments = error.getArguments();
                Locale locale = LocaleContextHolder.getLocale();
                try{
                    return messageSource.getMessage(c, arguments, locale);
                } catch(NoSuchMessageException e) {
                    return null;
                }
                //Objects관련 NonNull 처리, 첫번째로 찾은후 없을경우 DefaultMessage를 세팅해준다.
            }).filter(Objects::nonNull)
                    .findFirst()
                    .orElse(error.getDefaultMessage());

            //관련 메세지 잘 출력되는지 확인
            log.error("error message: {}",message);
        }
        //for문에서 뽑은 값을 관련 메소드로 다시 리턴
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
