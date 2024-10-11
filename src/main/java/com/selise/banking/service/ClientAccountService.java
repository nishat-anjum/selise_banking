package com.selise.banking.service;

import com.selise.banking.entity.ClientAccountInfo;
import com.selise.banking.model.ClientAccountRecord;
import com.selise.banking.model.TransferRequest;
import com.selise.banking.repo.ClientAccountInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientAccountService {
    private final ClientAccountInfoRepository clientAccountInfoRepository;

    public ClientAccountRecord getClientAccountRecord(String accountNo) {
        if (isNullOrEmpty(accountNo))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AccountNo must not be null");
        Optional<ClientAccountInfo> clientAccountInfo = clientAccountInfoRepository.findByAccountNumber(accountNo);
        return clientAccountInfo.map(ClientAccountRecord::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Account Record not found with No [%s]", accountNo)));
    }

    @Transactional
    public void transferAmount(TransferRequest transferRequest) {
        log.debug("Bank transfer request from: {}, to : {}", transferRequest.sender(), transferRequest.receiver());

        if (isNullOrEmpty(transferRequest.sender()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender must not be null");

        if (isNullOrEmpty(transferRequest.receiver()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver must not be null");

        if (transferRequest.amount() == null || transferRequest.amount().compareTo(BigDecimal.ZERO) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must not be negative");

        ClientAccountInfo sender = clientAccountInfoRepository.findByAccountNumber(transferRequest.sender())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found"));
        ClientAccountInfo recipient = clientAccountInfoRepository.findByAccountNumber(transferRequest.receiver())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipient not found"));

        if (!"Active".equals(sender.getAccountStatus()) || !"Active".equals(recipient.getAccountStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both accounts must be active for the transfer.");
        }

        if (sender.getBalance().compareTo(transferRequest.amount()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        sender.setBalance(sender.getBalance().subtract(transferRequest.amount()));
        recipient.setBalance(recipient.getBalance().add(transferRequest.amount()));
        sender.setLastTransactionDate(LocalDateTime.now());
        recipient.setLastTransactionDate(LocalDateTime.now());
        clientAccountInfoRepository.save(sender);
        clientAccountInfoRepository.save(recipient);
    }

    private static boolean isNullOrEmpty(String str) {
        return Optional.ofNullable(str)
                .map(String::isEmpty)
                .orElse(true);
    }
}
