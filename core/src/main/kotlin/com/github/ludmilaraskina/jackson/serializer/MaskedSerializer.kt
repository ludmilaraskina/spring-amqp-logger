package com.github.ludmilaraskina.jackson.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException

const val MASK_TO = 4

class MaskedSerializer : JsonSerializer<String?>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: String?, jgen: JsonGenerator?,
                           provider: SerializerProvider) {
        jgen?.writeString(value?.let { applyMask(it) })
    }

    private fun applyMask(value: String) =
            if (value.length <= MASK_TO) "*".repeat(value.length) else "*".repeat(MASK_TO) + value.substring(MASK_TO)
}
