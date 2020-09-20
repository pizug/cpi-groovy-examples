import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    println "You can print and see the result in the console!"
    //Body 
    def body = message.getBody(String);
    message.setBody(body + "Body is modified");
    
    //Headers 
    def map = message.getHeaders();
    def value = map.get("oldHeader");
    println "oldHeader value: " +value
    message.setHeader("oldHeader", value + " World!");
    message.setHeader("newHeader", "newHeader value");

    //Properties 
    map = message.getProperties();
    value = map.get("oldProperty");
    message.setProperty("oldProperty", value + "modified");
    message.setProperty("newProperty", "newProperty value");
    return message;
    }
