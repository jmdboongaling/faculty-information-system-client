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
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FormField extends JTextField{
    public FormField(String labelText, Color textColor, float fontSize){
        super(labelText);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }
    
    public FormField(Color textColor, float fontSize){
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}
