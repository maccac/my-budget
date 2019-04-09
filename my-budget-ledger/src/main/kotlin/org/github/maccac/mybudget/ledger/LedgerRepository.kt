package org.github.maccac.mybudget.ledger

import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface LedgerRepository : JpaRepository<Ledger, Long>

interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findByLedgerId(ledgerId: Long) : List<Transaction>

}

interface LabelRepository : JpaRepository<TransactionLabel, String>