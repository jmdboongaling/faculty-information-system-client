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
import ph.edu.ceu.fis.utils.ClientUtils;

public class UserClasses{
    
    private String userID;
    
    private ArrayList<HashMap<String, String>> courseSearchEntries,
                                               courseEntries;
    public UserClasses(String userID){
        this.userID = userID;
    }
    private void invokeCoursesSearch(String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        courseSearchEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/courses/open");
            ClientResponse wsResponse = webResource.queryParam("query", courseQuery).queryParam("campus", courseCampus).queryParam("semester", courseSemester).queryParam("school_year", courseSchoolYear).accept("application/json").get(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> courseInfo = new HashMap<String, String>();
                    courseInfo.put("course_id", serverResponse.getJSONObject(i).getString("course_id"));
                    courseInfo.put("course_code", serverResponse.getJSONObject(i).getString("course_code"));
                    courseInfo.put("course_description", serverResponse.getJSONObject(i).getString("course_description"));
                    courseInfo.put("course_day", serverResponse.getJSONObject(i).getString("course_day"));
                    courseInfo.put("course_time", serverResponse.getJSONObject(i).getString("course_time"));
                    courseInfo.put("course_building", serverResponse.getJSONObject(i).getString("course_building"));
                    courseInfo.put("course_room", serverResponse.getJSONObject(i).getString("course_room"));
                    courseInfo.put("course_year_section", serverResponse.getJSONObject(i).getString("course_year_section"));
                    courseInfo.put("course_semester", serverResponse.getJSONObject(i).getString("course_semester"));
                    courseInfo.put("course_school_year", serverResponse.getJSONObject(i).getString("course_school_year"));
                    courseInfo.put("course_campus", serverResponse.getJSONObject(i).getString("course_campus"));
                    courseInfo.put("lec_units", serverResponse.getJSONObject(i).getString("lec_units"));
                    courseInfo.put("lab_units", serverResponse.getJSONObject(i).getString("lab_units"));
                    courseSearchEntries.add(courseInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getSearchResults(String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        invokeCoursesSearch(courseQuery, courseCampus, courseSemester, courseSchoolYear);
        return courseSearchEntries;
    }
    
    private void invokeCourses(String userID, String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        courseEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/courses/" + userID);
            ClientResponse wsResponse = webResource.queryParam("query", courseQuery).queryParam("campus", courseCampus).queryParam("semester", courseSemester).queryParam("school_year", courseSchoolYear).accept("application/json").get(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> courseInfo = new HashMap<String, String>();
                    courseInfo.put("course_id", serverResponse.getJSONObject(i).getString("course_id"));
                    courseInfo.put("course_code", serverResponse.getJSONObject(i).getString("course_code"));
                    courseInfo.put("course_description", serverResponse.getJSONObject(i).getString("course_description"));
                    courseInfo.put("course_day", serverResponse.getJSONObject(i).getString("course_day"));
                    courseInfo.put("course_time", serverResponse.getJSONObject(i).getString("course_time"));
                    courseInfo.put("course_building", serverResponse.getJSONObject(i).getString("course_building"));
                    courseInfo.put("course_room", serverResponse.getJSONObject(i).getString("course_room"));
                    courseInfo.put("course_year_section", serverResponse.getJSONObject(i).getString("course_year_section"));
                    courseInfo.put("course_semester", serverResponse.getJSONObject(i).getString("course_semester"));
                    courseInfo.put("course_school_year", serverResponse.getJSONObject(i).getString("course_school_year"));
                    courseInfo.put("course_campus", serverResponse.getJSONObject(i).getString("course_campus"));
                    courseInfo.put("lec_units", serverResponse.getJSONObject(i).getString("lec_units"));
                    courseInfo.put("lab_units", serverResponse.getJSONObject(i).getString("lab_units"));
                    courseEntries.add(courseInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getCourses(String userID, String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        invokeCourses(userID, courseQuery, courseCampus, courseSemester, courseSchoolYear);
        return courseEntries;
    }
    
    
}
