import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    
	def messageLog = messageLogFactory.getMessageLog(message);
	if(messageLog != null){

		def filename = message.getHeaders().get("CamelFileName");		
		if(filename!=null){
			messageLog.addCustomHeaderProperty("filename", filename);		
        }
	}
	return message;
}