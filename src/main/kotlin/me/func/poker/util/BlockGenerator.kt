package me.func.poker.util

import me.func.poker.data.card.Card
import me.func.poker.data.card.CardType
import me.func.poker.data.card.SuitType

object BlockGenerator {

    // Создаем новую колоду
    fun new() =
        SuitType.values().map { suit -> CardType.values().map { type -> Card(type, suit) } }.flatten().toMutableSet()

}