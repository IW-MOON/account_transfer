package com.kakaobank.accounttransfer.transfer.domain.repository;

import com.kakaobank.accounttransfer.transfer.domain.MyAccount;
import com.kakaobank.accounttransfer.transfer.domain.MyAccountTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.TransferID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyAccountTransferHistoryRepository extends JpaRepository<MyAccountTransferHistory, TransferID> {

    @Query("select COALESCE(sum(c.transferAmount), 0) from MyAccountTransferHistory c where c.myAccount = :myAccount and c.transferClass = 'D'")
    Long findSumDepositByMyAcc(MyAccount myAccount);

    @Query("select COALESCE(sum(c.transferAmount), 0) from MyAccountTransferHistory c where c.myAccount = :myAccount and c.transferClass = 'W'")
    Long findSumWithdrawByMyAcc(MyAccount myAccount);
}
