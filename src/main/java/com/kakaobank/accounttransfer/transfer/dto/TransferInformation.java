package com.kakaobank.accounttransfer.transfer.dto;

import com.kakaobank.accounttransfer.transfer.domain.TransferBank;
import com.kakaobank.accounttransfer.transfer.domain.TransferClass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransferInformation {

    private Long transferAmount;
    private String transferAcc;
    private TransferBank transferBank;
    private TransferClass transferClass;
}
