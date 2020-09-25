import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.xml.*;


def Message processData(Message message) {
	def body = message.getBody(java.lang.String) as String;
	def ag = new xml2csv()
	message.setBody(ag.execute(body))
	return message
}

class xml2csv {
	def execute(ins) {
		//define writer 
		def writer = new StringWriter()
		//parse input
		def parsedXml = new XmlParser().parseText(ins)
		
		def content = new XmlSlurper().parseText(ins)
		def header = content.Headers.children().collect().join(',')
		def csv = content.Items.inject(header){ result, row ->
		 [result, row.children().collect().join(',')].join("\n")
		}
		println csv.toString()
		
		return csv.toString()
	}
	
	static main(args) {
		def ag = new xml2csv()
		def writer = new FileWriter("test/case1/output.csv")
		writer.write(ag.execute(new File("test/case1/input.xml").text))	
		writer.close()
	}
}