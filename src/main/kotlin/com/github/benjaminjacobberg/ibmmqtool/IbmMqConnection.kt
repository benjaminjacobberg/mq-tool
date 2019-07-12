package com.github.benjaminjacobberg.ibmmqtool

import com.ibm.msg.client.jms.JmsFactoryFactory
import com.ibm.msg.client.wmq.WMQConstants
import javax.jms.JMSContext
import javax.jms.Queue

open class IbmMqConnection {
    fun queueConnection(connectionInformation: ConnectionInformation): Pair<JMSContext, Queue> {
        val ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER)
        val cf = ff.createConnectionFactory()
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, connectionInformation.host)
        cf.setIntProperty(WMQConstants.WMQ_PORT, connectionInformation.port)
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, connectionInformation.channel)
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT)
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, connectionInformation.qm)
        cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "JmsPutGet (JMS)")
        cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true)
        cf.setStringProperty(WMQConstants.USERID, connectionInformation.userId)
        cf.setStringProperty(WMQConstants.PASSWORD, connectionInformation.password)
        val context = cf.createContext()
        val destination: Queue = context.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        return Pair(context, destination)
    }
}