package com.kakaobank.accounttransfer.transfer.domain;

import lombok.Getter;

@Getter
public enum TransferStatus {

    REQUEST("요청중"),
    COMPLETED("처리완료"),
    ERROR("에러");

    private final String message;

    TransferStatus(String message) {
        this.message = message;
    }
}
