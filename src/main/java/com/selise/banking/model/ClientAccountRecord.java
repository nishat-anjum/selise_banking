package com.selise.banking.model;

import com.selise.banking.entity.ClientAccountInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientAccountRecord(Long id,
                                  String accountNumber,
                                  String fullName,
                                  LocalDate dateOfBirth,
                                  String accountType,
                                  String accountStatus,
                                  BigDecimal balance,
                                  LocalDateTime lastTransactionDate) implements Serializable {

    public static ClientAccountRecord fromEntity(ClientAccountInfo entity) {
        return new ClientAccountRecord(
                entity.getId(),
                entity.getAccountNumber(),
                entity.getFullName(),
                entity.getDateOfBirth(),
                entity.getAccountType(),
                entity.getAccountStatus(),
                entity.getBalance(),
                entity.getLastTransactionDate()
        );
    }

    public ClientAccountInfo toEntity() {
        ClientAccountInfo entity = new ClientAccountInfo();
        entity.setId(this.id);
        entity.setAccountNumber(this.accountNumber);
        entity.setFullName(this.fullName);
        entity.setDateOfBirth(this.dateOfBirth);
        entity.setAccountType(this.accountType);
        entity.setAccountStatus(this.accountStatus);
        entity.setBalance(this.balance);
        entity.setLastTransactionDate(this.lastTransactionDate);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
