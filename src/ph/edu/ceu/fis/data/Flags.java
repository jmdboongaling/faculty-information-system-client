/**
 **  #####  ####### #     #         ####### ###  #####  
 ** #     # #       #     #         #        #  #     # 
 ** #       #       #     #         #        #  #       
 ** #       #####   #     #         #####    #   #####  
 ** #       #       #     #         #        #        # 
 ** #     # #       #     #         #        #  #     # 
 ** #####  #######  #####          #       ###  #####  
 **                        #######   
 ** @author: jmdboongaling @edit:
 ** @Comments: 
 ** 
 ** 
 **/
package ph.edu.ceu.fis.data; 

import com.jcraft.jsch.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.utils.*;



public class Flags{

    private boolean wsIsConnected = false,
                    dbIsConnected = false,
                    ftpIsConnected = false,
                    serverStatus;
    
    
    
    public Flags(){
        initFlags();
}
    
    private void initFlags(){
        testWs();
        testFTP();
    }
    
    public boolean wsIsConnected(){
        return wsIsConnected;
    }
    
    public boolean ftpIsConnected(){
        return ftpIsConnected;
    }
    
    private void testWs(){
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/connection/connection-test");
            ClientResponse wsResponse = webResource.accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus()!=200){
                ClientUtils.log(new java.util.Date() + "- Server Status..............." + ClientUtils.parseBool(wsIsConnected && dbIsConnected));
                ClientUtils.log(new java.util.Date() + "- Database Status..............." + ClientUtils.parseBool(dbIsConnected));
                ClientUtils.log(new java.util.Date() + "- Web Service Status..............." + ClientUtils.parseBool(wsIsConnected));
                ClientUtils.log(new java.util.Date() + "- Server Response: " + wsResponse.getStatus());
                wsIsConnected = false;
                dbIsConnected = false;
                serverStatus = false;
            }else{
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                wsIsConnected = serverResponse.getBoolean("server-response");
                dbIsConnected = serverResponse.getBoolean("database-response");
                serverStatus = wsIsConnected && dbIsConnected;
                ClientUtils.log(new java.util.Date() + "- Server Status..............." + ClientUtils.parseBool(wsIsConnected && dbIsConnected));
                ClientUtils.log(new java.util.Date() + "- Database Status..............." + ClientUtils.parseBool(dbIsConnected));
                ClientUtils.log(new java.util.Date() + "- Web Service Status..............." + ClientUtils.parseBool(wsIsConnected));
                
                
            }
        }catch(ClientHandlerException che){
            ClientUtils.log(new java.util.Date() + "- Web Server Connection..........[FALSE!]\nError Message: " + che.getMessage());
            
        }catch(JSONException je){
            je.printStackTrace();
        }
    }
    
    private void testFTP(){
        ftpIsConnected = false;
        try{
            JSch jsch = new JSch();
            com.jcraft.jsch.Session session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
            session.setPassword(DataUtils.getFTPServerHost()[3]);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection established.");
            System.out.println("Crating SFTP Channel.");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp)channel; //(ChannelSftp) session.openChannel("sftp");

            System.out.println("SFTP Channel created.");
            ftpIsConnected = true;
            sftpChannel.disconnect();
            channel.disconnect();
            session.disconnect();
            jsch.removeAllIdentity();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        


    
        
         
            
                    
    }
    
    public void retryConnectToWS(){
        testWs();
    }
    
    public void retryConnectToFTP(){
        testFTP();
    }

}
