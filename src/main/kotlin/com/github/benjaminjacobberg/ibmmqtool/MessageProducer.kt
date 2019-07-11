package com.github.benjaminjacobberg.ibmmqtool

import com.ibm.msg.client.jms.JmsFactoryFactory
import com.ibm.msg.client.wmq.WMQConstants
import org.springframework.stereotype.Component
import javax.jms.JMSProducer
import javax.jms.Queue


@Component
class MessageProducer {
    fun put(submission: Submission) {
        val ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER)
        val cf = ff.createConnectionFactory()
        cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, submission.host)
        cf.setIntProperty(WMQConstants.WMQ_PORT, submission.port)
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, submission.channel)
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT)
        cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, submission.qm)
        cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "JmsPutGet (JMS)")
        cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true)
        cf.setStringProperty(WMQConstants.USERID, submission.userId)
        cf.setStringProperty(WMQConstants.PASSWORD, submission.password)
        val context = cf.createContext()
        val destination: Queue = context.createQueue("queue:///${submission.queue}?targetClient=1")
        val producer: JMSProducer = context.createProducer()
        producer.send(destination, submission.body)
    }
}