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

import ph.edu.ceu.fis.utils.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONObject;

public class Session{
    private String userID;
    private ArrayList<String> profileInformation = new ArrayList<>();
    public Session(String userID){
        this.userID = userID;
    }

    public static boolean loginAuthentication(String userID, String userPassword){
        boolean loginValid = false;
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/LoginAuth");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).queryParam("user_password", userPassword).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus()!=200){
                ClientUtils.log(new java.util.Date() + "- Server Response: " + wsResponse.getStatus());
            }else{
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + "- Server Response..............." + serverResponse.getString("server-response"));
                loginValid = serverResponse.getBoolean("login-valid");
                ClientUtils.log(new java.util.Date() + "- Attempting Login For User " + userID + "..............." + ClientUtils.parseBool(loginValid));
}
        }catch(Exception e){
            e.printStackTrace();
        }
        return loginValid;
    }
    
    public void invokeWsProfileInformation(){
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/profile");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus()!=200){
                ClientUtils.log(new java.util.Date() + "- Server Response: " + wsResponse.getStatus());
            }else{
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + "- Server Response..............." + serverResponse.getString("server-response"));
                profileInformation.add(userID);
                profileInformation.add(serverResponse.getString("user-type"));
                profileInformation.add(serverResponse.getString("email-address"));
                profileInformation.add(serverResponse.getString("first-name"));
                profileInformation.add(serverResponse.getString("middle-name"));
                profileInformation.add(serverResponse.getString("last-name"));
                profileInformation.add(serverResponse.getString("birthday"));
                profileInformation.add(serverResponse.getString("birthplace"));
                profileInformation.add(serverResponse.getString("religion"));
                profileInformation.add(serverResponse.getString("citizenship"));
                profileInformation.add(serverResponse.getString("present-position"));
                profileInformation.add(serverResponse.getString("department"));
                profileInformation.add(serverResponse.getString("present-address"));
                profileInformation.add(serverResponse.getString("present-address-contact"));
                profileInformation.add(serverResponse.getString("permanent-address"));
                profileInformation.add(serverResponse.getString("permanent-address-contact"));
                profileInformation.add(serverResponse.getString("civil-status"));
                profileInformation.add(serverResponse.getString("spouse"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getProfileInformation(){
        return profileInformation;
    }
}
