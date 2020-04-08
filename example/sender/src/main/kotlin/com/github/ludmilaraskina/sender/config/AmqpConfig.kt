package com.github.ludmilaraskina.sender.config

import com.github.ludmilaraskina.sender.runner.Runner
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener


const val topicExchangeName = "spring-boot-exchange"
const val queueName = "spring-boot"

@Configuration
class AmqpConfig(private val runner: Runner) {

    @EventListener(ContextRefreshedEvent::class)
    fun onStartup() {
        runner.run()
    }

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#")
    }

/*
    @Bean
    fun container(connectionFactory: ConnectionFactory): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.setMessageListener(listenerAdapter);
        return container
    }

    @Bean
    fun listenerAdapter(receiver: Receiver?): MessageListenerAdapter? {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }
*/

}
