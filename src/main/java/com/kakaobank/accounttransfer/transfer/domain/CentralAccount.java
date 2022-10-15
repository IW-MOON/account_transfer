package com.kakaobank.accounttransfer.transfer.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CentralAccount extends DateTimeBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CENTRAL_ACC_ID")
    private Long id;

    @Column(length = 13, nullable = false)
    private String centralAcc;

    @Column(nullable = false)
    private LocalDate strtDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 1, nullable = false)
    private String useYn;
}
