package com.github.benjaminjacobberg.mqtool

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.ibm.mq.jms.MQQueueConnectionFactory
import com.ibm.msg.client.wmq.WMQConstants
import javax.jms.ConnectionFactory
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration








open class MqConnection {

    fun connectionFactory(connectionInformation: ConnectionInformation): ConnectionFactory {
        return if (connectionInformation.implementation == Implementation.IBM_MQ) {
            mqQueueConnectionFactory(connectionInformation)
        } else {
            sqsConnectionFactory(connectionInformation)
        }
    }

    private fun sqsConnectionFactory(connectionInformation: ConnectionInformation): SQSConnectionFactory {
        val builder = AmazonSQSClientBuilder.standard()
        builder.setEndpointConfiguration(EndpointConfiguration("http://localhost:4576", "us-east-1"))

        return SQSConnectionFactory(
                ProviderConfiguration(),
                AmazonSQSClientBuilder.defaultClient()
        )
    }

    private fun mqQueueConnectionFactory(connectionInformation: ConnectionInformation): MQQueueConnectionFactory {
        val connectionFactory = MQQueueConnectionFactory()
        connectionFactory.transportType = 1
        connectionFactory.channel = connectionInformation.channel
        connectionFactory.hostName = connectionInformation.host
        connectionFactory.port = connectionInformation.port
        connectionFactory.queueManager = connectionInformation.qm
        connectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, false)
        connectionFactory.setStringProperty(WMQConstants.USERID, connectionInformation.userId)
        connectionFactory.setStringProperty(WMQConstants.PASSWORD, connectionInformation.password)
        return connectionFactory
    }

}
