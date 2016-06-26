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

public class FormField extends JPanel{
    
    FormLabel fieldLabel;
    JTextField textField = new JTextField();
    String oldText = null;
    public FormField(String labelText){
        super(new BorderLayout(5, 5));
        setOpaque(true);
        setBackground(Color.WHITE);
        fieldLabel = new FormLabel(labelText, Color.BLACK, 15f);
        textField.setOpaque(false);
        textField.setFont(FrameWorkUtils.getSystemFont().deriveFont(15f));
        textField.setBorder(new EmptyBorder(5, 0, 5, 0));
        add(fieldLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        setBorder(new CustomShadowBorder());
    }
    
    public FormField(String labelText, String fieldText, boolean editable){
        super(new BorderLayout(5, 5));
        oldText = fieldText;
        setOpaque(true);
        setBackground(Color.WHITE);
        fieldLabel = new FormLabel(labelText, Color.BLACK, 15f);
        textField.setText(fieldText);
        textField.setEditable(editable);
        textField.setOpaque(false);
        textField.setFont(FrameWorkUtils.getSystemFont().deriveFont(15f));
        textField.setBorder(new EmptyBorder(5, 0, 5, 0));
        add(fieldLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        setBorder(new CustomShadowBorder());
    }
    
    public String getText(){
        return textField.getText();
    }
    
    public boolean valueChanged(){
        return textField.getText().equals(oldText);
    }
    
   

}
