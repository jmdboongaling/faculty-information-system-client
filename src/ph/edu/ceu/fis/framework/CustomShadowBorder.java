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
import org.jdesktop.swingx.border.DropShadowBorder;

public class CustomShadowBorder extends DropShadowBorder{

    public CustomShadowBorder(){
        super(Color.black, 5);
        setShowTopShadow(true);
        setShowLeftShadow(true);
        setShowRightShadow(true);
        setShowBottomShadow(true);
    }
}
