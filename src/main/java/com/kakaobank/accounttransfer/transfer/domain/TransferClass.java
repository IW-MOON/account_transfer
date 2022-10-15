package com.kakaobank.accounttransfer.transfer.domain;

import lombok.Getter;

@Getter
public enum TransferClass {
    D("DEPOSIT"),
    W("WITHDRAW");

    private final String message;

    TransferClass(String message) {
        this.message = message;
    }
}
