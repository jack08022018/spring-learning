package com.activemq.controller

import com.activemq.config.JMSProducer
import com.activemq.config.MessageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api"])
class ApiController {
    @Autowired
    lateinit var jmsProducer: JMSProducer

    @PostMapping(value = ["/sendMessage"])
    fun sendMessage() {
        jmsProducer.sendMessage("jms message!")
    }

    @PostMapping(value = ["/handleObj"])
    fun handleObj() {
        val message = MessageInfo(
            clientCode = "1001001",
            classCode = "1121245"
        )
        jmsProducer.handleObj(message)
    }
}