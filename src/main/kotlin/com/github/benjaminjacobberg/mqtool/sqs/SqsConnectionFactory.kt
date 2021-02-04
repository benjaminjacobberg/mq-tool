package com.github.benjaminjacobberg.mqtool.sqs

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.github.benjaminjacobberg.mqtool.ConnectionInformation
import javax.jms.ConnectionFactory


open class SqsConnectionFactory {

    fun connectionFactory(connectionInformation: ConnectionInformation): ConnectionFactory {
        val amazonSQSClientBuilder = AmazonSQSClientBuilder.standard()
        amazonSQSClientBuilder.setEndpointConfiguration(EndpointConfiguration("http://localhost:4576", "us-east-1"))

        return SQSConnectionFactory(
                ProviderConfiguration(),
                amazonSQSClientBuilder
        )
    }

}
