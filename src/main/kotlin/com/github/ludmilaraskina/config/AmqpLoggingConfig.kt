package com.github.ludmilaraskina.config

import com.github.ludmilaraskina.MessageLoggingProcessor
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@ConditionalOnProperty("sup.amqp.log.enabled")
@EnableConfigurationProperties(AmpqLoggingProperties::class)
@ConditionalOnClass(RabbitTemplate::class)
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
