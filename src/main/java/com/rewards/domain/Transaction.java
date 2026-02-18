package com.rewards.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import com.rewards.constants.RewardsConstants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_seq")
	@SequenceGenerator(
			name = "txn_seq",
			sequenceName = "txn_seq",
			allocationSize = 1
	)
	@Column(name = "trnsctn_id")
	private Integer transactionId;
	
	@Column(name = "cust_name")
	@NotEmpty(message = RewardsConstants.ERROR_MESSAGE_CUSTOMER_NAME)
	private String customerName;
	
	@Column(name = "amount")
	@NotNull(message = RewardsConstants.ERROR_MESSAGE_AMOUNT)
	private Integer amount;
	
	@Column(name = "trnsctn_date")
	@NotEmpty(message = RewardsConstants.ERROR_MESSAGE_DATE)
	private String transactionDate;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TransactionDetails> details;
	
}
