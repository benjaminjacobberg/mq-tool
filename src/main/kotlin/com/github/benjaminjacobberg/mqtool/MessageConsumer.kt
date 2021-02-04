package com.github.benjaminjacobberg.mqtool

interface MessageConsumer {
    fun scrape(size: Int, connectionInformation: ConnectionInformation): List<Message>
    fun info(connectionInformation: ConnectionInformation): MessageQueueInfoResponse
    fun pull(jmsId: String, connectionInformation: ConnectionInformation): Message?
}