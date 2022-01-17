import com.sap.gateway.ip.core.customdev.util.Message
import java.util.HashMap
import groovy.xml.*

def Message processData(Message message) {   
    //Parse sources
    def mainDoc = new XmlParser().parse(message.getBody(java.io.Reader))
    def extXml = message.getProperties().get("external_xml_data")
    def externalXmlDoc = new XmlParser().parseText(extXml)
    //Enhance and merge
    mainDoc.appendNode("Extension1").append(externalXmlDoc)    
    // Write document to body
    def result = (String)XmlUtil.serialize(mainDoc)    
    result = result.replaceAll("\n *?\n", "\n").trim()
    message.setBody(result)    
    return message
}