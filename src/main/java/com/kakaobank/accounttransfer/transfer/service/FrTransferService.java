package com.kakaobank.accounttransfer.transfer.service;

import com.kakaobank.accounttransfer.transfer.domain.*;
import com.kakaobank.accounttransfer.transfer.domain.repository.*;
import com.kakaobank.accounttransfer.transfer.dto.TransferCentralAccountRequest;
import com.kakaobank.accounttransfer.transfer.dto.TransferCentralAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class FrTransferService {

    private final CentralAccountTransferService centralAccountTransferService;
    private final MyAccountTransferService myAccountTransferService;
    private final CentralAccountHistoryRepository centralAccountHistoryRepository;
    private final MyAccountTransferHistoryRepository myAccountTransferHistoryRepository;
    private final FrTransferHistoryRepository frTransferHistoryRepository;

    public TransferCentralAccountResponse transferToCentralAccount(TransferCentralAccountRequest request) {

        Long myMemberId = 10000001L;

        MyAccount myAccount = myAccountTransferService.getMyAccount(myMemberId);

        Long myAccountBalance = myAccountTransferService.getAccountBalance(myAccount);
        if (myAccountBalance < request.getTransferAmount()) {
            throw new IllegalArgumentException("잔액이 충분하지 않습니다.");
        }

        CentralAccount centralAccount = centralAccountTransferService.getCentralAccount();
        String centralAccNo = centralAccount.getCentralAcc();

        String result = transferAcc(myAccount, centralAccount, request.getTransferAmount());

        // 내 계좌 및 임시계좌 입금이 정상적으로 끝났을 경우
        if (!result.equals("0000")) {
            return new TransferCentralAccountResponse(result, "이체에 실패했습니다.");
        }
        // 친구에게 이체 요청에 등록
        FrTransferHistory frTransferHistory = FrTransferHistory.builder()
                                                               .registerDatetime(LocalDateTime.now())
                                                               .fromMemberId(myMemberId)
                                                               .fromMemberAcc(myAccount.getMyAcc())
                                                               .toCentralAcc(centralAccNo)
                                                               .toMemberKakaotalkId(request.getToMemberKakaotalkId())
                                                               .transferAmount(request.getTransferAmount())
                                                               .transferStatus(TransferStatus.REQUEST)
                                                               .build();

        frTransferHistoryRepository.save(frTransferHistory);
        return new TransferCentralAccountResponse(result, "이체에 성공했습니다.");
    }

    // 이체 처리
    private String transferAcc(MyAccount fromMyAccount, CentralAccount toCentralAccount, Long transferAmount) {

        try {

            // 임시계좌 이체 내역 등록
            CentralAccountTransferHistory centralAccountTransferHistory = CentralAccountTransferHistory.builder()
                                                                                                       .transferClass(TransferClass.D)
                                                                                                       .transferDatetime(LocalDateTime.now())
                                                                                                       .transferBank(TransferBank.B_001)
                                                                                                       .centralAccount(toCentralAccount)
                                                                                                       .transferAcc(fromMyAccount.getMyAcc())
                                                                                                       .transferAmount(transferAmount)
                                                                                                       .build();
            centralAccountHistoryRepository.save(centralAccountTransferHistory);
            // 내 계좌 이체 내역에 등록
            MyAccountTransferHistory myAccountTransferHistory = MyAccountTransferHistory.builder()
                                                                                        .myAccount(fromMyAccount)
                                                                                        .myAcc(fromMyAccount.getMyAcc())
                                                                                        .transferClass(TransferClass.W)
                                                                                        .transferDatetime(LocalDateTime.now())
                                                                                        .transferAcc(toCentralAccount.getCentralAcc())
                                                                                        .transferAmount(transferAmount)
                                                                                        .build();

            myAccountTransferHistoryRepository.save(myAccountTransferHistory);

        } catch (Exception e) {
            e.printStackTrace();
            return "9999";
        }
        return "0000";
    }
}
