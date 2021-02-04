package com.github.benjaminjacobberg.mqtool.sqs

import com.github.benjaminjacobberg.mqtool.ConnectionInformation
import com.github.benjaminjacobberg.mqtool.Message
import com.github.benjaminjacobberg.mqtool.MessageConsumer
import com.github.benjaminjacobberg.mqtool.MessageQueueInfoResponse

open class SqsMessageConsumer : MessageConsumer, SqsConnectionFactory() {
    override fun scrape(size: Int, connectionInformation: ConnectionInformation): List<Message> {
        TODO("Not yet implemented")
    }

    override fun info(connectionInformation: ConnectionInformation): MessageQueueInfoResponse {
        TODO("Not yet implemented")
    }

    override fun pull(jmsId: String, connectionInformation: ConnectionInformation): Message? {
        TODO("Not yet implemented")
    }
}