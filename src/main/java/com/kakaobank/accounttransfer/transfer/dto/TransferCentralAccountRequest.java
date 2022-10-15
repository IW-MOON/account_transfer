package com.kakaobank.accounttransfer.transfer.dto;

import lombok.Getter;

@Getter
public class TransferCentralAccountRequest {

    private String fromMemberId;
    private Long toMemberKakaotalkId;
    private Long transferAmount;

}
