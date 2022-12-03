package com.example.coffee.Exception;

import com.example.coffee.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.MessageCodesResolver;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Locale;
import java.util.Map;

public class ViolationMessageResolver {

    private final MessageSource messageSource;
    private final MessageCodesResolver codesResolver;

    private final Logger logger = LoggerFactory.getLogger(ViolationMessageResolver.class);

    public ViolationMessageResolver(MessageSource messageSource, MessageCodesResolver messageCodesResolver) {
        this.messageSource = messageSource;
        this.codesResolver = messageCodesResolver;
    }

    public String message(ConstraintViolation<?> violation) {
        ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor();
        Map<String, Object> attributes = descriptor.getAttributes();

        String annotationName = descriptor.getAnnotation().annotationType().getSimpleName();
        String rootBeanName = violation.getRootBeanClass().getSimpleName();
        rootBeanName = rootBeanName.substring(0,1).toLowerCase() + rootBeanName.substring(1);
        String path = violation.getPropertyPath().toString();

        //어노테이션, 클래스, 필드 조합으로 코드 생성
        String[] codes = codesResolver.resolveMessageCodes(annotationName, rootBeanName, path, null);

        String result = null;

        for(String code : codes){
            try{
                //코드로 메세지 조회
                result = messageSource.getMessage(code, null, Locale.getDefault());
                for(Map.Entry<String, Object> es : attributes.entrySet()){
                    //어노테이션 기준으로 {...}형태의 메시지 치환
                    result = result.replace(
                            "{"+es.getKey()+"}", es.getValue().toString()
                    );
                }
                //검증 대상값 치환
                result = result.replace("{validatedValue}", violation.getInvalidValue().toString());
            } catch(NoSuchMessageException ignored){
                logger.error("validatedValue was ignored ... : "+ignored);
            }
        }
        //code로 메세지를 찾지 못한 경우 기본값 사용
        if(result == null){
            result = violation.getMessage();
        }
        return result;
    }
}
