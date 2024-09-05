package com.hexa.persistence.jpaPersistence.entities;

import com.hexa.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private long amount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    private ZonedDateTime dateTime;
    private TransactionType type;
}
