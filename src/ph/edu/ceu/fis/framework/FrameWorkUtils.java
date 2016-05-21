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
package ph.edu.ceu.fis.framework;

import java.awt.Font;
import java.io.InputStream;
import ph.edu.ceu.fis.utils.Constants;

public class FrameWorkUtils{
    
    
    /*
     * getSystemFont() function returns system font for use in GUI and Framework
     */
    public static Font getSystemFont(){
        Font systemFont = null;
        try{
            /* Loasing system font from ph.edu.ceu.fis.res.fonts package.
             * System font as of 05-19-2016 Glober Thin Free
             */
            InputStream is = Constants.class.getResourceAsStream("/ph/edu/ceu/fis/res/fonts/system_font.ttf");
            systemFont = Font.createFont(Font.TRUETYPE_FONT, is);
            System.out.println("Successfully loaded system font...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to load system font...\nNow using Verdana as system font.");
            systemFont = new Font("Verdana", Font.PLAIN, 12);
        }
        return systemFont;
    }
    
}
