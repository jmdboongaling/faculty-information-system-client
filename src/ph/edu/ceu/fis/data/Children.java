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
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.framework.MessageDialog;
import ph.edu.ceu.fis.utils.ClientUtils;

public class Children{
    
    public static ArrayList<ArrayList<String>> getChildren(String userID){
        ArrayList<ArrayList<String>> childEntries = new ArrayList<ArrayList<String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/children");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    ArrayList<String> childInfo = new ArrayList<String>();
                    childInfo.add(serverResponse.getJSONObject(i).getString("id"));
                    childInfo.add(serverResponse.getJSONObject(i).getString("child_name"));
                    childInfo.add(serverResponse.getJSONObject(i).getString("gender"));
                    childInfo.add(serverResponse.getJSONObject(i).getString("birth_order"));
                    childEntries.add(childInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
        return childEntries;
    }
    
    
    public static void addChild(String userID, String childName, String childGender, String childBirthOrder){
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/add-children");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).queryParam("child_name", childName).queryParam("gender", childGender).queryParam("birth_order", childBirthOrder).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
               
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public static void updateChild(String childID, String childName, String childGender, String childBirthOrder){
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/update-children");
            ClientResponse wsResponse = webResource.queryParam("child_id", childID).queryParam("child_name", childName).queryParam("gender", childGender).queryParam("birth_order", childBirthOrder).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                MessageDialog.showMessage("Record Update", "Record Successfully Updated!");
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    public static void deleteChild(String childID){
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/delete-children");
            ClientResponse wsResponse = webResource.queryParam("child_id", childID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                MessageDialog.showMessage("Record Delete", "Record Successfully Deleted!");
               
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
}
