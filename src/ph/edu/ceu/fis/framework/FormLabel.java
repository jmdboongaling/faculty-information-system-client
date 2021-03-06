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
import javax.swing.border.*;

public class FormLabel extends JLabel{
    
    
    public FormLabel(String labelText, Color textColor, float fontSize){
        super(labelText);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public FormLabel(String labelText, Color textColor, float fontSize, int horizontalAlignment, int sideSpacing){
        super(labelText);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(textColor);
        setHorizontalAlignment(horizontalAlignment);
        setHorizontalTextPosition(horizontalAlignment);
        setBorder(new EmptyBorder(0, sideSpacing, 0, sideSpacing));
    }
    
    public FormLabel(ImageIcon labelIcon, int sideSpacing){
        super(labelIcon);
        setBorder(new EmptyBorder(0, sideSpacing, 0, sideSpacing));
    }
    
}
