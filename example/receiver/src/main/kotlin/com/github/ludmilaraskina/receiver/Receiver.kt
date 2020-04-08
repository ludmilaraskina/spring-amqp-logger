package com.github.ludmilaraskina.receiver

import com.github.ludmilaraskina.api.ExampleMessage
import com.github.ludmilaraskina.receiver.config.queueName
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
class Receiver {
    val latch = CountDownLatch(1)

    @RabbitListener(queues = [queueName])
    fun receiveMessage(message: ExampleMessage) {
        println("Received <$message>")
        latch.countDown()
    }
}
