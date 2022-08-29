package me.func.poker.service.timer

import me.func.poker.service.session.Session
import me.func.poker.service.session.SessionStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
object Timer {

    private val sessions = arrayListOf<Session>()

    fun createNewSession() {
        sessions.add(Session((Math.random() * Long.MAX_VALUE).toLong()))
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    fun everySecond() {
        sessions.removeIf { it.time.get() == 20L && it.status == SessionStatus.END || it.users.isEmpty() }
        sessions.forEach { it.everySecond() }
    }

}