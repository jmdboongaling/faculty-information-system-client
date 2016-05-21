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
package ph.edu.ceu.fis.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import ph.edu.ceu.fis.framework.FormButton;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.PasswordField;
import ph.edu.ceu.fis.framework.UsernameField;
import ph.edu.ceu.fis.utils.Constants;


public class LoginForm extends JFrame{
    
    public LoginForm(){
       super(Constants.getAppTitle());
       initComponents();
       
    }
    
    
    
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 1, 0, 0));
        add(windowContainer());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        System.out.println("Login Window Load? OK!");
        
    }
    
    private JLabel windowContainer(){
        JLabel windowContainer = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/login_background.png")));
        windowContainer.setLayout(new BorderLayout());
        windowContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
        windowContainer.add(formContainer(), BorderLayout.EAST);
        
        
        
        return windowContainer;
    }
    
    private JLabel formContainer(){
        JLabel formContainer = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/login_container.png")));
        formContainer.setLayout(new GridLayout(1, 1));
        formContainer.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        UsernameField idField = new UsernameField("Username");
        PasswordField passwordField = new PasswordField("Password");
        
        FormButton loginButton = new FormButton("Login", Color.WHITE, 16f),
                   registerButton = new FormButton("Register", Color.WHITE, 16f),
                   forgotButton = new FormButton("Forgot Password?", Color.WHITE, 16f),
                   settingsButton = new FormButton("Settings", Color.WHITE, 16f);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Load(idField.getText().trim(), passwordField.getText());
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
                buttonPanel.add(registerButton);

        JPanel loginPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        loginPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        loginPanel.setOpaque(false);
        loginPanel.add(new FormLabel("Login", Color.WHITE, 22f, SwingConstants.CENTER, 120));
        loginPanel.add(idField);
        loginPanel.add(passwordField);
        loginPanel.add(buttonPanel);
        loginPanel.add(forgotButton);
        loginPanel.add(settingsButton);
        

        formContainer.add(loginPanel);
        return formContainer;
    }
    
    private JPanel statusPanel(){
        JPanel statusPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        
        
        return statusPanel;
    }
    
 
    
    
            
    
}
