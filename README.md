# spring-amqp-logger
Spring autoconfigure logger for AMQP requests/responses with ability to hide/mask information

## Run example
Application publishes message
```kotlin
    private data class ExampleMessage(
            val id: Int = 0,
            val description: String = "Example message",
            @Hidden
            val hidden: String = "Sensitive info",
            @Masked
            val masked: String = "1234567890"
    )
```

To start example complete following steps

1. docker-compose up
2. Start application SenderApplication.kt in example/sender. 
Logs produced:
```
Message to publish: {"id":0,"description":"Example message","hidden":"***","masked":"****567890"}
```
3. Start application ReceiverApplication.kt in example/receiver.
Logs produced:
```
Received message: {"id":0,"description":"Example message","hidden":"***","masked":"****567890"}
```
