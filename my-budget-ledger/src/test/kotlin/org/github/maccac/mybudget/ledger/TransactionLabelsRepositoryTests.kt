package org.github.maccac.mybudget.ledger

import com.tyro.oss.randomdata.RandomBigDecimal.randomBigDecimal
import com.tyro.oss.randomdata.RandomString.randomString
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.github.maccac.mybudget.MyBudgetApplication
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MyBudgetApplication::class])
@ActiveProfiles(value = ["test"])
@Transactional
class TransactionLabelsRepositoryTests {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var ledgerRepository: LedgerRepository

    @Test
    internal fun `should save a transaction`() {
        val ledger = Ledger(name = randomString())

        ledgerRepository.save(ledger)

        val transaction = Transaction(description = randomString(), signedAmount = randomBigDecimal(), ledger = ledger)
        transactionRepository.save(transaction)

        assertThat(transactionRepository.findAll().size).isEqualTo(1)
    }

}