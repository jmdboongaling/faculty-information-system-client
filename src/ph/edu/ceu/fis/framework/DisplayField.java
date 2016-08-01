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

public class DisplayField extends JTextField{
    
    public DisplayField(String labelText, Color textColor, float fontSize, boolean editable){
        super(labelText.trim());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter().brighter());
        setBorder(null);
        setEditable(editable);
        setOpaque(editable);
        super.setEnabled(editable);
        
    }
    
    
    
}
