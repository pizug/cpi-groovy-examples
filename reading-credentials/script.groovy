import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

import com.sap.it.api.securestore.SecureStoreService;
import com.sap.it.api.securestore.UserCredential;
import com.sap.it.api.securestore.exception.SecureStoreException;
import com.sap.it.api.ITApiFactory;

def Message processData(Message message) {
  
  // Read from properties to make it more dynamic
  def mapProperties = message.getProperties();
  def credentialName = mapProperties.get("credential_name_property_key");

  // Credential specific code
  SecureStoreService secureStoreService = ITApiFactory.getService(SecureStoreService.class, null);
  UserCredential userCredential = secureStoreService.getUserCredential(credentialName);

  def user = userCredential.getUsername().toString()
  def pass = userCredential.getPassword().toString()

  message.setProperty("user", user);
  message.setProperty("pass", pass);
  return message;
}