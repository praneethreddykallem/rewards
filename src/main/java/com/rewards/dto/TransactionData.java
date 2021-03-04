package com.rewards.dto;

import lombok.Data;

@Data
public class TransactionData {

	private String transactionId;
	private String customerName;
	private Integer amount;
	private String transactionDate;
	
}
