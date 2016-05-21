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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.SwingUtilities;
import org.codehaus.jettison.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class Main{
    
    public static LoginForm loginForm;
    public static void main(String[]args){
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                loginForm = new LoginForm();
            }
        });
        //System.out.println(encryptPassword("jWBExBkVYar7QJnbjaj5rxyd", "fis_client"));
        //jWBExBkVYar7QJnbjaj5rxyd
        //System.out.println(org.mindrot.jbcrypt.BCrypt.hashpw("123", BCrypt.gensalt(12)));
     
           
       
        
        
    }
    
    
    private static void x(){
        
        
    }
    
    
  
    
   
    
}
