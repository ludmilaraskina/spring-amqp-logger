package com.github.ludmilaraskina.config

import com.github.ludmilaraskina.MessageLoggingProcessor
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@ConditionalOnProperty("spring.amqp.log.enabled")
@EnableConfigurationProperties(AmqpLoggingProperties::class)
class AmqpLoggingConfig(private val rabbitTemplate: RabbitTemplate,
                        private val rabbitListenerContainerFactory: SimpleRabbitListenerContainerFactory) {

    @PostConstruct
    fun customizeRabbitTemplate() {
        rabbitTemplate.setBeforePublishPostProcessors(MessageLoggingProcessor("Message to publish"))
    }

    @PostConstruct
    fun customizeRabbitListener() {
        rabbitListenerContainerFactory.setAfterReceivePostProcessors(MessageLoggingProcessor("Received message"))
    }
}
