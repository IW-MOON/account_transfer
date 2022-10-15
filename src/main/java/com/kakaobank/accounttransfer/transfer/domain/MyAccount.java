package com.kakaobank.accounttransfer.transfer.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "IDX_MY_ACCOUNT_01", columnList = "memberId"))
public class MyAccount extends DateTimeBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MY_ACC_ID")
    private Long id;

    @Column(length = 13, nullable = false)
    private String myAcc;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private LocalDateTime registerDatetime;

    @Column(length = 1, nullable = false)
    private String useYn;
}
