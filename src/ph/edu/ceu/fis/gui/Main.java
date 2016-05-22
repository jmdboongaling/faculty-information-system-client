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
import ph.edu.ceu.fis.utils.ClientUtils;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ph.edu.ceu.fis.data.Session;

public class Main{
    
    public static LoginForm loginForm;
    public static void main(String[]args){
        
        ClientUtils.log(new java.util.Date() + "- Application Started...............[OK!]");
        /*SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                UIManager.put("ToolTip.background", java.awt.Color.WHITE);
                UIManager.put("ToolTip.font", ph.edu.ceu.fis.framework.FrameWorkUtils.getSystemFont().deriveFont(14f));
                loginForm = new LoginForm();
            }
        });*/
        //UIManager.put("Separator.background", Color.BLACK);
        //UIManager.put("Separator.foreground", Color.BLACK);
        new SystemFrame(new Session("13-11448"));
        
        //ClientUtils.log(encryptPassword("jWBExBkVYar7QJnbjaj5rxyd", "fis_client"));
        //jWBExBkVYar7QJnbjaj5rxyd
        //ClientUtils.log(org.mindrot.jbcrypt.BCrypt.hashpw("123", BCrypt.gensalt(12)));
     
           
       
        
        
    }
    
    
    
    
  
    
   
    
}
