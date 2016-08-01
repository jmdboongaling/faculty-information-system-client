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

import com.jcraft.jsch.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.faculty.FacultyMode;
import ph.edu.ceu.fis.utils.ClientUtils;

public class UserFiles{
    
    
    private String userID;
    
    private ArrayList<HashMap<String, String>> fileEntries;
    
    public UserFiles(String userID){
        this.userID = userID;
    }
    
    public String getUsedCapacity(){
        return ClientUtils.getFileSize(getUsedCapacityInBytes());
    }
    
    private Long getUsedCapacityInBytes(){
        long fileSize = 0;
        if(fileEntries.size() > 0){
            for(int i = 0; i < fileEntries.size(); i++){
                fileSize += Long.valueOf(fileEntries.get(i).get("file_size"));
            }
        }
        return fileSize;
    }
    
    public long getFreeSpace(){
        return 2500000000L - getUsedCapacityInBytes();
    }
    public String getUsedPercentage(){
        long totalCapacity = 2500000000L;
        double percentage = (Double.valueOf(getUsedCapacityInBytes())/ Double.valueOf(totalCapacity)) * 100;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
        return df.format(percentage) + "%";
    }
    
    
    
    private void invokeFiles(){
        fileEntries = new ArrayList<HashMap<String, String>>();
        try{
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/files/" + userID);
            ClientResponse wsResponse = webResource.accept("application/json").get(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONArray serverResponse = new JSONArray(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                for(int i = 0; i < serverResponse.length(); i++){
                    HashMap<String, String> fileInfo = new HashMap<String, String>();
                    fileInfo.put("id", serverResponse.getJSONObject(i).getString("entry_id"));
                    fileInfo.put("file_rel_path", serverResponse.getJSONObject(i).getString("file_rel_path"));
                    fileInfo.put("file_name", serverResponse.getJSONObject(i).getString("file_name"));
                    fileInfo.put("file_type", serverResponse.getJSONObject(i).getString("file_type"));
                    fileInfo.put("file_size", String.valueOf(serverResponse.getJSONObject(i).getLong("file_size")));
                    fileInfo.put("file_upload_date", serverResponse.getJSONObject(i).getString("file_upload_date"));
                    fileInfo.put("user_id", serverResponse.getJSONObject(i).getString("user_id"));

                    fileEntries.add(fileInfo);
                }
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
            }
        }catch(Exception e){
            ClientUtils.log(new java.util.Date() + " -  Error Message: " + e.getMessage());
        }
    }
    
    
    public ArrayList<HashMap<String, String>> getFileEntries(){
        invokeFiles();
        return fileEntries;
    }
    
    public void uploadFile(File fileUpload, String fileName, TransferEntry transferEntry){
        Thread uploadFile = new Thread(new Runnable() {
            @Override
            public void run() {
                doUpload(fileUpload, fileName, transferEntry);
            }
        });
        transferEntry.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile.stop();
            }
        });
        uploadFile.start();
    }
    
    
    private void doUpload(File fileUpload, String fileName, TransferEntry transferEntry){
        com.jcraft.jsch.Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;
        try{
            JSch jsch = new JSch();
            session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
            session.setPassword(DataUtils.getFTPServerHost()[3]);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp)channel;
            sftpChannel.cd("./www/" + ClientUtils.sha512Hash(userID, "files"));
            sftpChannel.put(new FileInputStream(fileUpload), ClientUtils.sha512Hash(FilenameUtils.getBaseName(fileUpload.getAbsolutePath()), "file") + "." + FilenameUtils.getExtension(fileUpload.getAbsolutePath()), new FTPProgressMonitor(fileUpload.length(), transferEntry, FTPProgressMonitor.UPLOAD_MODE));//, new SystemOutProgressMonitor());
            
            Client wsClient =  Client.create();
            WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/files/upload");
            ClientResponse wsResponse = webResource.queryParam("user_id", userID)
                                                   .queryParam("file_rel_path", "/" + ClientUtils.sha512Hash(userID, "files") + "/" + ClientUtils.sha512Hash(FilenameUtils.getBaseName(fileUpload.getAbsolutePath()), "file") + "." + FilenameUtils.getExtension(fileUpload.getAbsolutePath()))
                                                   .queryParam("file_name", fileName)
                                                   .queryParam("file_type", FilenameUtils.getExtension(fileUpload.getAbsolutePath()))
                                                   .queryParam("file_size", Long.valueOf(fileUpload.length()).toString())
                                                   .accept("application/json").post(ClientResponse.class);
            if(wsResponse.getStatus() == 200){
                JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                System.out.println(serverResponse);
                ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                ClientUtils.log(new java.util.Date() + " -  File Upload...............[OK!]");
            }else{
                ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
                ClientUtils.log(new java.util.Date() + " -  File Upload...............[FAILED!]");
            }

        }catch(JSchException e){
            e.printStackTrace();
        }catch(SftpException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }finally{
            FacultyMode.rightPanel.getFilesPanel().refreshFiles();
            sftpChannel.disconnect();
            channel.disconnect();
            session.disconnect();
        }
    }
    
    
    public void downloadFile(String userID, String fileRelPath, Long fileSize, TransferEntry transferEntry){
        Thread downloadFile = new Thread(new Runnable() {
            @Override
            public void run() {
                doDownload(userID, fileRelPath, fileSize, transferEntry);
            }
        });
        transferEntry.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFile.stop();
            }
        });
        downloadFile.start();
    }
    private void doDownload(String userID, String fileRelPath, Long fileSize, TransferEntry transferEntry){
        com.jcraft.jsch.Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;
        try{
            JSch jsch = new JSch();
            session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
            session.setPassword(DataUtils.getFTPServerHost()[3]);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp)channel; //(ChannelSftp) session.openChannel("sftp");
            sftpChannel.cd("./www/" + ClientUtils.sha512Hash(userID, "files"));
            FileOutputStream fileDownload = new FileOutputStream(new File(System.getProperty("user.home") + "/Downloads/" + FilenameUtils.getName(fileRelPath)));
            sftpChannel.get(FilenameUtils.getName(fileRelPath), fileDownload, new FTPProgressMonitor(fileSize, transferEntry, FTPProgressMonitor.DOWNLOAD_MODE));
            fileDownload.close();
            System.out.println("Downloaded!");

        }catch(JSchException e){
            e.printStackTrace();
        }catch(SftpException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            sftpChannel.disconnect();
            channel.disconnect();
            session.disconnect();
        }
    }
    
    public static void deleteFile(String userID, String fileID, String fileRelPath){
        Thread deleteFile = new Thread(new Runnable() {
            @Override
            public void run() {
                com.jcraft.jsch.Session session = null;
                Channel channel = null;
                ChannelSftp sftpChannel = null;
                try{
                    JSch jsch = new JSch();
                    session = jsch.getSession(DataUtils.getFTPServerHost()[2], DataUtils.getFTPServerHost()[0], Integer.parseInt(DataUtils.getFTPServerHost()[1]));
                    session.setPassword(DataUtils.getFTPServerHost()[3]);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();
                    channel = session.openChannel("sftp");
                    channel.connect();
                    sftpChannel = (ChannelSftp)channel;
                    sftpChannel.cd("./www/" + ClientUtils.sha512Hash(userID, "files"));
                    sftpChannel.rm(FilenameUtils.getName(fileRelPath));
                    Client wsClient =  Client.create();
                    WebResource webResource = wsClient.resource("http://" + DataUtils.getWebServerHost()[0] + ":" + DataUtils.getWebServerHost()[1] + "/CEU_FIS_WS/files/delete");
                    ClientResponse wsResponse = webResource.queryParam("file_id", fileID)
                                                           .accept("application/json").post(ClientResponse.class);
                    if(wsResponse.getStatus() == 200){
                        JSONObject serverResponse = new JSONObject(wsResponse.getEntity(String.class));
                        System.out.println(serverResponse);
                        ClientUtils.log(new java.util.Date() + " -  Server Response...............[OK!]");
                        ClientUtils.log(new java.util.Date() + " -  File Delete...............[OK!]");
                    }else{
                        ClientUtils.log(new java.util.Date() + " -  Server Response: " + wsResponse.getStatus());
                        ClientUtils.log(new java.util.Date() + " -  File Delete...............[FAILED!]");
                    }

                }catch(JSchException e){
                    e.printStackTrace();
                }catch(SftpException e){
                    e.printStackTrace();
                }catch(JSONException e){
                    e.printStackTrace();
                }finally{
                    FacultyMode.rightPanel.getFilesPanel().refreshFiles();
                    sftpChannel.disconnect();
                    channel.disconnect();
                    session.disconnect();
                }
            }
        });
        
        deleteFile.start();
    }
    
    public static boolean isAllowedFile(String fileExtension){
        String[] prohibitedFileExtensions = {"eml", "tmp", "exe", "mdb", "gadget", "shs", "scr", "lnk", "msi", "js", "asp", "bas", "mde", "jse", "chm", "mst", "vss", "fxp", "inf", "com", "ws", "cnt", "prg", "bat", "adp", "app", "ops", "url", "reg", "hta", "vbp", "crt", "mdt", "cpl", "pif", "vbs", "vbe", "ade", "msp", "sct", "cmd", "plg", "vst", "scf", "hlp", "vb", "msc", "mda", "mdw", "mat", "ksh", "xnk", "wsf", "mar", "mas", "mav", "isp", "maf", "ps1", "mam", "mdz", "prg", "ins", "shb", "der", "csh", "cer", "wsc", "mad", "vsw", "hpj", "mau", "psc1", "wsh", "vsmacros", "ps2", "ps1xml", "maq", "app", "osd", "pcd", "prf", "its", "mag", "ps2xml", "maw", "psc2", "msh2", "msh1xml", "mshxml", "app", "msh1", "msh", "msh2xml", "ins", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "7z", "7z.001", "7z.002", "7z.003", "7zip", "a00", "a01", "a02", "a03", "a04", "a05", "ace", "air", "apk", "appxbundle", "arc", "arj", "asec", "bar", "bin", "c00", "c01", "c02", "c03", "cab", "cbr", "cbz", "cso", "deb", "dlc", "gz", "gzip", "hqx", "inv", "ipa", "isz", "jar", "msu", "nbh", "pak", "part1.exe", "part1.rar", "part2.rar", "r00", "r01", "r02", "r03", "r04", "r05", "r06", "r07", "r08", "r09", "r10", "rar", "rpm", "sis", "sisx", "sit", "sitd", "sitx", "tar", "tar.gz", "tgz", "uax", "webarchive", "xap", "z01", "z02", "z03", "z04", "z05", "zab", "zip"};
        return !Arrays.asList(prohibitedFileExtensions).contains(fileExtension);
    }
    
    public static boolean isAllowedImage(String fileExtension){
        return fileExtension.equals("jpg");
    }
}

