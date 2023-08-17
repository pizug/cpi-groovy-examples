import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil

def Message processData(Message message) {
    //Body 
    def body = message.getBody(String);

    def lines = body.trim().split('\n')
    def headers = lines[0].split(',')
    def data = lines[1..-1].collect { it.split(',') }

    def xml = new StringBuilder()

    xml.append('<root>')
    data.each {
        row ->
            xml.append('<record>')
        headers.eachWithIndex {
            header,
            index ->
            xml.append("<${header}>${row[index]}</${header}>")
        }
        xml.append('</record>')
    }
    xml.append('</root>')

    def formattedXml = XmlUtil.serialize(xml.toString())

    message.setBody(formattedXml)
    return message;
}