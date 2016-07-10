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
package ph.edu.ceu.fis.gui.faculty;

import javax.swing.*;
import java.awt.*;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MenuButton;

public class RightPanel extends JPanel{

    private Session systemSession;
    private CardLayout panelSwitcher = new CardLayout();
        
    private JPanel switchPanel = new JPanel(panelSwitcher);
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/reload.png")), FrameWorkUtils.getPrimaryColor().brighter());
    public RightPanel(Session systemSession){
        super(new GridLayout(1, 1));
        this.systemSession = systemSession;
        
        setOpaque(false);
        
       
        add(mainPanel());
        
    }
    
    public JPanel mainPanel(){
        switchPanel.setOpaque(true);
        switchPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        switchPanel.add(new Profile(systemSession), "Profile");
        
        return switchPanel;
    }
    
   
    
}
