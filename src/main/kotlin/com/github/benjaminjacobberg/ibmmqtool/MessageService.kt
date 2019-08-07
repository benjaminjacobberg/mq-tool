package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Service

@Service
class MessageService(private val messageProducer: MessageProducer,
                     private val messageConsumer: MessageConsumer) {
    fun submit(message: Message, connectionInformation: ConnectionInformation) = messageProducer.put(message, connectionInformation)
    fun get(size: Int, connectionInformation: ConnectionInformation): List<Message> = messageConsumer.scrape(size, connectionInformation)
    fun info(connectionInformation: ConnectionInformation): QueueInfo = messageConsumer.info(connectionInformation)
}
