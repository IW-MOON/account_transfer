package com.kakaobank.accounttransfer.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TransferCentralAccountResponse {

    private String result;
    private String message;

}
