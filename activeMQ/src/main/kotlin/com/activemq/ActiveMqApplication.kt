package com.activemq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActiveMqApplication

fun main(args: Array<String>) {
    runApplication<ActiveMqApplication>(*args)
}
