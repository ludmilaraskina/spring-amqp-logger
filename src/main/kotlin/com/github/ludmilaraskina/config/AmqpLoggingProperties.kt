package com.github.ludmilaraskina.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.amqp.log")
class AmqpLoggingProperties {
    var enabled: Boolean = false
}
