package com.activemq.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import javax.jms.ObjectMessage

@Component
class JMSConsumer {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @JmsListener(destination = QueueName.PRINT_NAME)
    fun handleMessage(message: String) {
        logger.info("received: $message")
    }

    @JmsListener(destination = QueueName.HANDLE_BATCH)
    fun handleObj(objectMessage: ObjectMessage) {
        try {
            val message = objectMessage.getObject() as MessageInfo
            logger.info("received: ${ObjectMapper().writeValueAsString(message)}")
        } catch (e: Exception) {
            logger.error("\nupdateStaff:\n", e)
        }
    }
}