package com.selise.banking.service;

import com.selise.banking.entity.ClientAccountInfo;
import com.selise.banking.model.ClientAccountRecord;
import com.selise.banking.model.TransferRequest;
import com.selise.banking.repo.ClientAccountInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientAccountService {
    private final ClientAccountInfoRepository clientAccountInfoRepository;

    public ClientAccountRecord getClientAccountRecord(String accountNo) {
        Optional<ClientAccountInfo> clientAccountInfo = clientAccountInfoRepository.findByAccountNumber(accountNo);
        return clientAccountInfo.map(ClientAccountRecord::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Account Record not found with No [%s]", accountNo)));
    }

    @Transactional
    public void transferAmount(TransferRequest transferRequest) {
        log.debug("Bank transfer request from: {}, to : {}", transferRequest.sender(), transferRequest.receiver());
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
}
