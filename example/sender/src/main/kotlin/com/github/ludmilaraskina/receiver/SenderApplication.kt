package com.github.ludmilaraskina.receiver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SenderApplication

fun main(args: Array<String>) {
    runApplication<SenderApplication>(*args)
}
