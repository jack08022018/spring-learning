package com.activemq.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.jms.ConnectionFactory

@Component
class JMSProducer {
    @Autowired
    lateinit var connectionFactory: ConnectionFactory

    lateinit var jmsTemplate: JmsTemplate

    @PostConstruct
    fun init() {
        jmsTemplate = JmsTemplate(connectionFactory!!)
    }

    fun sendMessage(message: String?) {
        jmsTemplate.send(QueueName.PRINT_NAME) { session ->
            session.createTextMessage(message)
        }
    }

    fun handleObj(message: MessageInfo) {
        jmsTemplate.send(QueueName.HANDLE_BATCH) { session ->
            val objectMessage = session.createObjectMessage(message)
            objectMessage.setObject(message)
            objectMessage
        }
    }
}