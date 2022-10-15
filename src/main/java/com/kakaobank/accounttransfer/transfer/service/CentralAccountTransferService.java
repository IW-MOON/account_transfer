package com.kakaobank.accounttransfer.transfer.service;

import com.kakaobank.accounttransfer.transfer.domain.CentralAccount;
import com.kakaobank.accounttransfer.transfer.domain.CentralAccountTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.repository.CentralAccountHistoryRepository;
import com.kakaobank.accounttransfer.transfer.domain.repository.CentralAccountRepository;
import com.kakaobank.accounttransfer.transfer.dto.TransferInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class CentralAccountTransferService {

    private final CentralAccountRepository centralAccountRepository;
    private final CentralAccountHistoryRepository centralAccountHistoryRepository;

    @Transactional(readOnly = true)
    public CentralAccount getCentralAccount() {
       return centralAccountRepository.findTop1ByUseYnOrderByStrtDateDesc("Y")
                                      .orElseThrow(() -> new RuntimeException("등록된 모계좌가 없습니다."));
    }

    @Transactional(readOnly = true)
    public Long getAccountBalance(CentralAccount centralAccount) {
        return centralAccountHistoryRepository.findSumDepositByCentralAcc(centralAccount) - centralAccountHistoryRepository.findSumWithdrawByCentralAcc(centralAccount);
    }

    public void transferCentralAccount(CentralAccount centralAccount, TransferInformation transferInformation) {

        CentralAccountTransferHistory centralAccountTransferHistory = CentralAccountTransferHistory.builder()
                                                                                                   .centralAccount(centralAccount)
                                                                                                   .transferDatetime(LocalDateTime.now())
                                                                                                   .transferAmount(transferInformation.getTransferAmount())
                                                                                                   .transferAcc(transferInformation.getTransferAcc())
                                                                                                   .transferBank(transferInformation.getTransferBank())
                                                                                                   .transferClass(transferInformation.getTransferClass()).build();
        centralAccountHistoryRepository.save(centralAccountTransferHistory);
    }
}
