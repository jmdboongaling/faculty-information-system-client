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
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

public class FormComboBox extends JComboBox{
    
    public FormComboBox(Object[] items){
        super(items);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setForeground(FrameWorkUtils.getSecondaryColor());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(15f));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setUI((ComboBoxUI) MyComboBoxUI.createUI(this));
        
    }
    
    static class MyComboBoxUI extends BasicComboBoxUI {
    public static ComponentUI createUI(JComponent c) {
      return new MyComboBoxUI();
    }

    protected JButton createArrowButton() {
      MenuButton dropButton = new MenuButton("", 0f, new ImageIcon("images/drop.png"), FrameWorkUtils.getPrimaryColor().brighter());
      return dropButton;
    }
  }

}
