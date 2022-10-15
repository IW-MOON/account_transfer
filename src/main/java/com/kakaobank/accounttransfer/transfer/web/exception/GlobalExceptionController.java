package com.kakaobank.accounttransfer.transfer.web.exception;

import com.kakaobank.accounttransfer.transfer.dto.TransferCentralAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
    })
    protected TransferCentralAccountResponse handleBadRequestException(Exception e) {
        return TransferCentralAccountResponse.builder().message(e.getMessage()).build();
    }

}
