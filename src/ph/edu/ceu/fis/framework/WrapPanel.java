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

public class WrapPanel extends JPanel{
    public WrapPanel(Component... components){
        super(new WrapLayout(FlowLayout.LEFT, 5, 5));
        setOpaque(false);
        for(int i = 0; i < components.length; i++){
            add(components[i]);
        }
    }
    public WrapPanel(){
        super(new WrapLayout(FlowLayout.LEFT, 5, 5));
        setOpaque(false);
        
    }
    
    public void setComponents(Component... components){
        for(int i = 0; i < components.length; i++){
            add(components[i]);
        }
    }
    


}
