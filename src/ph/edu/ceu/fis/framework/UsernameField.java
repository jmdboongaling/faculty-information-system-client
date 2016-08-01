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
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;


public class UsernameField extends JPanel{
    
    private JFormattedTextField textField = new JFormattedTextField();
    private JLabel iconLabel = new JLabel();
    
    public UsernameField(String textFieldText){
        super(new BorderLayout(10, 10));
        setOpaque(false);
        setBorder(new MatteBorder(0, 0, 1, 0, FrameWorkUtils.getSecondaryColor()));
        iconLabel.setOpaque(false);
        iconLabel.setIcon(new ImageIcon("images/username_icon.png"));
        iconLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textField.setColumns(20);
        textField.setText(textFieldText);
        textField.setOpaque(false);
        textField.setForeground(FrameWorkUtils.getSecondaryColor());
        textField.setFont(FrameWorkUtils.getSystemFont().deriveFont(16f));
        textField.setBorder(null);
        try {
            MaskFormatter dateMask = new MaskFormatter("##-#####");
            dateMask.install(textField);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        textField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent fe){
                setOpaque(true);
                setBackground(FrameWorkUtils.getSecondaryColor());
                textField.setForeground(FrameWorkUtils.getPrimaryColor());
                if(textField.getText().equals(textFieldText)){
                    textField.setText("");
                }
                iconLabel.setIcon(new ImageIcon("images/username_icon_focus.png"));
            }

            @Override
            public void focusLost(FocusEvent fe){
                setOpaque(false);
                setBackground(null);
                textField.setForeground(FrameWorkUtils.getSecondaryColor());
                if(textField.getText().equals("")){
                    textField.setText(textFieldText);
                }
                iconLabel.setIcon(new ImageIcon("images/username_icon.png"));
            }
        });
        
        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        
    }
    
    public String getText(){
        return textField.getText();
    }
}
