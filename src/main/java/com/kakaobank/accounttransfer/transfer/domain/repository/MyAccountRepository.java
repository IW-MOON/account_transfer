package com.kakaobank.accounttransfer.transfer.domain.repository;

import com.kakaobank.accounttransfer.transfer.domain.MyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MyAccountRepository extends JpaRepository<MyAccount, Long> {

    Optional<MyAccount> findTop1ByUseYnAndMemberIdOrderByRegisterDatetimeDesc(String useYn, Long memberId);
}
