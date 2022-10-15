package com.kakaobank.accounttransfer.transfer.domain.repository;

import com.kakaobank.accounttransfer.transfer.domain.FrTransferHistory;
import com.kakaobank.accounttransfer.transfer.domain.TransferHistoryID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrTransferHistoryRepository extends JpaRepository<FrTransferHistory, TransferHistoryID> {
}
