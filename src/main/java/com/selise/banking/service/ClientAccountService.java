package com.selise.banking.service;

import com.selise.banking.entity.ClientAccountInfo;
import com.selise.banking.model.ClientAccountRecord;
import com.selise.banking.repo.ClientAccountInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientAccountService {
    private final ClientAccountInfoRepository clientAccountInfoRepository;

    public ClientAccountRecord getClientAccountRecord(String accountNo) {
        Optional<ClientAccountInfo> clientAccountInfo = clientAccountInfoRepository.findByAccountNumber(accountNo);
        return clientAccountInfo.map(ClientAccountRecord::fromEntity).orElse(null);
    }
}
