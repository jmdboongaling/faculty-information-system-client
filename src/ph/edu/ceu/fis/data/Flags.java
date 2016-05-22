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
                    ftpIsConnected = true,
                    serverStatus;
    
    
    
    public Flags(){
        initFlags();
    }
    
    private void initFlags(){
        testWs();
    }
    public boolean serverIsAvailable(){
        return serverStatus;
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
            WebResource webResource = wsClient.resource("http://" + Constants.getServerAddress() + ":" + Constants.getServerPort() + "/CEU_FIS_WS/connection/connection-test");
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
            che.printStackTrace();
        }catch(JSONException je){
            je.printStackTrace();
        }
    }
    
    private void testFTP(){
        ftpIsConnected = true;
    }
    
    public void retryConnectToWS(){
        testWs();
    }
    
    public void retryConnectToFTP(){
        testFTP();
    }

}
