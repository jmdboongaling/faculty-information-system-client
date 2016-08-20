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

import java.util.HashMap;

public class ErrorReport{
    
    public static String getErrorReport(String errorNumber){
        HashMap<String, String> errorList = new HashMap<String, String>();
        errorList.put("BI-00001", "Error Code: BI-00001\n - Invalid employee ID format.");
        errorList.put("BI-00002", "Error Code: BI-00002\n - Employee ID is already registered in the system");
        errorList.put("BI-00003", "Error Code: BI-00003\n - Account is no longer active.");
        errorList.put("BI-00004", "Error Code: BI-00004\n - Account is no longer active.");
        errorList.put("BI-00005", "Error Code: BI-00005\n - Last Name field should not be blank.");
        errorList.put("BI-00006", "Error Code: BI-00006\n - First Name field should not be blank.");
        errorList.put("BI-00007", "Error Code: BI-00007\n - Middle Name field should not be blank.");
        
        
        errorList.put("BI-00010", "Error Code: BI-00010\n - Enter the name of your spouse if civil status is not set to \"Single\"");
        
        errorList.put("EI-00001", "Error Code: EI-00001\n - Enter only numberic values on fields that require numbers. Ex. 1, 2, 3, 4.0, 5.5\n\nCharacters and special characters such as A, B, C, !, @, ?, etc are not allowed");
        errorList.put("EI-00002", "Error Code: EI-00002\n - Enter only numberic values on fields that require numbers. Ex. 1, 2, 3, 4.0, 5.5\n\nCharacters and special characters such as A, B, C, !, @, ?, etc are not allowed");
        errorList.put("EI-00003", "Error Code: EI-00003\n - If you will specify end date, please select both a month and year.");

        return errorList.get(errorNumber);
    }
}
