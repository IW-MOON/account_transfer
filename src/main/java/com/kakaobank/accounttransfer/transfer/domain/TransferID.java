package com.kakaobank.accounttransfer.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class TransferID implements Serializable {

    private Long id;
    private LocalDateTime transferDatetime;
}
