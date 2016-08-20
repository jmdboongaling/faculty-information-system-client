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
 ** @Comments: Bugs - Indicator icon doesn't change for web server button and ftp server button unless hovered.
 ** 
 ** 
 **/
package ph.edu.ceu.fis.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import ph.edu.ceu.fis.data.DataUtils;
import ph.edu.ceu.fis.data.Flags;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.utils.*;


public class LoginForm extends JFrame{
    
    // Declaration of Session object which holds all information of current user.
    public Session systemSession;
    
    public static SystemFrame systemFrame;
    
    // Flags constructor tests connection to Web Server and FTP Server.
    private Flags systemStartupFlags = new Flags(); 
    
    // Declaration and initialization of custom Username field with placeholder of "Username".
    private UsernameField idField = new UsernameField("Username"); 
    
    // Declaration and initialization of custom Password field with placeholder of "Password".
    private PasswordField passwordField = new PasswordField("Password");
    
    private FormButton loginButton, // Declaration of custom JButton for login button.
                       registerButton, // Declaration of custom JButton for registration button.
                       forgotButton; // Declaration of custom JButton for forgot password button.
                      
    
    private FormLabel indicatorLabel; // Declaration of custom JLabel for indicator text for loader.
    
    // Layout for switching in between panels in window.
    private CardLayout panelSwitcher = new CardLayout();
    
    // Main container for window.
    private JPanel mainContainer = new JPanel(panelSwitcher);
    
    /* LoginForm.class constructor the calls superclass(JFrame) constructor for setting title of window.
     * Constructor also calls initComponents function.
     */
    public LoginForm(){
       super(DataUtils.getAppTitle()); // Superclass constructor to set title of window.
       initComponents(); // Call initComponents function.
       
    }
    
    
    /* initComponents function sets window properties such as size, layout, etc.
     * Adds all pages to be switched in the main container of the window.
     */
    private void initComponents(){
        
        // Setting default close operation: Program will exit when window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting background color of window.
        getContentPane().setBackground(FrameWorkUtils.getSecondaryColor());
        // Setting layout of window(GridLayout).
        setLayout(new GridLayout(1, 1, 0, 0));
        // Setting window icon.
        //setIconImage(new ImageIcon("images/link.eps")).getImage());
        setIconImages(new FrameWorkUtils().getAppIcons());
        mainContainer.setOpaque(true); // Setting mainContainer as opaque.
        mainContainer.setBackground(FrameWorkUtils.getSecondaryColor());// Setting background color of mainContainer.
        mainContainer.add(windowContainer(), "Main");// Adding login page and referenced as "Main".
        mainContainer.add(preLoader(), "Load");// Adding preloader page and refferenced as "Load".
        setContentPane(mainContainer);// Setting mainContainer as content pane of window.
        setResizable(false);// Setting window as not resizable.
        pack();// Packing window into minimum size.
        setLocationRelativeTo(null);// Centering window on startup.
        setVisible(true);// Displaying window.
        
        // Logging window as displayed
        ClientUtils.log(new java.util.Date() + " -  Login Window Start...............[OK!]");
        
        
        
    }
    
    /* windowContainer function returns JLabel for background image.
     * This function also adds the container for the login form.
     */
    private JLabel windowContainer(){
        
        // Declaring and initializing container with background.
        JLabel windowContainer = new JLabel(new ImageIcon("images/login_background.png"));
        // Setting layout of windowContainer to BorderLayout.
        windowContainer.setLayout(new BorderLayout()); 
        // Removing insets.
        windowContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
        //Adding login form to be placed on right side of container.
        windowContainer.add(formContainer(), BorderLayout.EAST);
        //windowContainer.add(new SteelCheckBox(), BorderLayout.WEST);
        
        
        
        // Returning the container.
        return windowContainer;
    }
    
    private JPanel formContainer(){
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setOpaque(false);
        formContainer.setBorder(new EmptyBorder(20, 20, 20, 50));
       

        
        passwordField.getTextField().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                startSystem(idField.getText().trim(), passwordField.getText());
            }
        });
        
        loginButton = new FormButton("Login", 16f);
        registerButton = new FormButton("Register", 16f);
        forgotButton = new FormButton("Forgot Password?", 16f);
        
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                startSystem(idField.getText().trim(), passwordField.getText());
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

        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(true);
        loginPanel.setBorder(new EmptyBorder(50, 20, 50, 20));
        loginPanel.setBackground(FrameWorkUtils.getPrimaryColor());

        DesignGridLayout formLayout = new DesignGridLayout(loginPanel);
        formLayout.row().center().add(new FormLabel("Login", FrameWorkUtils.getSecondaryColor(), 22f, SwingConstants.CENTER, 120));
        formLayout.row().grid().add(idField);
        formLayout.row().grid().add(passwordField);
        formLayout.row().grid().add(buttonPanel);
        formLayout.row().grid().add(forgotButton);
        
        formContainer.add(loginPanel);
        
        return formContainer;
    }
    
   
    
    private JPanel preLoader(){
        JLabel preLoader = new JLabel(new ImageIcon("images/loading.gif"));
        preLoader.setBorder(new EmptyBorder(0, 150, 0, 150));
        indicatorLabel = new FormLabel("Loading", FrameWorkUtils.getPrimaryColor(), 25f);
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(true);
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(0, 0, 100, 0));
        container.add(preLoader, BorderLayout.CENTER);
        container.add(indicatorLabel, BorderLayout.SOUTH);
        return container;
    }
    
   
    
    
    
    private void startSystem(String userID, String userPassword){
        panelSwitcher.show(mainContainer, "Load");
        Thread startSystem = new Thread(new Runnable(){
            @Override
            public void run(){
                systemSession = new Session(userID);
                indicatorLabel.setText("Connecting to web server...");
                if(Flags.serverIsAvailable()){
                    ClientUtils.wait(1);
                    indicatorLabel.setText("Successfully connected to web server...");
                    ClientUtils.wait(1);
                    indicatorLabel.setText("Connecting to file server...");
                    if(Flags.ftpIsAvailable()){
                        ClientUtils.wait(1);
                        indicatorLabel.setText("Successfully connected to file server...");
                        ClientUtils.wait(1);
                        indicatorLabel.setText("Logging in...");
                        if(systemSession.loginAuthentication(userID, userPassword)){
                            ClientUtils.wait(1);
                            indicatorLabel.setText("Credentials authenticated...");
                            java.io.File f = new java.io.File("tmp//" + ClientUtils.sha512Hash(userID, "fis"));
                            f.mkdir();
                            ClientUtils.wait(1);
                            indicatorLabel.setText("Loading system...");
                            ClientUtils.wait(1);
                            systemFrame = new SystemFrame(systemSession);
                            panelSwitcher.show(mainContainer, "Main");
                            dispose();
                            systemFrame.setVisible(true);
                        }else{
                            ClientUtils.wait(1);
                            indicatorLabel.setText("Invalid Credentials...");
                            ClientUtils.wait(2);
                            panelSwitcher.show(mainContainer, "Main");  
                        }
                    }else{
                        ClientUtils.wait(1);
                        indicatorLabel.setText("Failed to connect to file server...");
                        ClientUtils.wait(2);
                        panelSwitcher.show(mainContainer, "Main");
                    }
                }else{
                    indicatorLabel.setText("Failed to connect to web server...");
                    ClientUtils.wait(2);
                    panelSwitcher.show(mainContainer, "Main");
                }
                
            }
        });
        
        startSystem.start();
    }
    
    
    
    
    
 
    
    
            
    
}
