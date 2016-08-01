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

        
public class WrapField extends JTextArea{
    public WrapField(String labelText, Color textColor, float fontSize, boolean editable){
        super(labelText.trim());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter().brighter());
       
            
        setEditable(editable);
        setOpaque(editable);
        setEnabled(editable);
        
        
        setLineWrap(true);
        setWrapStyleWord(true);
        
        
    }
    
    
    
    
}
