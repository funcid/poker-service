package me.func.poker.service.user

import me.func.poker.data.card.Card

data class User(val id: Long) {

    lateinit var hand: Pair<Card, Card>

    var status = UserStatus.ALIVE

    var cash = 500L

}