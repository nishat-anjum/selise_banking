package com.selise.banking;

import com.selise.banking.model.TransferRequest;
import com.selise.banking.service.ClientAccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class SeliseBankingApplicationTests {

	@Autowired
	private ClientAccountService clientAccountService;

	@Test
	void contextLoads() {
		log.debug("##################");
		TransferRequest transferRequest = new TransferRequest("1234567890", "9876543210", BigDecimal.valueOf(200));
		clientAccountService.transferAmount(transferRequest);
	}
}
