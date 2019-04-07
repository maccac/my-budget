package org.github.maccac.mybudget.mybudget

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyBudgetApplication

fun main(args: Array<String>) {
	runApplication<MyBudgetApplication>(*args)
}
