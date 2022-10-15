package com.kakaobank.accounttransfer.transfer.web;

import com.kakaobank.accounttransfer.transfer.dto.TransferCentralAccountRequest;
import com.kakaobank.accounttransfer.transfer.dto.TransferCentralAccountResponse;
import com.kakaobank.accounttransfer.transfer.service.FrTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
public class TransferController {

    private final FrTransferService frTransferService;

    @PostMapping("/central-account")
    @ResponseStatus(HttpStatus.OK)
    public TransferCentralAccountResponse transferToCentralAccount(@RequestBody TransferCentralAccountRequest request) {

        return frTransferService.transferToCentralAccount(request);
    }
}
