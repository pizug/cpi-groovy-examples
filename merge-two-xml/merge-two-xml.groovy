import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import org.xml.sax.InputSource;

def Message processData(Message message) {

    def body = message.getBody(java.io.Reader)
    def mainDoc = new XmlParser().parse(body)


    def map = message.getProperties();
    String value = map.get("external_xml_data");
    def externalXmlDoc = new XmlParser().parse(new InputSource(new StringReader(value)))

    // Create "Extension1" element for clarity
    def nodeBuilder = new NodeBuilder();
    def node1 = nodeBuilder.Extension1{}

    // Merge
    node1.append(externalXmlDoc)
    mainDoc.append(node1)

    
    // Write document to body
    def sw = new StringWriter()
    def xmlNodePrinter = new XmlNodePrinter(new PrintWriter(sw))
    xmlNodePrinter.with {
        preserveWhitespace = true
    }   
    xmlNodePrinter.print(mainDoc)

    String result = sw.toString()
    message.setBody(result)
    
    return message;
}
