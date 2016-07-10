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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import ph.edu.ceu.fis.utils.*;
import com.sun.jersey.api.client.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Session{
    
    private String userID;
    
    private HashMap<String, String> profileInformation = new HashMap<String, String>();
    
    private ArrayList<ArrayList<String>> bulletinEntries,
                                         courseEntries;
    public Session(String userID){
        this.userID = userID;
    }

    public String getUserID(){
        return this.userID;
    }
    public static boolean loginAuthentication(String userID, String userPassword){
        boolean loginValid = false;
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/LoginAuth");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).queryParam("user_password", userPassword).queryParam("machine_id", DataUtils.getMachineID()).queryParam("machine_key", DataUtils.getMachineKey()).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + " -  Server Response..............." + serverResponse.getString("server_response"));
                switch(serverResponse.getInt("server_response")){
                    case 0:
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "[Login FAILED!]");
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "INVALID CREDENTIALS");
                        break;
                    case 1:
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "[Login OK!]");
                        loginValid = true;
                        break;
                    case 2:
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "[Login FAILED!]");
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "MACHINE NOT REGISTERED");
                        ClientUtils.log(new java.util.Date() + " -  Server Message..............." + "ATTEMPT FLAGGED!");
                        break;
                }
                
                
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
        return loginValid;
    }
    
    public BufferedImage getProfilePicture(){
        try{
            JSch jsch = new JSch();
            com.jcraft.jsch.Session session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
            session.setPassword(DataUtils.getFTPServerHost()[3]);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp)channel; //(ChannelSftp) session.openChannel("sftp");
            sftpChannel.cd("./www/" + ClientUtils.sha512Hash("profile_pictures", "fis"));
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(sftpChannel.get(ClientUtils.sha512Hash(userID, "profile_picture") + ".jpg"));
            File newFile = new File("tmp/" + ClientUtils.sha512Hash(userID, "fis") + "/" + ClientUtils.sha512Hash(userID, "profile_picture") + ".jpg");
            OutputStream os = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int readCount;
            while( (readCount = bis.read(buffer)) > 0){
            bos.write(buffer, 0, readCount);
            }
            bis.close();
            bos.close();
            return ImageIO.read(newFile);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void invokeProfileInformation(){
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/profile");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
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
    }
    
    public HashMap<String, String> getProfileInformation(){
        invokeProfileInformation();
        return profileInformation;
    }
    
    private void invokeBulletin(boolean manilaBulletins, boolean makatiBulletins, boolean malolosBulletins){
        bulletinEntries = new ArrayList<ArrayList<String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/bulletins/get");
            ClientResponse wsResponse = webResource.queryParam("manila_bulletins", String.valueOf(manilaBulletins)).queryParam("makati_bulletins", String.valueOf(makatiBulletins)).queryParam("malolos_bulletins", String.valueOf(malolosBulletins)).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    ArrayList<String> bulletinInfo = new ArrayList<String>();
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_id"));
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_subject"));
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_content"));
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_poster"));
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_campus"));
                    bulletinInfo.add(serverResponse.getJSONObject(i).getString("bulletin_time"));
                    bulletinEntries.add(bulletinInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<ArrayList<String>> getBulletins(boolean manilaBulletins, boolean makatiBulletins, boolean malolosBulletins){
        invokeBulletin(manilaBulletins, makatiBulletins, malolosBulletins);
        return bulletinEntries;
    }
        
    private void invokeCourses(){
        courseEntries = new ArrayList<ArrayList<String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/courses/latest");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").get(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    ArrayList<String> courseInfo = new ArrayList<String>();
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_id"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_code"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_description"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_day"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_time"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_building"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_room"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_year_section"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_semester"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_school_year"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_campus"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("lec_units"));
                    courseInfo.add(serverResponse.getJSONObject(i).getString("lab_units"));
                    courseEntries.add(courseInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<ArrayList<String>> getCourses(){
        invokeCourses();
        return courseEntries;
    }
   
}
