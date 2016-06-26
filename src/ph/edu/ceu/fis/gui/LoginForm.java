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
import ph.edu.ceu.fis.data.DataUtils;
import ph.edu.ceu.fis.data.Flags;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.utils.*;


public class LoginForm extends JFrame{
    private Flags systemStartupFlags = new Flags();
    private UsernameField idField = new UsernameField("Username");
    
    private PasswordField passwordField = new PasswordField("Password");
    
    private FormButton loginButton,
                       registerButton,
                       forgotButton,
                       wsButton,
                       ftpButton,
                       settingsButton;
    private FormLabel indicatorLabel;
    private CardLayout panelSwitcher = new CardLayout();
    private JPanel mainContainer = new JPanel(panelSwitcher);
    public LoginForm(){
       super(DataUtils.getAppTitle());
       initComponents();
       
    }
    
    
    
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 1, 0, 0));
        setIconImage(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/icon.png")).getImage());
        mainContainer.setOpaque(true);
        mainContainer.setBackground(Color.WHITE);
        mainContainer.add(windowContainer(), "Main");
        mainContainer.add(preLoader(), "Load");
        setContentPane(mainContainer);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        ClientUtils.log(new java.util.Date() + "- Login Window Start...............[OK!]");
        
        
        
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
        
        passwordField.getTextField().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                doLogin();
            }
        });
        
        loginButton = new FormButton("Login", Color.WHITE, 16f);
        registerButton = new FormButton("Register", Color.WHITE, 16f);
        forgotButton = new FormButton("Forgot Password?", Color.WHITE, 16f);
        
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                doLogin();
            }
        });
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //doLogin()
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
        loginPanel.add(statusPanel());
        

        formContainer.add(loginPanel);
        return formContainer;
    }
    
    private JPanel statusPanel(){
        JPanel statusPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        statusPanel.setOpaque(false);
        if(systemStartupFlags.serverIsAvailable()){
            wsButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_ok.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_ok_focus.png")), Color.WHITE);
        }else{
            wsButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_fail.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_fail_focus.png")), Color.WHITE);
        }
        
        if(systemStartupFlags.ftpIsConnected()){
            ftpButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_ok.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_ok_focus.png")), Color.WHITE);
        }else{
            ftpButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_fail.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_fail_focus.png")), Color.WHITE);
        }
        settingsButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/settings_icon.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/settings_icon_focus.png")), Color.WHITE);
        
        wsButton.setToolTipText("Attempting Connection Web Server: " + DataUtils.getWebServerHost()[0] + "At Port " + DataUtils.getWebServerHost()[1]);
        wsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(mainContainer, "Load");
                indicatorLabel.setText("Connecting to Web Server");
                connectToWS().start();
            }
        });
        
        ftpButton.setToolTipText("Attempting Connection FTP Server: " + DataUtils.getFTPServerHost()[0] + "At Port " + DataUtils.getFTPServerHost()[1]);
        ftpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(mainContainer, "Load");
                indicatorLabel.setText("Connecting to FTP Server");
                connectToFTP().start();
            }
        });
        statusPanel.add(wsButton);
        statusPanel.add(ftpButton);
        statusPanel.add(settingsButton);
        return statusPanel;
    }
    
    private JPanel preLoader(){
        JLabel preLoader = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/loading.gif")));
        preLoader.setBorder(new EmptyBorder(0, 150, 0, 150));
        indicatorLabel = new FormLabel("Loading", Color.BLACK, 25f);
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(new EmptyBorder(0, 0, 100, 0));
        container.add(preLoader, BorderLayout.CENTER);
        container.add(indicatorLabel, BorderLayout.SOUTH);
        return container;
    }
    
    private void doLogin(){
        panelSwitcher.show(mainContainer, "Load");
        initSystem(idField.getText().trim(), passwordField.getText()).start();
    }
    private Thread connectToWS(){
        Thread connectToWS = new Thread(new Runnable(){
            @Override
            public void run(){
                try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                systemStartupFlags.retryConnectToWS();
                wsButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_load.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_load_focus.png")));
                if(systemStartupFlags.serverIsAvailable()){
                    try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                    indicatorLabel.setText("Connection Successful");
                    wsButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_ok.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_ok_focus.png")));
                }else{
                    try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                    indicatorLabel.setText("Connection Failed");
                    wsButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_fail.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ws_fail_focus.png")));       
                }
                try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                panelSwitcher.show(mainContainer, "Main");
            }
        });
        
        return connectToWS;
    }
    
    private Thread connectToFTP(){
        Thread connectToFTP = new Thread(new Runnable(){
            @Override
            public void run(){
                try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                systemStartupFlags.retryConnectToFTP();
                ftpButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_load.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_load_focus.png")));
                if(systemStartupFlags.ftpIsConnected()){
                    try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                    indicatorLabel.setText("Connection Successful");
                    ftpButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_ok.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_ok_focus.png")));
                }else{
                    try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                    indicatorLabel.setText("Connection Successful");
                    ftpButton.setIcons(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_fail.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/ftp_fail_focus.png")));   
                }
                try{TimeUnit.SECONDS.sleep(2);}catch(InterruptedException ie){ie.printStackTrace();}
                panelSwitcher.show(mainContainer, "Main");
            }
        });
        return connectToFTP;
    }
    private Thread initSystem(String userID, String userPassword){
        
        Thread startSystem = new Thread(new Runnable(){
            @Override
            public void run(){
                Session systemSession = new Session(userID);
                try{
                    indicatorLabel.setText("Logging In");
                    ClientUtils.log(new java.util.Date()+ "- Attempting Login For User " + userID + "...............[PENDING!]");
                    TimeUnit.SECONDS.sleep(1);
                    if(systemStartupFlags.serverIsAvailable()){
                        if(systemSession.loginAuthentication(userID, userPassword)){  
                            indicatorLabel.setText("Login Successful");
                            TimeUnit.SECONDS.sleep(1);
                            indicatorLabel.setText("Fetching Profile Information");
                            //systemSession.invokeWsProfileInformation();
                            TimeUnit.SECONDS.sleep(1);
                            indicatorLabel.setText("Loading Profile");
                            TimeUnit.SECONDS.sleep(1);
                            indicatorLabel.setText("Loading User Preferences");
                            TimeUnit.SECONDS.sleep(2);
                            panelSwitcher.show(mainContainer, "Main");
                            //dispose();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run(){
                                    //new SystemFrame(systemSession);
                                }
                            });
                        }else{
                            indicatorLabel.setText("Login Invalid");
                            TimeUnit.SECONDS.sleep(2);
                            panelSwitcher.show(mainContainer, "Main");
                        }
                    }else{
                        indicatorLabel.setText("Unable To Connect To Web Server");
                        TimeUnit.SECONDS.sleep(2);
                        panelSwitcher.show(mainContainer, "Main");
                    }
                        
                    
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
               
            }
        });
        
        
        return startSystem;
    }
    
    
    
    
    
 
    
    
            
    
}
