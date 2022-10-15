package com.kakaobank.accounttransfer.transfer.domain;

import lombok.Getter;

@Getter
public enum TransferBank {

    B_001("KAKAO"),
    B_002("SHINHAN"),
    B_003("KB");

    private final String message;

    TransferBank(String message) {
        this.message = message;
    }
}
