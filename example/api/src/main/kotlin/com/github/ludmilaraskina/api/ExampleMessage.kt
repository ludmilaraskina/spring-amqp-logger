package com.github.ludmilaraskina.api

import com.github.ludmilaraskina.annotation.Hidden
import com.github.ludmilaraskina.annotation.Masked

data class ExampleMessage(
        val id: Int = 0,
        val description: String = "Example message",
        @Hidden
        val hidden: String = "Sensitive info",
        @Masked
        val masked: String = "1234567890"
)
