package com.activemq.config

import kotlinx.serialization.Serializable

@Serializable
data class MessageInfo(
    private var batchCode: Int = 0,
    private var clientMemCode: Int = 0,
    private var clientCode: String = "",
    private var classCode: String = ""
) : java.io.Serializable