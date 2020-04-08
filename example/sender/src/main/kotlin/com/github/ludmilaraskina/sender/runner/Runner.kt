package com.github.ludmilaraskina.sender.runner

import com.github.ludmilaraskina.annotation.Hidden
import com.github.ludmilaraskina.annotation.Masked
import com.github.ludmilaraskina.sender.config.topicExchangeName
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component


@Component
class Runner(private val rabbitTemplate: RabbitTemplate) {

    fun run() {
        println("Sending message...")
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", ExampleMessage())
    }

    private data class ExampleMessage(
            val id: Int = 0,
            val description: String = "Example message",
            @Hidden
            val hidden: String = "Sensitive info",
            @Masked
            val masked: String = "1234567890"
    )

}
