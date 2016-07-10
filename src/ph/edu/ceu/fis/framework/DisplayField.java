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

import javax.swing.*;
import java.awt.*;

public class DisplayField extends JTextField{
    
    public DisplayField(String labelText, Color textColor, float fontSize){
        super(labelText.trim());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(null);
        setEditable(false);
        setOpaque(false);
        super.setEnabled(false);
        
    }
    
    @Override
    public void setEnabled(boolean isEnabled){
        if(isEnabled){
            super.setEnabled(true);
            setEditable(true);
            setOpaque(true);
            setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        }else{
            super.setEnabled(false);
            setEditable(false);
            setOpaque(false);
            setBackground(null);
        }
        revalidate();
    }
}
