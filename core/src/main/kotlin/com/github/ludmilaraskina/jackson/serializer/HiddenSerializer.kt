package com.github.ludmilaraskina.jackson.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException

private const val HIDDEN = "***"

class HiddenSerializer : JsonSerializer<Any?>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: Any?, jgen: JsonGenerator?,
                           provider: SerializerProvider) {
        jgen?.writeString(HIDDEN)
    }
}
