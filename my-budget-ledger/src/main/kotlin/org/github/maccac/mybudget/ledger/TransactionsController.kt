package org.github.maccac.mybudget.ledger

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class TransactionsController(val transactionRepository: TransactionRepository) {


    @GetMapping("/ledgers/{ledgerId}/transactions")
    fun getTransactions(ledgerId: Long) : HttpEntity<List<TransactionResource>> {
        val transactions = transactionRepository.findByLedgerId(ledgerId).map { TransactionResource(it.id, it.description, it.signedAmount) }
        return ResponseEntity(transactions, HttpStatus.OK)
    }

}

data class TransactionResource(val id: Long, val description: String, val signedAmount: BigDecimal) : ResourceSupport()
