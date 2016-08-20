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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FrameWorkUtils{
    
    
  
    public static Font getSystemFont(){
        Font systemFont = null;
        try{
            /* Loasing system font from ph.edu.ceu.fis.res.fonts package.
             * System font as of 05-19-2016 Glober Thin Free
             */
            InputStream is = FrameWorkUtils.class.getResourceAsStream("/ph/edu/ceu/fis/res/fonts/OpenSans-Light.ttf");
            systemFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(13f);
        }catch(Exception e){
            e.printStackTrace();
            systemFont = new Font("Verdana", Font.PLAIN, 12);
        }
        return systemFont;
    }
    
    public static Color getPrimaryColor(){
        return new Color(43, 43, 43);
    }
    
    public static Color getSecondaryColor(){
        return new Color(169, 183, 198);
    }
    
    public static Color getAccentColor(){
        return new Color(255, 128, 128);
    }
    
  
    public List<Image> getAppIcons(){
        List<Image> iconsList = new ArrayList<Image>();
        iconsList.add(new ImageIcon("images/icon16.png").getImage());
        iconsList.add(new ImageIcon("images/icon24.png").getImage());
        iconsList.add(new ImageIcon("images/icon32.png").getImage());
        iconsList.add(new ImageIcon("images/icon64.png").getImage());
        iconsList.add(new ImageIcon("images/icon128.png").getImage());
        iconsList.add(new ImageIcon("images/icon256.png").getImage());
        iconsList.add(new ImageIcon("images/icon512.png").getImage());
        return iconsList;
    }
    
    
}
