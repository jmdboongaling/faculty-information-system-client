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

public class FormHint extends JLabel{
    public FormHint(String labelText, Color textColor, float fontSize){
        super(labelText);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize).deriveFont(Font.ITALIC));
        setForeground(textColor);
        setHorizontalAlignment(SwingConstants.LEFT);
    }
}
