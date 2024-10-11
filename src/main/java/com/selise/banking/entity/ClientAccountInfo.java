package com.selise.banking.entity;

import com.selise.banking.model.ClientAccountRecord;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "client_account")
public class ClientAccountInfo {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, length = 20)
    private String accountNumber;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Column(name = "account_status", nullable = false, length = 20)
    private String accountStatus;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "last_transaction_date")
    private LocalDateTime lastTransactionDate;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static ClientAccountInfo fromRecord(ClientAccountRecord record) {
        ClientAccountInfo account = new ClientAccountInfo();
        account.setAccountNumber(record.accountNumber());
        account.setFullName(record.fullName());
        account.setDateOfBirth(record.dateOfBirth());
        account.setAccountType(record.accountType());
        account.setAccountStatus(record.accountStatus());
        account.setBalance(record.balance());
        account.setLastTransactionDate(record.lastTransactionDate());
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }
}
