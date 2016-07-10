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


public class PasswordField extends JPanel{
    
    private JPasswordField textField = new JPasswordField();
    private JLabel iconLabel = new JLabel();
    
    public PasswordField(String textFieldText){
        super(new BorderLayout(10, 10));
        setOpaque(false);
        setBorder(new MatteBorder(0, 0, 1, 0, FrameWorkUtils.getSecondaryColor()));
        iconLabel.setOpaque(false);
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/password_icon.png")));
        iconLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textField.setText(textFieldText);
        textField.setOpaque(false);
        textField.setForeground(FrameWorkUtils.getSecondaryColor());
        textField.setFont(FrameWorkUtils.getSystemFont().deriveFont(16f));
        textField.setBorder(null);
        textField.setEchoChar((char) 0);

        
        
        textField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent fe){
                setOpaque(true);
                setBackground(FrameWorkUtils.getSecondaryColor());
                textField.setForeground(FrameWorkUtils.getPrimaryColor());
                textField.setEchoChar('•');
                if(textField.getText().equals(textFieldText)){
                    textField.setText("");
                }
                iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/password_icon_focus.png")));
            }

            @Override
            public void focusLost(FocusEvent fe){
                setOpaque(false);
                setBackground(null);
                textField.setForeground(FrameWorkUtils.getSecondaryColor());
                if(textField.getText().equals("")){
                    textField.setText(textFieldText);
                    textField.setEchoChar((char) 0);
                }
                iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/password_icon.png")));
            }
        });
        
        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        
    }
    
    public String getText(){
        return textField.getText();
    }
    
    public JTextField getTextField(){
        return textField;
    }
}
