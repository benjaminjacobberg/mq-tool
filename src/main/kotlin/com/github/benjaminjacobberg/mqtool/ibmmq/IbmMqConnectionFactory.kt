package com.github.benjaminjacobberg.mqtool.ibmmq

import com.github.benjaminjacobberg.mqtool.ConnectionInformation
import com.ibm.mq.jms.MQQueueConnectionFactory
import com.ibm.msg.client.wmq.WMQConstants
import javax.jms.ConnectionFactory


open class IbmMqConnectionFactory {

    fun connectionFactory(connectionInformation: ConnectionInformation): ConnectionFactory {
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
