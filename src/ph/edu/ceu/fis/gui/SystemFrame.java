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
    private JXCollapsiblePane sideBar;
    private FormButton menuButton;
    public SystemFrame(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
        
    }
    
    private void initComponents(){
        setTitle(Constants.getAppTitle());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(super.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        
        add(centerPanel(), BorderLayout.CENTER);
        add(sideBarMain(), BorderLayout.WEST);
        //add(new JButton("Test"), BorderLayout.EAST);
       
        setResizable(false);
        setVisible(true);

    }
    
    public JPanel centerPanel(){
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setOpaque(true);
        mainContainer.setBackground(Color.WHITE);
        mainContainer.add(topBar(), BorderLayout.NORTH);
        mainContainer.add(new JLabel("Test"), BorderLayout.CENTER);
        
        return mainContainer;
    }
    public JPanel topBar(){
        JPanel mainContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor());
        menuButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/close_focus.png")));
        menuButton.addActionListener(new ActionListener() {
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
        mainContainer.add(menuButton);
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
        mainContainer.setBackground(new Color(204, 204, 204).darker());
        
        FormButton infoButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/info.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/info_focus.png"))),
                   lockButton = new FormButton(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/lock.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/lock_focus.png")));
        
        JPanel profileContainer = new JPanel(new BorderLayout());
        profileContainer.setOpaque(true);
        profileContainer.setBackground(mainContainer.getBackground().darker().darker().darker());
        
        profileContainer.add(infoButton, BorderLayout.WEST);
        profileContainer.add(new PictureLabel("test.jpg", 200, 200, true), BorderLayout.CENTER);
        profileContainer.add(lockButton, BorderLayout.EAST);
        
        mainContainer.add(profileContainer, BorderLayout.NORTH);
        mainContainer.add(new SubMenu("Profile", new ArrayList<FormButton>(Arrays.asList(new FormButton[]{new FormButton("Basic Information", Color.WHITE, new Color(255, 128, 128), 20f), new FormButton("Basic Information", Color.WHITE, new Color(255, 128, 128), 20f), new FormButton("Basic Information", Color.WHITE, new Color(255, 128, 128), 20f)}))), BorderLayout.CENTER);
        return mainContainer;
    }
    
    

}
