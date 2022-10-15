package com.kakaobank.accounttransfer.transfer.domain.repository;

import com.kakaobank.accounttransfer.transfer.domain.CentralAccount;
import com.kakaobank.accounttransfer.transfer.domain.CentralAccountTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.TransferID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CentralAccountHistoryRepository extends JpaRepository<CentralAccountTransferHistory, TransferID> {

    @Query("select COALESCE(sum(c.transferAmount), 0) from CentralAccountTransferHistory c where c.centralAccount = :centralAccount and c.transferClass = 'D'")
    Long findSumDepositByCentralAcc(CentralAccount centralAccount);

    @Query("select COALESCE(sum(c.transferAmount), 0) from CentralAccountTransferHistory c where c.centralAccount = :centralAccount and c.transferClass = 'W'")
    Long findSumWithdrawByCentralAcc(CentralAccount centralAccount);
}
