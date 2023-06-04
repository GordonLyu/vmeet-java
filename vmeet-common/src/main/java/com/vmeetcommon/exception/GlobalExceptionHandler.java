package com.vmeetcommon.exception;

import com.vmeetcommon.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BizException e){
        log.info("业务异常");
        log.info("发生业务异常！原因是：{}",e.getErrorMsg());
        return Result.fail(Integer.valueOf(e.getErrorCode()),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.info("空指针异常");
        log.info("发生空指针异常！原因是:", e);
        return Result.fail(ExceptionEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理参数的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result ValidationException(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.info("参数异常");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), "参数异常", collect);
    }


//    /**
//     * 处理其他异常
//     *
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result exceptionHandler(HttpServletRequest req, Exception e) {
//        log.info("未知异常");
//        log.info("rep: " + req.getHeader("Cookie"));
//        log.info("未知异常！原因是:", e);
//        return Result.fail(ExceptionEnum.INTERNAL_SERVER_ERROR);
//    }


}
