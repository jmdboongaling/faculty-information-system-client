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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONObject;

public class Session{
    private String userID;
    public Session(String userID){
        this.userID = userID;
    }
    
    public static boolean loginAuthentication(String userID, String userPassword){
        boolean loginValid = false;
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://127.0.0.1:8084/CEU_FIS_WS/user/LoginAuth");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).queryParam("user_password", userPassword).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus()!=200){
                System.out.println(wsResponse.getStatus());
            }
            JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
            System.out.println(serverResponse.getString("server-response"));
            loginValid = serverResponse.getBoolean("login-valid");
            System.out.println("ID[" + userID + "] Login Valid? " + loginValid);

            }catch(Exception e){
                e.printStackTrace();
            }
        return loginValid;
    }
}
