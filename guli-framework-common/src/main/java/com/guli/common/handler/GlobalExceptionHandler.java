package com.guli.common.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.vo.R;
import com.guli.common.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//全局异常
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        e.printStackTrace();
        return R.error().message(ResultCodeEnum.BAD_SQL_GRAMMAR.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(JsonParseException e){
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e){
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
