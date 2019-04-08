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
class TransactionRepositoryTests {

    @Autowired
    private lateinit var labelRepository: LabelRepository

    @Test
    internal fun `should save a transaction label`() {
        val label = TransactionLabel(code = randomString(16), name = randomString())

        labelRepository.save(label)

        assertThat(labelRepository.findAll().size).isEqualTo(1)
    }

}