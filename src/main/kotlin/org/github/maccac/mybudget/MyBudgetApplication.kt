package org.github.maccac.mybudget

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class MyBudgetApplication

fun main(args: Array<String>) {
	runApplication<MyBudgetApplication>(*args)
}
