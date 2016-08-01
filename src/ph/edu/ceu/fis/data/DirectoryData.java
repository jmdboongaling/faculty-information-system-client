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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.codehaus.jettison.json.JSONArray;
import ph.edu.ceu.fis.utils.ClientUtils;

public class DirectoryData{
    
    private ArrayList<HashMap<String, String>> directorySearchEntries;

    private void invokeDirectorySearch(String directoryQuery){
        directorySearchEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/directory/search");
            ClientResponse wsResponse = webResource.queryParam("query", directoryQuery).accept("application/json").get(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> directoryInfo = new HashMap<String, String>();
                    directoryInfo.put("user_id", serverResponse.getJSONObject(i).getString("user_id"));
                    directoryInfo.put("last_name", serverResponse.getJSONObject(i).getString("last_name"));
                    directoryInfo.put("first_name", serverResponse.getJSONObject(i).getString("first_name"));
                    directoryInfo.put("middle_name", serverResponse.getJSONObject(i).getString("middle_name"));
                    directoryInfo.put("gender", serverResponse.getJSONObject(i).getString("gender"));
                    directoryInfo.put("email", serverResponse.getJSONObject(i).getString("email"));
                    directoryInfo.put("employee_status", serverResponse.getJSONObject(i).getString("employee_status"));
                    directoryInfo.put("employee_position", serverResponse.getJSONObject(i).getString("employee_position"));
                    directoryInfo.put("employee_department", serverResponse.getJSONObject(i).getString("employee_department"));
                    directorySearchEntries.add(directoryInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    public ArrayList<HashMap<String, String>> getSearchResults(String directoryQuery){
        invokeDirectorySearch(directoryQuery);
        return directorySearchEntries;
    }
    
    public BufferedImage getProfilePicture(String userID, String gender){
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
                if(gender.equals("Male")){
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
    
}
