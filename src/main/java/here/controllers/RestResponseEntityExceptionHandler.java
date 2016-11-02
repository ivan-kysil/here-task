package here.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.hateoas.VndErrors;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
/**
* Customer Error handlers go HERE
*/
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger LOG = Logger.getLogger(RestResponseEntityExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected VndErrors illegalArgumentExceptionHandler(Exception ex, WebRequest request) {
        LOG.error("", ex);
        return new VndErrors("error", ex.getMessage());
    }

}