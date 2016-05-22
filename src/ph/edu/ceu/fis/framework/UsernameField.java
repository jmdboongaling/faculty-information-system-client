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


public class UsernameField extends JPanel{
    
    private JTextField textField = new JTextField();
    private JLabel iconLabel = new JLabel();
    
    public UsernameField(String textFieldText){
        super(new BorderLayout(10, 10));
        setOpaque(false);
        setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
        iconLabel.setOpaque(false);
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/username_icon.png")));
        iconLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textField.setText(textFieldText);
        textField.setOpaque(false);
        textField.setForeground(Color.WHITE);
        textField.setFont(FrameWorkUtils.getSystemFont().deriveFont(16f));
        textField.setBorder(null);
        
        textField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent fe){
                setOpaque(true);
                setBackground(Color.WHITE);
                textField.setForeground(Color.BLACK);
                if(textField.getText().equals(textFieldText)){
                    textField.setText("");
                }
                iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/username_icon_focus.png")));
            }

            @Override
            public void focusLost(FocusEvent fe){
                setOpaque(false);
                setBackground(null);
                textField.setForeground(Color.WHITE);
                if(textField.getText().equals("")){
                    textField.setText(textFieldText);
                }
                iconLabel.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/username_icon.png")));
            }
        });
        
        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        
    }
    
    public String getText(){
        return textField.getText();
    }
}
