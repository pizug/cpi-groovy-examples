import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.xml.*;
import java.util.HashMap;

def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String;
    
    // Get LogLevel of the artifact
    def map = message.getProperties();
	def logConfig = map.get("SAP_MessageProcessingLogConfiguration");
	def logLevel = (String) logConfig.logLevel;
	
    def messageLog = messageLogFactory.getMessageLog(message);
    if(messageLog != null){
        if(logLevel.equals("DEBUG") || logLevel.equals("TRACE")) {
            messageLog.setStringProperty("Logging", "Printing Payload As Attachment");
            messageLog.addAttachmentAsString("Outgoing", body , "text/plain");
        } 
    }

    return message;
}