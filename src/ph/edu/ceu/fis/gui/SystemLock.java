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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;

public class SystemLock extends JFrame{
    
    private Session systemSession;
    private JFrame systemFrame;
    private PasswordField passwordField = new PasswordField("Password");
    private Container originalContentPane;
    private FormLabel indicatorLabel = new FormLabel("<HTML><B>System Locked!</B><HTML>", FrameWorkUtils.getSecondaryColor(), 20f, JLabel.LEFT, 0);
    public SystemLock(JFrame parentFrame, Session systemSession){
        this.systemFrame = parentFrame;
        this.originalContentPane = parentFrame.getContentPane();
         JLayer<Component> blurLayer = new JLayer<>(parentFrame.getContentPane(), new BlurLayerUI());
        parentFrame.setContentPane(blurLayer);  
        parentFrame.revalidate();
        
        parentFrame.setEnabled(false);
        this.systemSession = systemSession;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setSize(new Dimension(1000, 300));
        getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(mainContainer());
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    
    private JPanel mainContainer(){
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5));
        mainContainer.setOpaque(false);
        mainContainer.setBorder(new EmptyBorder(30, 0, 50, 0));
        mainContainer.add(new JLabel(new ImageIcon("images/lock64.png")), BorderLayout.WEST);
        mainContainer.add(messageContainer(), BorderLayout.CENTER);
        mainContainer.add(buttonPanel(), BorderLayout.SOUTH);
        
        return mainContainer;
    }
    private JPanel messageContainer(){
        JPanel messageContainer = new JPanel(new GridLayout(3, 1, 5, 5));
        messageContainer.setOpaque(false);
        messageContainer.add(new FormLabel("System Locked.", FrameWorkUtils.getSecondaryColor(), 30f, JLabel.LEFT, 0));
        messageContainer.add(indicatorLabel);
        messageContainer.add(passwordField);
        return messageContainer;
    }
    
    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);
        FormButton unlockButton = new FormButton("Unlock", 20f),
                   logoutButton = new FormButton("Logout", 20f);
        
        unlockButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                authenticatePassword();
            }
        });
        
        logoutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                doLogout();
            }
        });
        
        buttonPanel.add(unlockButton);
        buttonPanel.add(logoutButton);
        
        return buttonPanel;
    }
    
    private void authenticatePassword(){
        if(systemSession.loginAuthentication(systemSession.getUserID(), passwordField.getText())){
            systemFrame.setEnabled(true);
            systemFrame.setContentPane(originalContentPane);
            systemFrame.revalidate();
            dispose();
        }else{
            indicatorLabel.setText("<HTML><B>Invalid Credentials!</B><HTML>");
        }
    }
    
    private void doLogout(){
        
        systemFrame.dispose();
        dispose();
        systemSession.endSession();
        
    }
}
