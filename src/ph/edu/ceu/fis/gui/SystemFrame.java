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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.coobird.thumbnailator.Thumbnails;
import net.java.dev.designgridlayout.DesignGridLayout;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.utils.Constants;
import org.jdesktop.swingx.*;
import ph.edu.ceu.fis.framework.FormButton;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.PictureLabel;
import ph.edu.ceu.fis.framework.SubMenu;

public class SystemFrame extends JFrame{
    Session systemSession;
    private JXCollapsiblePane sideBar,
                              logoutPane;
    private FormButton menuButton;
    
    private CardLayout pageSwitcher = new CardLayout();
    private JPanel pagePanel = new JPanel(pageSwitcher);
    public SystemFrame(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
        
    }
    
    private void initComponents(){
        setTitle(Constants.getAppTitle());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setBackground(new Color(204, 204, 204));
        setLayout(new BorderLayout()); 
        add(centerPanel(), BorderLayout.CENTER);
        add(sideBarMain(), BorderLayout.WEST);
        setResizable(false);
        setExtendedState(super.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
    
    public JPanel centerPanel(){
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setOpaque(true);
        mainContainer.setBackground(new Color(240, 240, 240));
        mainContainer.add(topBar(), BorderLayout.NORTH);
        mainContainer.add(new Dashboard(), BorderLayout.CENTER);
        
        return mainContainer;
    }
    public JPanel topBar(){
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor());
        
        menuButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close_focus.png")));
        menuButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(sideBar.isCollapsed()){
                    menuButton.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close_focus.png")));
                }else{
                    menuButton.setIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/expand.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/expand_focus.png")));        
                }
                new JButton(sideBar.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION)).doClick();
            }
        });
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.add(menuButton);
        
        FormButton yesButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/yes.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/yes_focus.png"))),
                   noButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/no.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/no_focus.png"))),
                   bulletinButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/bulletin.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/bulletin_focus.png"))),
                   logoutButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/logout.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/logout_focus.png")));
        
        logoutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new JButton(logoutPane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION)).doClick();
            }
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setOpaque(true);
        logoutPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        logoutPanel.setBorder(new MatteBorder(0, 5, 0, 0, new Color(244, 186, 112)));
        logoutPanel.add(new FormLabel("Logout?", Color.WHITE, 15f, 0, 10));
        logoutPanel.add(yesButton);
        logoutPanel.add(noButton);
        
        logoutPane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);
        logoutPane.setCollapsed(true);
        logoutPane.add(logoutPanel);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(true);
        rightPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        rightPanel.add(bulletinButton);
        rightPanel.add(logoutButton);
        rightPanel.add(logoutPane);
        
        mainContainer.add(leftPanel, BorderLayout.WEST);
        mainContainer.add(rightPanel, BorderLayout.EAST);
        //mainContainer.add(new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/logo.png"))));
        //mainContainer.add(new FormLabel("Faculty Information System", Color.WHITE, 20f));
       
        return mainContainer;
    }
    

    
    public JXCollapsiblePane sideBarMain(){
        sideBar = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);
        sideBar.setCollapsed(false);
        JLabel menuTitle = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/user.png")));
        menuTitle.setBorder(new EmptyBorder(menuButton.getInsets().top + menuButton.getMargin().top + menuButton.getBorder().getBorderInsets(this).top, 150, menuButton.getInsets().bottom + menuButton.getMargin().bottom + menuButton.getBorder().getBorderInsets(this).bottom, 150));
        menuTitle.setOpaque(true);
        menuTitle.setBackground(new Color(255, 128, 128));//new Color(222, 157, 73));
        
        FormLabel menuContent = new FormLabel("SIDE BAR HERE!", Color.WHITE, 20f);
        menuContent.setBackground(new Color(204, 204, 204));
        menuContent.setOpaque(true);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        contentPane.add(menuTitle, BorderLayout.NORTH);
        contentPane.add(sideBarCenter(), BorderLayout.CENTER);
        sideBar.add(contentPane);
                
        return sideBar;
    }
    
    public JPanel sideBarCenter(){
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setOpaque(true);
        mainContainer.setBackground(new Color(204, 204, 204).darker().darker().darker());
        
        FormButton infoButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/info.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/info_focus.png"))),
                   lockButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/lock.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/lock_focus.png")));
        
        JPanel profileContainer = new JPanel(new BorderLayout());
        profileContainer.setOpaque(true);
        profileContainer.setBackground(mainContainer.getBackground().darker());
        JPanel namePanel = new JPanel(new GridBagLayout());
        namePanel.setOpaque(false);
        namePanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        namePanel.add(new FormLabel("Joshua Myron Deidre Dizon Boongaling", Color.WHITE, 18f), gridbagConstraints);
        namePanel.add(new FormLabel("Systems Administrator", Color.WHITE, 16f), gridbagConstraints);
        namePanel.add(new FormLabel("ICT Department", Color.WHITE, 13f), gridbagConstraints);
        profileContainer.add(infoButton, BorderLayout.WEST);
        profileContainer.add(new PictureLabel("test.jpg", 200, 200, true), BorderLayout.CENTER);
        profileContainer.add(lockButton, BorderLayout.EAST);
        profileContainer.add(namePanel, BorderLayout.SOUTH);
        
        JPanel menuContainer = new JPanel();
        menuContainer.setOpaque(false);
        SubMenu profileMenu = new SubMenu("Profile", 
                                          new ArrayList<FormButton>(
                                          Arrays.asList(new FormButton[]{  
                                          new FormButton("      Basic Information", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30), 
                                          new FormButton("      Files", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30), 
                                          new FormButton("      Manage Account", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30), 
                                          new FormButton("      Print Information Sheet", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30)})));
        ArrayList<FormButton> profileMenuButtons = profileMenu.getSubMenuButtons();
        profileMenuButtons.get(0).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        profileMenuButtons.get(1).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        profileMenuButtons.get(2).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        profileMenuButtons.get(3).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        SubMenu serviceRecordMenu = new SubMenu("Service Record", 
                                                new ArrayList<FormButton>(
                                                Arrays.asList(new FormButton[]{  
                                                new FormButton("      Load", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30), 
                                                new FormButton("      Browse Courses", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30), 
                                                new FormButton("      Print Service Record", new Color(244, 186, 112), new Color(255, 128, 128), 14f, 30)})));
        
        ArrayList<FormButton> serviceRecordMenuButtons = serviceRecordMenu.getSubMenuButtons();
        serviceRecordMenuButtons.get(0).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        serviceRecordMenuButtons.get(1).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        serviceRecordMenuButtons.get(2).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        
        DesignGridLayout menuContainerLayout = new DesignGridLayout(menuContainer);
        menuContainerLayout.margins(0);
        menuContainerLayout.row().grid().add(profileMenu);
        menuContainerLayout.row().grid().add(serviceRecordMenu);
        mainContainer.add(profileContainer, BorderLayout.NORTH);
        mainContainer.add(menuContainer, BorderLayout.CENTER);
        return mainContainer;
    }
    
    

}
