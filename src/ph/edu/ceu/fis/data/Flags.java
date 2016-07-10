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

    public static boolean serverIsAvailable(){
        boolean wsIsConnected = false,
                dbIsConnected = false,
                serverIsAvailable = wsIsConnected && dbIsConnected;
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/connection/connection-test");
            ClientResponse wsResponse = webResource.accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus()==200){
               JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                wsIsConnected = serverResponse.getBoolean("server_response");
                dbIsConnected = serverResponse.getBoolean("database_response");
                serverIsAvailable = wsIsConnected && dbIsConnected;
                ClientUtils.log(new java.util.Date() + " -  Server Status..............." + ClientUtils.parseBool(wsIsConnected && dbIsConnected));
                ClientUtils.log(new java.util.Date() + " -  Database Status..............." + ClientUtils.parseBool(dbIsConnected));
                ClientUtils.log(new java.util.Date() + " -  Web Service Status..............." + ClientUtils.parseBool(wsIsConnected));
            }else{
                wsIsConnected = false;
                dbIsConnected = false;
                serverIsAvailable = wsIsConnected && dbIsConnected;
                ClientUtils.log(new java.util.Date() + " -  Server Status..............." + ClientUtils.parseBool(wsIsConnected && dbIsConnected));
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());  
            }
        }catch(ClientHandlerException che){
            ClientUtils.log(new java.util.Date() + " -  Web Server Connection..........[FALSE!]\nError Message: " + che.getMessage());
            
        }catch(JSONException je){
            je.printStackTrace();
        }
        
        return serverIsAvailable;
    }
    
    public static boolean ftpIsAvailable(){
        boolean ftpIsAvailable = false;
        try{
            JSch jsch = new JSch();
            com.jcraft.jsch.Session session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
            session.setPassword(DataUtils.getFTPServerHost()[3]);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp)channel; //(ChannelSftp) session.openChannel("sftp");
            ftpIsAvailable = true;
            sftpChannel.disconnect();
            channel.disconnect();
            session.disconnect();
            jsch.removeAllIdentity();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return ftpIsAvailable;                
    }

}
