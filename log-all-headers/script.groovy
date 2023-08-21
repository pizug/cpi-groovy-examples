import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;

def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String;
    
    def builder = new JsonBuilder()
    builder(message.getHeaders())
    def prettyJson = JsonOutput.prettyPrint(builder.toString())
    def messageLog = messageLogFactory.getMessageLog(message);
    if(messageLog != null){
        messageLog.addAttachmentAsString("HTTP Headers:", prettyJson, "application/json");
     }
 
   
     
    return message;
}
