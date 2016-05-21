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
import java.util.concurrent.TimeUnit;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FormLabel;

public class Load extends JFrame{
    
    private FormLabel indicatorLabel = new FormLabel("Logging In", Color.BLACK, 25f);
    private String userID, 
                   userPassword;
    public Load(String userID, String userPassword){
        this.userID = userID;
        this.userPassword = userPassword;
        initComponents();
    }
    
    private void initComponents(){
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        add(preLoader());
        //add(indicatorLabel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        initSystem().start();
    }
    
    
    private Thread initSystem(){
        Thread startSystem = new Thread(new Runnable() {
            @Override
            public void run(){
                
                try{
                    TimeUnit.SECONDS.sleep(2);
                    if(Session.loginAuthentication(userID, userPassword)){
                        indicatorLabel.setText("Login Successful");
                        TimeUnit.SECONDS.sleep(2);
                        indicatorLabel.setText("Fetching Profile Information");
                        TimeUnit.SECONDS.sleep(2);
                        indicatorLabel.setText("Loading Profile");
                        TimeUnit.SECONDS.sleep(2);
                        indicatorLabel.setText("Loading User Preferences");
                        TimeUnit.SECONDS.sleep(4);
                        new SystemFrame();
                        dispose();
                    }else{
                        indicatorLabel.setText("Login Invalid");
                        TimeUnit.SECONDS.sleep(2);
                        dispose();
                        Main.loginForm.setVisible(true);
                    }
                    
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
               
            }
        });
        
        
        return startSystem;
    }
    
    private JPanel preLoader(){
        JLabel preLoader = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/loading.gif")));
        preLoader.setBorder(new EmptyBorder(0, 300, 0, 300));
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(new EmptyBorder(0, 0, 100, 0));
        container.add(preLoader, BorderLayout.CENTER);
        container.add(indicatorLabel, BorderLayout.SOUTH);
        
        return container;
    }

}
