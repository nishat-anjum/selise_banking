package com.selise.banking.api;

import com.selise.banking.model.TransferRequest;
import com.selise.banking.service.ClientAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balance")
public class AccountBalanaceController {

    private final ClientAccountService clientAccountService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferAmount(@RequestBody TransferRequest transferRequest) {
        clientAccountService.transferAmount(transferRequest);
        return ResponseEntity.ok("Transfer successful");
    }
}
