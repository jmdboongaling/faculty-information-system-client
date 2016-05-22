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
package ph.edu.ceu.fis.utils; 

public class ClientUtils{
    
    public static void log(String message){
        System.out.println(message);
    }
    public static void log(Object message){
        System.out.println(message);
    }
    
    public static String parseBool(boolean message){
        if(message){
            return "[OK!]";
        }else{
            return "[FAILED!]";
        }
    }

}
