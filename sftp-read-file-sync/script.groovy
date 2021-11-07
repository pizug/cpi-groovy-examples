import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sap.it.api.securestore.SecureStoreService;
import com.sap.it.api.securestore.UserCredential;
import com.sap.it.api.ITApiFactory;

import java.util.HashMap;
def Message processData(Message message) {

    //Properties
    map = message.getProperties();

    def sftp_hostname = map.get("sftp_hostname")
    def sftp_port = map.get("sftp_port")
    def sftp_credential = map.get("sftp_credential");
    def sftp_filename = map.get("sftp_filename");

    def secureStorageService =  ITApiFactory.getApi(SecureStoreService.class, null);
    def credential = secureStorageService.getUserCredential(sftp_credential);

    if (credential == null){
    // TODO
    }

    String sftp_username = credential.getUsername();
    String sftp_password =  new String(credential.getPassword());

    String encodedString = getFileContentFromSFTP(sftp_hostname,sftp_port,sftp_username,sftp_password,sftp_filename)
    message.setBody(encodedString);

    return message;
}

def String getFileContentFromSFTP(sftp_hostname,sftp_port,sftp_username,sftp_password,sftp_filename){
        JSch jsch = new JSch();
    Session session = null;
    try {
        session = jsch.getSession(sftp_username, sftp_hostname, sftp_port as Integer);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(sftp_password);
        session.connect(10000);

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;

        InputStream stream = sftpChannel.get(sftp_filename);

        def bytes = convertInputStreamToBytes(stream)

        def encodedString = Base64.getEncoder().encodeToString(bytes)

        return encodedString

    } catch (Exception e) {
        throw e;
    } finally {
        sftpChannel.exit();
        session.disconnect();
    }
}

def byte[] convertInputStreamToBytes(InputStream inputStream){

    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    while ((length = inputStream.read(buffer)) != -1) {
        result.write(buffer, 0, length);
    }

    return result.toByteArray()
}
