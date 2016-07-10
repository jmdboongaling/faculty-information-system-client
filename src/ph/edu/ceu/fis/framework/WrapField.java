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

        
public class WrapField extends JTextArea{
    public WrapField(String labelText, Color textColor, float fontSize){
        super(labelText.trim());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        //setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setEditable(false);
        setOpaque(false);
        setEnabled(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        
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
    }
    
    
}
