package com.kakaobank.accounttransfer.transfer.service;

import com.kakaobank.accounttransfer.transfer.domain.CentralAccount;
import com.kakaobank.accounttransfer.transfer.domain.CentralAccountTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.MyAccount;
import com.kakaobank.accounttransfer.transfer.domain.MyAccountTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.repository.MyAccountRepository;
import com.kakaobank.accounttransfer.transfer.domain.repository.MyAccountTransferHistoryRepository;
import com.kakaobank.accounttransfer.transfer.dto.TransferInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MyAccountTransferService {

    private final MyAccountRepository myAccountRepository;
    private final MyAccountTransferHistoryRepository myAccountTransferHistoryRepository;

    @Transactional(readOnly = true)
    public MyAccount getMyAccount(Long memberId) {
        return myAccountRepository.findTop1ByUseYnAndMemberIdOrderByRegisterDatetimeDesc("Y", memberId)
                                       .orElseThrow(() -> new IllegalArgumentException("등록된 계좌가 없습니다."));
    }

    @Transactional(readOnly = true)
    public Long getAccountBalance(MyAccount myAccount) {
        return myAccountTransferHistoryRepository.findSumDepositByMyAcc(myAccount) - myAccountTransferHistoryRepository.findSumWithdrawByMyAcc(myAccount);
    }

    public void transferMyAccount(MyAccount myAccount, TransferInformation transferInformation) {

        MyAccountTransferHistory myAccountTransferHistory = MyAccountTransferHistory.builder()
                                                                                    .myAccount(myAccount)
                                                                                    .myAcc(myAccount.getMyAcc())
                                                                                    .transferDatetime(LocalDateTime.now())
                                                                                    .transferAmount(transferInformation.getTransferAmount())
                                                                                    .transferAcc(transferInformation.getTransferAcc())
                                                                                    .transferBank(transferInformation.getTransferBank())
                                                                                    .transferClass(transferInformation.getTransferClass()).build();
        myAccountTransferHistoryRepository.save(myAccountTransferHistory);
    }
}
