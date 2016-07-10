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

import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    
    public static long getHours(Date startDate, Date endDate){
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        long hours = secs / 3600;    
        secs = secs % 3600;
        long mins = secs / 60;
        secs = secs % 60;
        return hours;
    }
    
    public static long getMinutes(Date startDate, Date endDate){
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        long hours = secs / 3600;    
        secs = secs % 3600;
        long mins = secs / 60;
        secs = secs % 60;
        return mins;
    }
    
     public static String sha512Hash(String string, String salt){
        MessageDigest md;
        StringBuffer sb = new StringBuffer();
        try{
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(string.getBytes("UTF-8"));
            for(int i=0; i< bytes.length ;i++){
               sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString().substring(0, 32);
    }
     
    
    public static String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
        case 11:
        case 12:
        case 13:
            return i + "th";
        default:
            return i + sufixes[i % 10];

        }
    }
    public static void wait(int seconds){
        try{TimeUnit.SECONDS.sleep(seconds);}catch(InterruptedException ie){ie.printStackTrace();}
    }

}
