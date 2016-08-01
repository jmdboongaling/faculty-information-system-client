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
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScollBar extends JScrollBar{
    
    public CustomScollBar(){
        
        
        setUI(new BasicScrollBarUI(){   
            
            @Override
            protected JButton createDecreaseButton(int orientation){
                return createZeroButton();
            }

            @Override    
            protected JButton createIncreaseButton(int orientation){
                return createZeroButton();
            }

            private JButton createZeroButton(){
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
        
        setUnitIncrement(16);
        setValue(getMinimum());
    }
}
