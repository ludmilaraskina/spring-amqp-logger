package com.github.ludmilaraskina.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("sup.ampq.log")
class AmpqLoggingProperties {
    var enabled: Boolean = false
}
