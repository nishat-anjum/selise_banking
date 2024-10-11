package com.selise.banking.model;


import java.io.Serializable;
import java.math.BigDecimal;

public record TransferRequest(String sender,
                              String receiver,
                              BigDecimal amount) implements Serializable {
}
