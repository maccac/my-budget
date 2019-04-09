package org.github.maccac.mybudget.ledger

import org.springframework.data.repository.findByIdOrNull
import org.springframework.hateoas.Identifiable
import org.springframework.hateoas.Link
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LedgerController(val ledgerRepository: LedgerRepository) {


    @GetMapping("/ledgers")
    fun getLedgers() : HttpEntity<List<LedgerResource>> {
        val ledgers = ledgerRepository.findAll().map { toLedgerResource(it) }.toList()
        return ResponseEntity(ledgers, HttpStatus.OK)
    }

    @GetMapping("/ledgers/{ledgerId}")
    fun getLedger(@PathVariable ledgerId: Long) : HttpEntity<LedgerResource> {
        ledgerRepository.findByIdOrNull(ledgerId)?.let {
            return ResponseEntity(toLedgerResource(it), HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    private fun toLedgerResource(it: Ledger) : LedgerResource {
        val ledgerResource = LedgerResource(it.id, it.name, it.currencyType)
        ledgerResource.add(
            linkTo(methodOn(TransactionsController::class.java, it.id).getTransactions(it.id)).withRel("transactions")
        )
        ledgerResource.add(
                linkTo(methodOn(LedgerController::class.java, it.id).getLedger(it.id)).withSelfRel()
        )
        return ledgerResource
    }

}

data class LedgerResource(val ledgerId: Long, val name: String, val currencyType: String) : ResourceSupport()
