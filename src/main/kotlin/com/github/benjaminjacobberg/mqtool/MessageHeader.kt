package com.github.benjaminjacobberg.mqtool

data class MessageHeader(val jmsMessageId: String, val jmsCorrelationID: String?, val jmsTimestamp: String)
