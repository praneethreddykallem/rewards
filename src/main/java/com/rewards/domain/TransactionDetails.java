package com.rewards.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Getter
@Setter
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_details_seq")
    @SequenceGenerator(
            name = "txn_details_seq",
            sequenceName = "txn_details_seq",
            allocationSize = 1
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "trnsctn_id")
    private Transaction transaction;

    private String jsonDetails;

}