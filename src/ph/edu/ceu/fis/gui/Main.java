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


import ph.edu.ceu.fis.utils.ClientUtils;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.WrapField;


public class Main{
    
    
    public static LoginForm loginForm; // Declared as static to be later accessed on logout event.
    
    /* Main method contains UI Variables being setup to match GUI Design parameters.
     * String[]args is arguments passed during runtime.
     */
    public static void main(String[]args){
        
        // System started!
        ClientUtils.log(new java.util.Date() + " -  Application Started...............[OK!]");
        
        /*
         * Starting login window on AWT Dispatch Thread
         */
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                UIManager.put("ToolTip.background", FrameWorkUtils.getSecondaryColor()); // Setting tool tip background color.
                UIManager.put("ToolTip.font", FrameWorkUtils.getSystemFont().deriveFont(14f)); // Setting tool tip font.
                UIManager.put("ScrollBar.background", FrameWorkUtils.getPrimaryColor().brighter());
                UIManager.put("ScrollBar.foreground", FrameWorkUtils.getSecondaryColor());
                UIManager.put("ScrollBar.thumb", FrameWorkUtils.getSecondaryColor());
                UIManager.put("ScrollBar.thumbDarkShadow", FrameWorkUtils.getSecondaryColor());
                UIManager.put("ScrollBar.thumbHighlight", FrameWorkUtils.getSecondaryColor());
                UIManager.put("ScrollBar.thumbShadow", FrameWorkUtils.getSecondaryColor());
                UIManager.put("ScrollBar.track", FrameWorkUtils.getPrimaryColor().brighter());
                UIManager.put("ScrollBar.trackHighlight", FrameWorkUtils.getPrimaryColor().brighter());
                UIManager.put("ScrollBar.width", 10);
                UIManager.put("Separator.background", FrameWorkUtils.getSecondaryColor());
                UIManager.put("Separator.foreground", FrameWorkUtils.getSecondaryColor());

                loginForm = new LoginForm(); // Constructor of LoginForm class which opens Login Window.
                //ClientUtils.log(ClientUtils.sha512Hash("13-11448", "profile_picture"));
                //java.io.File f = new java.io.File("tmp//" + ClientUtils.sha512Hash("13-11448", "fis"));
                //f.mkdir();
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
    
    
   
   
    
  
    
   
    
}
