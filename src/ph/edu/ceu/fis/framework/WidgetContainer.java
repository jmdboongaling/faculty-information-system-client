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
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import org.jdesktop.swingx.border.DropShadowBorder;

public class WidgetContainer extends JPanel{
    public static int HORIZONTAL = 0,
                      VERTICAL = 1;
    public WidgetContainer(JComponent[] widgetList, int orientation){
        if(orientation == WidgetContainer.HORIZONTAL){
            setLayout(new GridLayout(1, widgetList.length, 5, 5));
        }else{
            setLayout(new GridLayout(widgetList.length, 1, 5, 5));
        }
        setBorder(new EmptyBorder(10, 20, 0, 20));
        setOpaque(false);
        for(int i = 0; i < widgetList.length; i++){
            
            add(widgetList[i]);
        }
        
    }
    
}
