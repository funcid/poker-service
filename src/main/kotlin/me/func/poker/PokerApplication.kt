package me.func.poker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokerApplication

fun main(args: Array<String>) {
	runApplication<PokerApplication>(*args)
}
