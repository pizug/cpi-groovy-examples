
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;


def Message processData(Message message) {

    String magicKeyword = '$B2B_SEG_COUNTER'

    def body = message.getBody(java.io.Reader)
    def parser = new XmlParser()
    def ediXmlDoc = parser.parse(body)
    
    Integer segmentCount = 0



    //find message and count segments inside
    ediXmlDoc.breadthFirst().findAll { possibleMessageNode ->
        // GroovyIDE returns String, Cloud Integration returns QName here.
        // println possibleMessageNode.name().getClass()
        // The implementation is flexible in the Class spec. .name() function returns java.lang.Object. 
        // It is possible that since this input has no namespace, it returns string.
        

        // it is a message node
        if ( getLocalName(possibleMessageNode).startsWith("M_") ){
            possibleMessageNode.breadthFirst().findAll { possibleSegmentNode -> 
                // it is a segment node
                if ( getLocalName(possibleSegmentNode).startsWith("S_") ){
                    // println possibleSegmentNode.name()
                    segmentCount ++
                }
            }
        }
    }

    // find nodes to be filled
    ediXmlDoc.breadthFirst().findAll { node ->
        if (node.localText().size() > 0 && node.localText().first() == magicKeyword ){
           node.value = segmentCount
        }
    }





    // Write document to body
    def sw = new StringWriter()
    def xmlNodePrinter = new XmlNodePrinter(new PrintWriter(sw))
    xmlNodePrinter.with {
        preserveWhitespace = true
    }
    xmlNodePrinter.print(ediXmlDoc)

    String result = sw.toString()
    message.setBody(result)

    return message;
    }


def String getLocalName( groovy.util.Node node ){
    def name = node.name() // can be java.lang.String or groovy.xml.QName or another object!
    String localName = ""
    if (name instanceof groovy.xml.QName) {
        groovy.xml.QName qname = (groovy.xml.QName) name
        localName = qname.getLocalPart()
    }
    else {
        localName = name.toString()
    }
    return localName
}