package com.selise.banking.repo;

import com.selise.banking.entity.ClientAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientAccountInfoRepository extends JpaRepository<ClientAccountInfo, Long> {
    Optional<ClientAccountInfo> findByAccountNumber(String accountNumber);
}
