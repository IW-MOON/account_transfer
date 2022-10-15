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
public class MyAccountTransferHistory extends DateTimeBaseEntity{

    @Id
    @Column(name = "MY_ACC_TRANSFER_HISTORY_ID")
    private Long id;

    @Id
    private LocalDateTime transferDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MY_ACC_ID")
    private MyAccount myAccount;

    @Column(length = 13, nullable = false)
    private String myAcc;

    @Column(nullable = false)
    private Long transferAmount;

    @Column(length = 16)
    private String transferAcc;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private TransferBank transferBank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private TransferClass transferClass;

}
