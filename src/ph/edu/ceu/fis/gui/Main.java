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


import java.io.*;
import java.net.ServerSocket;
import javax.swing.*;
import javax.swing.border.LineBorder;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MessageDialog;
import ph.edu.ceu.fis.utils.ClientUtils;


public class Main{
    
    
    public static LoginForm loginForm; // Declared as static to be later accessed on logout event.
    public static ServerSocket SERVER_SOCKET;
    /* Main method contains UI Variables being setup to match GUI Design parameters.
     * String[]args is arguments passed during runtime.
     */
    public static void main(String[]args){
       
        /*try {
            SERVER_SOCKET = new ServerSocket(1334);
            loginForm = new LoginForm();
            
        } catch (IOException x) {
            MessageDialog.showExitDialog(loginForm, "Another Instance Running", "Another instance of the application is running on your local system.\n\n\t Only one instance of the application may run. To get rid of this message on next startup of the system, make sure no other instance of the application is running. If you can't find the source, try logging out of your system and logging in to shutdown all processes running on your machine.");
        }
        // System started!
        ClientUtils.log(new java.util.Date() + " -  Application Started...............[OK!]");
        /*
         * Starting login window on AWT Dispatch Thread
         */
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ToolTipManager.sharedInstance().setInitialDelay(0);
                UIManager.put("ComboBox.disabledBackground", FrameWorkUtils.getPrimaryColor().brighter());
                UIManager.put("ComboBox.disabledForeground", FrameWorkUtils.getSecondaryColor());
                UIManager.put("TextField.caretForeground", FrameWorkUtils.getAccentColor());
                UIManager.put("TextArea.caretForeground", FrameWorkUtils.getAccentColor());
                UIManager.put("ToolTip.background", FrameWorkUtils.getPrimaryColor()); // Setting tool tip background color.
                UIManager.put("ToolTip.foreground", FrameWorkUtils.getAccentColor()); // Setting tool tip background color.
                UIManager.put("ToolTip.border", new LineBorder(FrameWorkUtils.getAccentColor())); // Setting tool tip background color.
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
                UIManager.put("ProgressBar.selectionForeground", FrameWorkUtils.getPrimaryColor().brighter());
                UIManager.put("ProgressBar.selectionBackground", FrameWorkUtils.getPrimaryColor().brighter());
                new SystemFrame(new Session("13-11448")).setVisible(true);
                //loginForm = new LoginForm();
            }
        });
        

        
        
    }
    
    
   
    
    
    
   
    
}
