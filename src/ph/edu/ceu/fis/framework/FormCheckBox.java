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
import java.awt.event.*;
import javax.swing.*;


public class FormCheckBox extends JPanel{
    
    private JCheckBox checkBox = new JCheckBox();
    
    public FormCheckBox(String checkBoxText){
        super(new BorderLayout(5, 5));
        setOpaque(false);
        checkBox.setBorderPainted(false);
        checkBox.setIcon(new ImageIcon("uncheck.png"));
        checkBox.setSelectedIcon(new ImageIcon("check.png"));
        checkBox.setDisabledIcon(new ImageIcon("uncheck.png"));
        checkBox.setDisabledSelectedIcon(new ImageIcon("uncheck.png"));
        checkBox.setPressedIcon(new ImageIcon("check.png"));
        checkBox.setSelected(true);
        checkBox.setContentAreaFilled(false);
                
        add(checkBox, BorderLayout.WEST);
        add(new FormLabel(checkBoxText, FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 0), BorderLayout.CENTER);
        
    }
    
    public boolean isChecked(){
        return checkBox.isSelected();
    }
}
