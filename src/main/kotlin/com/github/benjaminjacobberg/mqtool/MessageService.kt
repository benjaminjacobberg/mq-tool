package com.github.benjaminjacobberg.mqtool

import org.springframework.stereotype.Service

@Service
class MessageService {
    fun submit(message: Message, connectionInformation: ConnectionInformation) =
            MessageProducerFactory.createMessageProducer(connectionInformation.implementation).put(message, connectionInformation)
    fun list(size: Int, connectionInformation: ConnectionInformation): List<Message> =
            MessageConsumerFactory.createMessageConsumer(connectionInformation.implementation).scrape(size, connectionInformation)
    fun info(connectionInformation: ConnectionInformation): MessageQueueInfoResponse =
            MessageConsumerFactory.createMessageConsumer(connectionInformation.implementation).info(connectionInformation)
    fun pull(jmsId: String, connectionInformation: ConnectionInformation): Message? =
            MessageConsumerFactory.createMessageConsumer(connectionInformation.implementation).pull(jmsId, connectionInformation)
}
