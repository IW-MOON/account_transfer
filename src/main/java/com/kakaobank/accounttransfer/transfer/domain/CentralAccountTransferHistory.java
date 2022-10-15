package com.kakaobank.accounttransfer.transfer.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(TransferID.class)
public class CentralAccountTransferHistory extends DateTimeBaseEntity{

    @Id
    @Column(name = "CENTRAL_ACC_TRANSFER_HISTORY_ID")
    private Long id;

    @Id
    private LocalDateTime transferDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTRAL_ACC_ID")
    private CentralAccount centralAccount;

    @Column(nullable = false)
    private Long transferAmount;

    @Column(length = 16, nullable = false)
    private String transferAcc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private TransferBank transferBank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private TransferClass transferClass;

}
