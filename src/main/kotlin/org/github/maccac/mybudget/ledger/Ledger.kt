package org.github.maccac.mybudget.ledger

import java.math.BigDecimal
import java.math.BigInteger
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Ledger(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: BigInteger = BigInteger.ZERO,
                  @NotNull val name: String,
                  @NotNull val currencyType: String = "AUD")