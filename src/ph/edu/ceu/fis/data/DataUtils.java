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

import java.util.HashMap;
import java.sql.*;
import org.apache.commons.dbutils.DbUtils;
import org.mindrot.jbcrypt.BCrypt;
import ph.edu.ceu.fis.utils.ClientUtils;

public class DataUtils{
    
    public static String getAppTitle(){
        return "Centro Escolar University - Faculty Information System";
    }
    public static String getMachineID(){
        Connection embeddedConnection = null;
        PreparedStatement queryStatement = null;
        ResultSet rs = null;
        String machineID = null;
        try{
            embeddedConnection = DriverManager.getConnection("jdbc:derby:data", "fis_client", "26b3ba9fc3ba8e6bc6d009267337d705");
            queryStatement = embeddedConnection.prepareStatement("SELECT MACHINE_ID FROM APP.MACHINE_DATA WHERE MACHINE_KEY = ?");
            queryStatement.setString(1, "1c29962e40d1674f2d38f8a6dfb2f4f6");
            rs = queryStatement.executeQuery();
            if(rs.next()){
                machineID = rs.getString(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(queryStatement);
            DbUtils.closeQuietly(embeddedConnection);
        }
        return machineID;
    }
    
    public static String getMachineKey(){
        return getMachineID() + System.getProperty("os.name") + System.getProperty("os.version") + System.getProperty("os.arch") + System.getProperty("user.name");
    }
    
    public static String[] getWebServerHost(){
        Connection embeddedConnection = null;
        PreparedStatement queryStatement = null;
        ResultSet rs = null;
        String[] webServer = new String[2];
        try{
            embeddedConnection = DriverManager.getConnection("jdbc:derby:data", "fis_client", "26b3ba9fc3ba8e6bc6d009267337d705");
            queryStatement = embeddedConnection.prepareStatement("SELECT REMOTE_HOST, REMOTE_PORT FROM APP.WEB_DATA WHERE DATA_KEY = ?");
            queryStatement.setString(1, "90267ff53f2aaab3e03aa76db8199425");
            rs = queryStatement.executeQuery();
            if(rs.next()){
                webServer[0] = rs.getString(1);
                webServer[1] = Integer.toString(rs.getInt(2));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(queryStatement);
            DbUtils.closeQuietly(embeddedConnection);
        }
        return webServer;
    }
    
    public static String[] getFTPServerHost(){
        Connection embeddedConnection = null;
        PreparedStatement queryStatement = null;
        ResultSet rs = null;
        String[] webServer = new String[4];
        try{
            embeddedConnection = DriverManager.getConnection("jdbc:derby:data", "fis_client", "26b3ba9fc3ba8e6bc6d009267337d705");
            queryStatement = embeddedConnection.prepareStatement("SELECT REMOTE_HOST, REMOTE_PORT, REMOTE_USERNAME, REMOTE_PASSWORD FROM APP.WEB_DATA WHERE DATA_KEY = ?");
            queryStatement.setString(1, "102000daf76939f04c17b367ffde74ff");
            rs = queryStatement.executeQuery();
            if(rs.next()){
                webServer[0] = rs.getString(1);
                webServer[1] = Integer.toString(rs.getInt(2));
                webServer[2] = rs.getString(3);
                webServer[3] = rs.getString(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(queryStatement);
            DbUtils.closeQuietly(embeddedConnection);
        }
        return webServer;
    }
    
   
    
    
    
}
