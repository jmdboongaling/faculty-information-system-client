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
import com.sun.jersey.api.client.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.gui.Main;
import ph.edu.ceu.fis.utils.*;

public class Session{
    
    private String userID,
                   sessionID;
    
    private HashMap<String, String> profileInformation = new HashMap<String, String>();
    
    private ArrayList<ArrayList<String>> childEntries;
                                         
    private ArrayList<HashMap<String, String>> latestCourseEntries,
                                               bulletinEntries,
                                               educationEntries,
                                               workEntries,
                                               adminAppointmentEntries,
                                               licensureEntries,
                                               consultancyEntries,
                                               communityWorkEntries,
                                               professionalOrganizationEntries,
                                               eventAttendanceEntries,
                                               researchEntries,
                                               researchFellowshipAwardsEntries;
   
    public Session(String userID){
        this.userID = userID;
    }

    public String getUserID(){
        return this.userID;
    }
    
    public boolean loginAuthentication(String userID, String userPassword){
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
                        sessionID = serverResponse.getString("session_id");
                        if(!new File("tmp/" + ClientUtils.sha512Hash(userID, "fis")).exists()){
                            new File("tmp/" + ClientUtils.sha512Hash(userID, "fis")).mkdir();
                        }
                            
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
        BufferedImage profilePicture = null;
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
            profilePicture = ImageIO.read(newFile);
        }catch(Exception e){
            try{
                if(getProfileInformation().get("gender").equals("Male")){
                    profilePicture = ImageIO.read(new File("images/male.png"));
                }else{
                    profilePicture = ImageIO.read(new File("images/female.png"));
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        
        return profilePicture;
    }
    
    public void changeProfilePicture(File profilePictureFile){
        
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
            sftpChannel.put(new FileInputStream(profilePictureFile), ClientUtils.sha512Hash(userID, "profile_picture") + ".jpg");
        }catch(Exception e){
            e.printStackTrace();
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
        bulletinEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/bulletins/get");
            ClientResponse wsResponse = webResource.queryParam("manila_bulletins", String.valueOf(manilaBulletins)).queryParam("makati_bulletins", String.valueOf(makatiBulletins)).queryParam("malolos_bulletins", String.valueOf(malolosBulletins)).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> bulletinInfo = new HashMap<String, String>();
                    bulletinInfo.put("bulletin_id", serverResponse.getJSONObject(i).getString("bulletin_id"));
                    bulletinInfo.put("bulletin_subject", serverResponse.getJSONObject(i).getString("bulletin_subject"));
                    bulletinInfo.put("bulletin_content", serverResponse.getJSONObject(i).getString("bulletin_content"));
                    bulletinInfo.put("bulletin_poster", serverResponse.getJSONObject(i).getString("bulletin_poster"));
                    bulletinInfo.put("bulletin_campus", serverResponse.getJSONObject(i).getString("bulletin_campus"));
                    bulletinInfo.put("bulletin_time", serverResponse.getJSONObject(i).getString("bulletin_time"));
                    bulletinEntries.add(bulletinInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getBulletins(boolean manilaBulletins, boolean makatiBulletins, boolean malolosBulletins){
        invokeBulletin(manilaBulletins, makatiBulletins, malolosBulletins);
        return bulletinEntries;
    }
        
    private void invokeCourses(){
        latestCourseEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/courses/latest");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").get(ClientResponse.class);
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
                    latestCourseEntries.add(courseInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getLatestCourses(){
        invokeCourses();
        return latestCourseEntries;
    }
    
    private void invokeChildren(){
        childEntries = new ArrayList<ArrayList<String>>();
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
    }
    
    public ArrayList<ArrayList<String>> getChildren(){
        invokeChildren();
        return childEntries;
    }
    
    private void invokeEducation(){
        educationEntries = new ArrayList<HashMap<String, String>>();
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
    }
    
    public ArrayList<HashMap<String, String>> getEducation(){
        invokeEducation();
        return educationEntries;
    }
    
    private void invokeWork(){
        workEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/work");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> workInfo = new HashMap<String, String> ();
                    workInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    workInfo.put("work_position", serverResponse.getJSONObject(i).getString("work_position"));
                    workInfo.put("institution", serverResponse.getJSONObject(i).getString("institution"));
                    workInfo.put("address", serverResponse.getJSONObject(i).getString("address"));
                    workInfo.put("start_date", serverResponse.getJSONObject(i).getString("start_date"));
                    workInfo.put("end_date", serverResponse.getJSONObject(i).getString("end_date"));
                    workEntries.add(workInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getWork(){
        invokeWork();
        return workEntries;
    }
    
    
    private void invokeAdminAppointment(){
        adminAppointmentEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/admin-appointment");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> adminAppointmentInfo = new HashMap<String, String> ();
                    adminAppointmentInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    adminAppointmentInfo.put("employee_position", serverResponse.getJSONObject(i).getString("employee_position"));
                    adminAppointmentInfo.put("institution", serverResponse.getJSONObject(i).getString("institution"));
                    adminAppointmentInfo.put("department", serverResponse.getJSONObject(i).getString("department"));
                    adminAppointmentInfo.put("start_date", serverResponse.getJSONObject(i).getString("start_date"));
                    adminAppointmentInfo.put("end_date", serverResponse.getJSONObject(i).getString("end_date"));
                    adminAppointmentEntries.add(adminAppointmentInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getAdminAppointment(){
        invokeAdminAppointment();
        return adminAppointmentEntries;
    }
    
    private void invokeLicensure(){
        licensureEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/licensure");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> licensureInfo = new HashMap<String, String>();
                    licensureInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    licensureInfo.put("profession", serverResponse.getJSONObject(i).getString("profession"));
                    licensureInfo.put("year_taken", serverResponse.getJSONObject(i).getString("year_taken"));
                    licensureInfo.put("license_number", serverResponse.getJSONObject(i).getString("license_number"));
                    licensureInfo.put("prc_exp_date", serverResponse.getJSONObject(i).getString("prc_exp_date"));
                    licensureInfo.put("grade", serverResponse.getJSONObject(i).getString("grade"));
                    licensureInfo.put("rank", serverResponse.getJSONObject(i).getString("rank"));
          
                    licensureEntries.add(licensureInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getLicensure(){
        invokeLicensure();
        return licensureEntries;
    }
    
    private void invokeConsultancy(){
        consultancyEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/consultancy");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> consultancyInfo = new HashMap<String, String>();
                    consultancyInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    consultancyInfo.put("institution", serverResponse.getJSONObject(i).getString("institution"));
                    consultancyInfo.put("nature", serverResponse.getJSONObject(i).getString("nature"));
                    consultancyInfo.put("consultancy_date", serverResponse.getJSONObject(i).getString("consultancy_date"));
                    consultancyEntries.add(consultancyInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getConsultancy(){
        invokeConsultancy();
        return consultancyEntries;
    }
    
    private void invokeCommunityWork(){
        communityWorkEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/community-work");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> communityWorkInfo = new HashMap<String, String>();
                    communityWorkInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    communityWorkInfo.put("organization", serverResponse.getJSONObject(i).getString("organization"));
                    communityWorkInfo.put("nature", serverResponse.getJSONObject(i).getString("nature"));
                    communityWorkInfo.put("consultancy_date", serverResponse.getJSONObject(i).getString("consultancy_date"));
                    communityWorkEntries.add(communityWorkInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getCommunityWork(){
        invokeCommunityWork();
        return communityWorkEntries;
    }
    
    private void invokeProfessionalOrganization(){
        professionalOrganizationEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/professional-organization");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> professionalOrganizationInfo = new HashMap<String, String>();
                    professionalOrganizationInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    professionalOrganizationInfo.put("organization", serverResponse.getJSONObject(i).getString("organization"));
                    professionalOrganizationInfo.put("officer", String.valueOf(serverResponse.getJSONObject(i).getBoolean("officer")));
                    professionalOrganizationEntries.add(professionalOrganizationInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getProfessionalOrganization(){
        invokeProfessionalOrganization();
        return professionalOrganizationEntries;
    }
    
    private void invokeEventAttendance(){
        eventAttendanceEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/event-attendance");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> eventAttendanceInfo = new HashMap<String, String>();
                    eventAttendanceInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    eventAttendanceInfo.put("theme", serverResponse.getJSONObject(i).getString("theme"));
                    eventAttendanceInfo.put("venue", serverResponse.getJSONObject(i).getString("venue"));
                    eventAttendanceInfo.put("event_date", serverResponse.getJSONObject(i).getString("event_date"));
                    eventAttendanceEntries.add(eventAttendanceInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getEventAttendance(){
        invokeEventAttendance();
        return eventAttendanceEntries;
    }
    
    private void invokeResearch(){
        researchEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/research");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> researchInfo = new HashMap<String, String>();
                    researchInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    researchInfo.put("title", serverResponse.getJSONObject(i).getString("title"));
                    researchInfo.put("start_date", serverResponse.getJSONObject(i).getString("start_date"));
                    researchInfo.put("end_date", serverResponse.getJSONObject(i).getString("end_date"));
                    researchInfo.put("funding_agency", serverResponse.getJSONObject(i).getString("funding_agency"));
                    researchInfo.put("research_role", serverResponse.getJSONObject(i).getString("research_role"));
                    researchEntries.add(researchInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getResearch(){
        invokeResearch();
        return researchEntries;
    }
    
    private void invokeResearchFellowshipAwards(){
        researchFellowshipAwardsEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/research-fellowship-awards");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> researchFellowshipAwardsInfo = new HashMap<String, String>();
                    researchFellowshipAwardsInfo.put("id", serverResponse.getJSONObject(i).getString("id"));
                    researchFellowshipAwardsInfo.put("honor_award", serverResponse.getJSONObject(i).getString("honor_award"));
                    researchFellowshipAwardsInfo.put("institution", serverResponse.getJSONObject(i).getString("institution"));
                    researchFellowshipAwardsInfo.put("award_date", serverResponse.getJSONObject(i).getString("award_date"));researchFellowshipAwardsEntries.add(researchFellowshipAwardsInfo);
                    researchFellowshipAwardsEntries.add(researchFellowshipAwardsInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getResearchFellowshipAwards(){
        invokeResearchFellowshipAwards();
        return researchFellowshipAwardsEntries;
    }
    
    public void endSession(){
        try{
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/user/logout");
            ClientResponse wsResponse = webResource.queryParam("session_id", this.sessionID).accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                ClientUtils.log(new java.util.Date() + " -  Server Response..............." + serverResponse.getString("server_response"));
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }finally{
                String[] args = null;
            try {
                Main.SERVER_SOCKET.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                Main.main(args);
        }
    }
    
   
}
