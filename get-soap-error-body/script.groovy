import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import org.w3c.dom.Node;
import groovy.xml.*

def Message processData(Message message) {
    def map = message.getProperties();
    def ex = map.get("CamelExceptionCaught");
    if (ex.getClass().getCanonicalName().equals("org.apache.cxf.binding.soap.SoapFault")) {
        // log, use, or set to body

        // You can also get statusCode or Message
        // String exceptionMessage = ex.getMessage();

        // Fault Detail Element
        def xml = XmlUtil.serialize(ex.getOrCreateDetail());
        message.setBody(xml);
    }
    
    return message;
}