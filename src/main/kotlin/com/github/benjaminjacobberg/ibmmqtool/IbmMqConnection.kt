package com.github.benjaminjacobberg.ibmmqtool

import com.ibm.msg.client.jms.JmsConnectionFactory
import com.ibm.msg.client.jms.JmsFactoryFactory
import com.ibm.msg.client.wmq.WMQConstants
import javax.jms.JMSContext
import javax.jms.Queue

open class IbmMqConnection {
    fun queueConnection(connectionInformation: ConnectionInformation): Pair<JMSContext, Queue> {
        val jmsFactoryFactory: JmsFactoryFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER)
        val jmsConnectionFactory: JmsConnectionFactory = jmsFactoryFactory.createConnectionFactory()
        jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, connectionInformation.host)
        jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, connectionInformation.port)
        jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, connectionInformation.channel)
        jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT)
        jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, connectionInformation.qm)
        jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "JmsPutGet (JMS)")
        jmsConnectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true)
        jmsConnectionFactory.setStringProperty(WMQConstants.USERID, connectionInformation.userId)
        if (connectionInformation.password != null) jmsConnectionFactory.setStringProperty(WMQConstants.PASSWORD, connectionInformation.password)
        val jmsContext: JMSContext = jmsConnectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")

        return Pair(jmsContext, destination)
    }
}
