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
import java.awt.event.*;

public class CustomScrollPane extends JScrollPane{
    public CustomScrollPane(Component viewPort){
        super(viewPort, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBar(new CustomScollBar());
        getViewport().setBackground(FrameWorkUtils.getPrimaryColor());
        setViewportBorder(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setOpaque(false);
    }
}
