import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {

    String logLevel = message.getProperty('SAP_MessageProcessingLogConfiguration').logLevel.toString()

    if (logLevel in ['DEBUG', 'TRACE']) {
        messageLogFactory.getMessageLog(message)?.addAttachmentAsString('Message body', message.getBody(String), 'text/plain')
    }

    return message
}