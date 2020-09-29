import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    
	def messageLog = messageLogFactory.getMessageLog(message);
	if(messageLog != null){

		def po_number = message.getHeaders().get("po_number");		
		if(po_number!=null){
			messageLog.addCustomHeaderProperty("po_number", po_number);		
        }
	}
	return message;
}