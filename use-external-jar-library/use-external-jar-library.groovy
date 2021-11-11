// These lines are ignored in Cloud Integration runtime:

//groovyide.com:jar:file:C:/opt/jars/jackson-core-2.12.5.jar
//groovyide.com:jar:file:C:/opt/jars/jackson-databind-2.12.5.jar
//groovyide.com:jar:file:C:/opt/jars/jackson-annotations-2.12.5.jar

//imports will not give error:
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.type.CollectionType;

import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
    
	return message;
}