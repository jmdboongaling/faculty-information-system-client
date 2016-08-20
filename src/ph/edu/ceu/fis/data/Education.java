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
import java.util.HashMap;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.framework.MessageDialog;
import ph.edu.ceu.fis.utils.ClientUtils;

public class Education{
    
    public static ArrayList<HashMap<String, String>> getEducation(String userID){
        ArrayList<HashMap<String, String>> educationEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/education");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> educationInfo = new HashMap<String, String> ();
                    educationInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    educationInfo.put("degree", serverResponse.getJSONObject(i).getString("degree"));
                    educationInfo.put("institution", serverResponse.getJSONObject(i).getString("institution"));
                    educationInfo.put("start_date", serverResponse.getJSONObject(i).getString("start_date"));
                    educationInfo.put("honors", serverResponse.getJSONObject(i).getString("honors"));
                    educationInfo.put("end_date", serverResponse.getJSONObject(i).getString("end_date"));
                    educationInfo.put("units_earned", serverResponse.getJSONObject(i).getString("units_earned"));
                    educationInfo.put("scholarship", serverResponse.getJSONObject(i).getString("scholarship"));
                    educationInfo.put("state_units", serverResponse.getJSONObject(i).getString("state_units"));
                    educationEntries.add(educationInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
        return educationEntries; 
    }
    
    public static void addEducation(HashMap<String, String> educationInfo){
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/add-education");
            ClientResponse wsResponse =  webResource.queryParam("user_id", educationInfo.get("user_id"))
                                                    .queryParam("degree", educationInfo.get("degree"))
                                                    .queryParam("institution", educationInfo.get("institution"))
                                                    .queryParam("start_date", educationInfo.get("start_date"))
                                                    .queryParam("honors", educationInfo.get("honors"))
                                                    .queryParam("end_date", educationInfo.get("end_date"))
                                                    .queryParam("units_earned", educationInfo.get("units_earned"))
                                                    .queryParam("scholarship", educationInfo.get("scholarship"))
                                                    .queryParam("state_units", educationInfo.get("state_units"))
                                                    .accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                MessageDialog.showMessage("Record Update", "Record Successfully Added!");
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
    }
    
    public static void updateEducation(HashMap<String, String> educationInfo){
        ClientUtils.log("Size" + educationInfo.size());
        System.out.println(educationInfo.get("education_id"));
        System.out.println(educationInfo.get("honors"));
        System.out.println(educationInfo.get("end_date"));
        System.out.println(educationInfo.get("units_earned"));
        System.out.println(educationInfo.get("scholarship"));
        System.out.println(educationInfo.get("state_units"));
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/update-education");
            ClientResponse wsResponse =  webResource.queryParam("education_id", educationInfo.get("education_id"))
                                                    .queryParam("honors", educationInfo.get("honors"))
                                                    .queryParam("end_date", educationInfo.get("end_date"))
                                                    .queryParam("units_earned", educationInfo.get("units_earned"))
                                                    .queryParam("scholarship", educationInfo.get("scholarship"))
                                                    .queryParam("state_units", educationInfo.get("state_units"))
                                                    .accept("application/json").post(ClientResponse.class);
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
            e.printStackTrace();
        }
        
    }
    
    public static void deleteEducation(String educationID){
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/delete-education");
            ClientResponse wsResponse =  webResource.queryParam("education_id", educationID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                MessageDialog.showMessage("Record Update", "Record Successfully Deleted!");

            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        
    }
}
