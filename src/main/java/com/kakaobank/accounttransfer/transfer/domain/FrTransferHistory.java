package com.kakaobank.accounttransfer.transfer.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(TransferHistoryID.class)
@Table(indexes =
                {@Index(name = "IDX_FR_TRANSFER_HISTORY_01", columnList = "registerDatetime, transferStatus")
                })
public class FrTransferHistory extends DateTimeBaseEntity implements Serializable {

    @Id
    @Column(name = "TRANSFER_HISTORY_ID")
    private Long id;

    @Id
    private LocalDateTime registerDatetime;

    @Column(nullable = false)
    private Long fromMemberId;

    @Column(length = 13, nullable = false)
    private String fromMemberAcc;

    @Column(length = 13, nullable = false)
    private String toCentralAcc;

    @Column(nullable = false)
    private Long toMemberKakaotalkId;

    @Column(nullable = false)
    private Long transferAmount;

    @Column(length = 16)
    private String toMemberAcc;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private TransferBank toMemberBank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransferStatus transferStatus;

    @Column(length = 4)
    private String transferResult;

    private LocalDateTime transferDatetime;


    public void transferComplete(String result) {
        this.transferDatetime = LocalDateTime.now();
        this.transferStatus = TransferStatus.COMPLETED;
        this.transferResult = result;
    }

}
