package com.github.ludmilaraskina.receiver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReceiverApplication

fun main(args: Array<String>) {
    runApplication<ReceiverApplication>(*args)
}
