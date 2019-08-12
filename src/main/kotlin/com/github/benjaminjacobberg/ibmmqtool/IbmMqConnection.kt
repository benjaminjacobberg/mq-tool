package com.github.benjaminjacobberg.ibmmqtool

import com.ibm.mq.jms.MQQueueConnectionFactory
import com.ibm.msg.client.wmq.WMQConstants
import javax.jms.ConnectionFactory


open class IbmMqConnection {

    fun connectionFactory(connectionInformation: ConnectionInformation): ConnectionFactory {
        val mqQueueConnectionFactory = MQQueueConnectionFactory()
        mqQueueConnectionFactory.transportType = 1;
        mqQueueConnectionFactory.channel = connectionInformation.channel
        mqQueueConnectionFactory.hostName = connectionInformation.host
        mqQueueConnectionFactory.port = connectionInformation.port
        mqQueueConnectionFactory.queueManager = connectionInformation.qm
        mqQueueConnectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, false);
        mqQueueConnectionFactory.setStringProperty(WMQConstants.USERID, connectionInformation.userId);
        mqQueueConnectionFactory.setStringProperty(WMQConstants.PASSWORD, connectionInformation.password);

        return mqQueueConnectionFactory
    }

}
