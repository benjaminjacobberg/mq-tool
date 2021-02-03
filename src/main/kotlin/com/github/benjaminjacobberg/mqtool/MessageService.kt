package com.github.benjaminjacobberg.mqtool

import org.springframework.stereotype.Service

@Service
class MessageService(private val messageProducer: MessageProducer,
                     private val messageConsumer: MessageConsumer) {
    fun submit(message: Message, connectionInformation: ConnectionInformation) = messageProducer.put(message, connectionInformation)
    fun list(size: Int, connectionInformation: ConnectionInformation): List<Message> = messageConsumer.scrape(size, connectionInformation)
    fun info(connectionInformation: ConnectionInformation): QueueInfo = messageConsumer.info(connectionInformation)
    fun pull(jmsId: String, connectionInformation: ConnectionInformation): Message? = messageConsumer.pull(jmsId, connectionInformation)
}
