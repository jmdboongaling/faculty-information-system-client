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
package ph.edu.ceu.fis.gui;

import java.awt.Color;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import ph.edu.ceu.fis.utils.ClientUtils;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.commons.dbutils.DbUtils;
import org.mindrot.jbcrypt.BCrypt;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FrameWorkUtils;

public class Main{
    
    public static LoginForm loginForm;
    public static void main(String[]args){
        
        ClientUtils.log(new java.util.Date() + "- Application Started...............[OK!]");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                UIManager.put("Separator.background", Color.BLACK);
                UIManager.put("Separator.foreground", Color.BLACK.brighter().brighter().brighter());
                UIManager.put("ScrollBar.background", Color.WHITE);
                UIManager.put("ScrollBar.foreground", Color.WHITE);
                UIManager.put("ScrollBar.thumb", Color.BLACK.brighter().brighter().brighter());
                UIManager.put("ScrollBar.thumbDarkShadow", Color.BLACK.brighter().brighter().brighter());
                UIManager.put("ScrollBar.thumbHighlight", Color.BLACK.brighter().brighter().brighter());
                UIManager.put("ScrollBar.thumbShadow", Color.BLACK.brighter().brighter().brighter());
                UIManager.put("ScrollBar.track", Color.WHITE);
                UIManager.put("ScrollBar.trackHighlight", Color.WHITE);
                UIManager.put("ToolTip.background", java.awt.Color.WHITE);
                UIManager.put("ToolTip.font", ph.edu.ceu.fis.framework.FrameWorkUtils.getSystemFont().deriveFont(14f));
                loginForm = new LoginForm();
            }
        });
        
        /*Session systemSession = new Session("13-11448");
        systemSession.invokeWsProfileInformation();
        new SystemFrame(systemSession);*/
        
        //ClientUtils.log(encryptPassword("jWBExBkVYar7QJnbjaj5rxyd", "fis_client"));
        //ClientUtils.log(encryptPassword("ftp", "fis_client"));
        //jWBExBkVYar7QJnbjaj5rxyd
        //ClientUtils.log(org.mindrot.jbcrypt.BCrypt.hashpw("123", BCrypt.gensalt(12)));
        //encryptPassword("8", "fis_client").toUpperCase());
        //System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
       //new Main();
        
        
    }
    
    
    /*public Main(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:derby:data;create=true", "fis_client", "26b3ba9fc3ba8e6bc6d009267337d705");
            System.out.println("Goods");
            DbUtils.closeQuietly(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
   
    
    
  
    
   
    
}
