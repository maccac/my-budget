package org.github.maccac.mybudget.ledger

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Ledger(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "id")
        var id: Long = 0,
        @NotNull
        @Column(length = 256)
        var name: String,
        @NotNull
        var currencyType: String = "AUD",
        @OneToMany(fetch = FetchType.EAGER)
        var labels: List<TransactionLabel> = emptyList()
)

@Entity
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "id")
        val id: Long = 0,
        @NotNull
        @Column(length = 512)
        val description: String,
        @NotNull
        val signedAmount: BigDecimal,
        @NotNull
        @Column(length = 6)
        val currencyType: String = "AUD",
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        val ledger: Ledger,
        @OneToMany(fetch = FetchType.EAGER)
        val labels: List<TransactionLabel> = emptyList()
)

@Entity
data class TransactionLabel(
        @Id
        @Column(length = 16)
        val code: String,
        @Column(length = 256)
        val name: String)