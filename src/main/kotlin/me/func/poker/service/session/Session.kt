package me.func.poker.service.session

import me.func.poker.data.card.Card
import me.func.poker.service.user.User
import me.func.poker.service.user.UserStatus
import me.func.poker.util.BlockGenerator
import java.util.concurrent.atomic.AtomicLong

data class Session(
    var id: Long,
    var block: MutableSet<Card> = BlockGenerator.new(),
    var users: MutableList<User> = arrayListOf(),
) {

    var pickedUser = 0

    var round = 0

    var time = AtomicLong(0)

    var status = SessionStatus.WAITING

    var cash: Long = 0L

    var table: MutableSet<Card> = hashSetOf()

    fun takeCard() = block.random().apply { block.remove(this) }

    fun join() = User((Math.random() * Long.MAX_VALUE).toLong()).apply { users.add(this) }

    fun getPickedUser() = users[pickedUser]

    private fun nextUser(): User {
        pickedUser = if (pickedUser == users.size) 0 else pickedUser + 1
        return getPickedUser()
    }

    private fun nextRound() {
        round++
        cash = 0
        table = hashSetOf()
        users.forEach { user ->
            user.status = if (user.status == UserStatus.SPECTATOR) UserStatus.SPECTATOR else UserStatus.ALIVE
            user.hand = takeCard() to takeCard()
        }
    }

    fun call(user: User, value: Long) {
        if (user.cash < 1) return
        user.cash -= value
        cash += value
        nextUser()
    }

    fun fold(user: User) {
        user.status = UserStatus.DEAD
        nextUser()
    }

    fun everySecond() {
        val now = time.getAndIncrement()
        when (status) {
            SessionStatus.WAITING -> {
                if (users.size > 2) {
                    time.set(0)
                    status = SessionStatus.PLAYING
                    nextRound()
                }
            }
            SessionStatus.PLAYING -> {
                val alive = users.filter { it.status == UserStatus.ALIVE }
                if (alive.count() == 1) {
                    alive.forEach { user -> user.cash += cash }
                    cash = 0
                    nextRound()
                }
            }
            SessionStatus.END -> {

            }
        }
    }
}