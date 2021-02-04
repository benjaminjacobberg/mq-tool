package com.github.benjaminjacobberg.mqtool

import com.github.benjaminjacobberg.mqtool.ibmmq.IbmMqMessageProducer
import com.github.benjaminjacobberg.mqtool.sqs.SqsMessageProducer

class MessageProducerFactory {

    companion object {
        fun createMessageProducer(implementation: Implementation): MessageProducer = when (implementation) {
            Implementation.IBM_MQ -> object : IbmMqMessageProducer() {}
            Implementation.SQS -> object : SqsMessageProducer() {}
        }
    }

}