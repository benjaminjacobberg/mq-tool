package com.github.benjaminjacobberg.mqtool

import com.github.benjaminjacobberg.mqtool.ibmmq.IbmMqMessageConsumer
import com.github.benjaminjacobberg.mqtool.sqs.SqsMessageConsumer

class MessageConsumerFactory {

    companion object {
        fun createMessageConsumer(implementation: Implementation): MessageConsumer = when (implementation) {
            Implementation.IBM_MQ -> object : IbmMqMessageConsumer() {}
            Implementation.SQS -> object : SqsMessageConsumer() {}
        }
    }

}