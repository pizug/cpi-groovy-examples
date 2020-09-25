import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String;

    def messageLog = messageLogFactory.getMessageLog(message);
    if(messageLog != null){

        messageLog.setStringProperty("Logging", "Printing Payload As Attachment");
        messageLog.addAttachmentAsString("Message#1", body, "text/plain");

    }
    return message;
}