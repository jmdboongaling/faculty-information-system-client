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

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import ph.edu.ceu.fis.utils.*;

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
            ClientUtils.log(new java.util.Date() + "- Loading System Font(Quicksand)...............[OK!]");
        }catch(Exception e){
            e.printStackTrace();
            ClientUtils.log(new java.util.Date() + "- Loading System Font(Quicksand)...............[FAILED!]");
            systemFont = new Font("Verdana", Font.PLAIN, 12);
            ClientUtils.log(new java.util.Date() + "- Loading System Font(Verdana)...............[OK!]");

        }
        return systemFont;
    }
    
    public static Color getPrimaryColor(){
        return new Color(74, 120, 156);
    }
    
}
