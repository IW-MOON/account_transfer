package com.kakaobank.accounttransfer.transfer.domain.repository;

import com.kakaobank.accounttransfer.transfer.domain.CentralAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentralAccountRepository extends JpaRepository<CentralAccount, Long> {

    Optional<CentralAccount> findTop1ByUseYnOrderByStrtDateDesc(String useYn);
}
