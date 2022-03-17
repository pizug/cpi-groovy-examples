import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
                
                // get a map of properties
                def map = message.getProperties();
                
                // get an exception java class instance
                def ex = map.get("CamelExceptionCaught");
                if (ex!=null) {
                  
                  // an rfc adapter throws an instance of com.sap.it.rt.adapter.rfc.exceptions.RFCLibraryException
                  if (ex.getClass().getCanonicalName().equals("com.sap.it.rt.adapter.rfc.exceptions.RFCLibraryException")) {
                    
                    // save the rfc error response as a message attachment 
                    def messageLog = messageLogFactory.getMessageLog(message);
                    messageLog.addAttachmentAsString("rfc.ResponseBody", "<Exception>" + ex.getCause() + "</Exception>", "text/plain");
                    // copy the rfc error response to the message body in xml
                    message.setBody("<Exception>" + ex.getCause() + "</Exception>");
                    // copy the rfc error response to the message body in json
                    //message.setBody("{ \"Exception\": \"" + ex.getCause() +"\" }");
                    // set custom response code
                    message.setHeader("CamelHttpResponseCode","400");
                  }
                
                }

                return message;
}
