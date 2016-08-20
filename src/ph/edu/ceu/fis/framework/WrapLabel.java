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

import java.awt.*;
import javax.swing.*;

        
public class WrapLabel extends JTextArea{
    public WrapLabel(String labelText, Color textColor, float fontSize){
        super(labelText.trim());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
       
            
        setEditable(false);
        setOpaque(false);
        setEnabled(false);
        setDisabledTextColor(textColor);
        
        setLineWrap(true);
        setWrapStyleWord(true);
        
        
    }
    
    
    
    
}
