package com.github.ludmilaraskina

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.ludmilaraskina.jackson.LogModule
import mu.KotlinLogging.logger
import org.springframework.amqp.AmqpException
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.support.converter.DefaultClassMapper.DEFAULT_CLASSID_FIELD_NAME

class MessageLoggingProcessor(private val prefix: String) : MessagePostProcessor {

    private val objectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
    private val logObjectMapper = jacksonObjectMapper().registerModules(JavaTimeModule(), LogModule())
    private val log = logger {}

    @Throws(AmqpException::class)
    override fun postProcessMessage(message: Message): Message {
        try {
            val messageClass = Class.forName(message.messageProperties.headers[DEFAULT_CLASSID_FIELD_NAME].toString())
            val messageObject = objectMapper.readValue(message.body, messageClass)
            val logMessage = logObjectMapper.writeValueAsString(messageObject)
            log.info { "$prefix: $logMessage" }
        } catch (e: Exception) {
            log.warn("Error in MessageLoggingProcessor. Ignoring: ${e.message}", e)
        }
        return message
    }
}
