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
import java.util.HashMap;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.utils.ClientUtils;

public class BasicInformation{
    
    public static HashMap<String, String> getBasicInformation(String userID){
        HashMap<String, String> profileInformation = new HashMap<String, String>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/profile");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                ClientUtils.log(serverResponse.toString());
                profileInformation.put("user_type", serverResponse.getString("user_type"));
                profileInformation.put("last_name", serverResponse.getString("last_name"));
                profileInformation.put("first_name", serverResponse.getString("first_name"));
                profileInformation.put("middle_name", serverResponse.getString("middle_name"));
                profileInformation.put("gender", serverResponse.getString("gender"));
                profileInformation.put("email", serverResponse.getString("email"));
                profileInformation.put("civil_status", serverResponse.getString("civil_status"));
                profileInformation.put("birthday", serverResponse.getString("birthday"));
                profileInformation.put("employee_status", serverResponse.getString("employee_status"));
                profileInformation.put("employee_position", serverResponse.getString("employee_position"));
                profileInformation.put("employee_department", serverResponse.getString("employee_department"));
                profileInformation.put("birth_place", serverResponse.getString("birth_place"));
                profileInformation.put("religion", serverResponse.getString("religion"));
                profileInformation.put("citizenship", serverResponse.getString("citizenship"));
                profileInformation.put("present_address", serverResponse.getString("present_address"));
                profileInformation.put("present_contact", serverResponse.getString("present_contact"));
                profileInformation.put("residence_address", serverResponse.getString("residence_address"));
                profileInformation.put("residence_contact", serverResponse.getString("residence_contact"));
                profileInformation.put("spouse_name", serverResponse.getString("spouse_name"));
                profileInformation.put("emergency_name", serverResponse.getString("emergency_name"));
                profileInformation.put("emergency_relationship", serverResponse.getString("emergency_relationship"));
                profileInformation.put("emergency_contact", serverResponse.getString("emergency_contact"));
                profileInformation.put("specialization_fields", serverResponse.getString("specialization_fields"));
                
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
        return profileInformation;
    }
    
    public static void updateProfileInformation(HashMap<String, String> updateInformation){
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/update-profile");
            ClientResponse wsResponse =  webResource.queryParam("user_id", updateInformation.get("user_id"))
                                                    .queryParam("last_name", updateInformation.get("last_name"))
                                                    .queryParam("middle_name", updateInformation.get("middle_name"))
                                                    .queryParam("civil_status", updateInformation.get("civil_status"))
                                                    .queryParam("employee_status", updateInformation.get("employee_status"))
                                                    .queryParam("employee_position", updateInformation.get("employee_position"))
                                                    .queryParam("employee_department", updateInformation.get("employee_department"))
                                                    .queryParam("religion", updateInformation.get("religion"))
                                                    .queryParam("present_address", updateInformation.get("present_address"))
                                                    .queryParam("present_contact", updateInformation.get("present_contact"))
                                                    .queryParam("spouse_name", updateInformation.get("spouse_name"))
                                                    .queryParam("emergency_name", updateInformation.get("emergency_name"))
                                                    .queryParam("emergency_relationship", updateInformation.get("emergency_relationship"))
                                                    .queryParam("emergency_contact", updateInformation.get("emergency_contact"))
                                                    .queryParam("specialization_fields", updateInformation.get("specialization_fields"))
                                                   .accept("application/json").post(ClientResponse.class);
            
            
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                ClientUtils.log(serverResponse.toString());
                
                
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
    }
}
