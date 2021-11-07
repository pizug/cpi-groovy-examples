
import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {

    byte[] body = message.getBody((byte[]).class);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    byte[] BOM =[ (byte) 0xEF, (byte) 0xBB, (byte) 0xBF ];

    bos.write(BOM,0,3); 
    bos.write(body,0,body.length);

    bos.flush();

    message.setBody(bos.toByteArray());
    return message;
}