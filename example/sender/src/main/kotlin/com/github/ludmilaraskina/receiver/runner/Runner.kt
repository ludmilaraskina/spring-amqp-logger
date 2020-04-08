package com.github.ludmilaraskina.receiver.runner

import com.github.ludmilaraskina.api.ExampleMessage
import com.github.ludmilaraskina.receiver.config.topicExchangeName
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Runner(private val rabbitTemplate: RabbitTemplate) {

    @EventListener(ApplicationReadyEvent::class)
    fun run() {
        println("Sending message...")
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", ExampleMessage())
    }
}
