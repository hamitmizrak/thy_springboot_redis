package com.hamitmizrak.thy_springboot_redis.error;

import com.hamitmizrak.thy_springboot_redis.utils.frontend.ReactFrontend;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2
@RequiredArgsConstructor

// Spring tarafından /error url gelen hataları yakalayıp bizim belirlediğimiz ApiResult'a gönder
// http://localhost:4444/error
@RestController
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND)
public class CustomHandleWebRequest implements ErrorController {

    // 1.YOL FIELD INJECTION
    /*
    @Autowired
    private ErrorAttributes errorAttributes;
     */

    // 2.YOL CONSTRUCTOR INJECTION
    /*
    private final ErrorAttributes errorAttributes;
    @Autowired
    public CustomHandleWebRequest(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    */

    // 3.YOL LOMBOK INJECTION
    private final ErrorAttributes errorAttributes;

    // s m p v
    private ApiResult apiResult;
    private Integer status;
    private String message;
    private String path;
    private Map<String,String> validationErrors;

    // http://localhost:4444/error
    @GetMapping("/error")
    public ApiResult handleWebError(WebRequest webRequest) {
        // Spring>=2.3
        /*
        public interface ErrorAttributes {
       default Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        return Collections.emptyMap();
        }
        */
        Map<String,Object>  attributes = errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,ErrorAttributeOptions.Include.BINDING_ERRORS)
        ); //end attributes

        // SpringBoottan aldığım obje bileşenleri ApiResultta Yaz
        status=(Integer)attributes.get("status");
        message=(String)attributes.get("message");
        path=(String)attributes.get("path");
        // Spring'den aldığım bilgileri ApiResultta parametreli constructa argüman olarak verelim.
        apiResult=new ApiResult(path,message,status);

        // Bütün hataları yakalamak ve validationErrors Set edelim.
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrorList = (List<FieldError>) attributes.get("errors");
            validationErrors=new HashMap<>();

            for(FieldError fieldError:fieldErrorList){
                validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationErrors);
        }
        return apiResult;
    } //end handleWebError

} // end class CustomHandleWebRequest
