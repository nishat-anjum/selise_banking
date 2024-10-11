package com.selise.banking.api;

import com.selise.banking.model.ClientAccountRecord;
import com.selise.banking.service.ClientAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class ClientAccountController {

    private final ClientAccountService clientAccountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ClientAccountRecord> getAccountDetails(@PathVariable String accountNumber) {
        log.debug("Request to load account detail");
        return ResponseEntity.ok(clientAccountService.getClientAccountRecord(accountNumber));
    }

}
