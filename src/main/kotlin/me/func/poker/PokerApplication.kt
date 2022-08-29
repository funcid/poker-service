package me.func.poker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class PokerApplication

fun main(args: Array<String>) {
	runApplication<PokerApplication>(*args)
}
