import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {

    String logLevel = message.getProperty('SAP_MPL_LogLevel_Overall')

    if (logLevel in ['DEBUG', 'TRACE']) {
        messageLogFactory.getMessageLog(message)?.addAttachmentAsString('Message body', message.getBody(String), 'text/plain')
    }

    return message
}