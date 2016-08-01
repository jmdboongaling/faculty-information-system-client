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

import java.awt.*;
import javax.swing.*;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MenuButton;

public class RightPanel extends JPanel{

    private Session systemSession;
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel switchPanel = new JPanel(panelSwitcher);
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon("images/reload.png"), FrameWorkUtils.getPrimaryColor().brighter());
    
    private Profile profilePanel;
    
    private Files filesPanel;
    
    private Classes classesPanel;
    
    private Directory directoryPanel;
    
    public RightPanel(Session systemSession){
        super(new GridLayout(1, 1));
        this.systemSession = systemSession;
        
        setOpaque(false);
        
        profilePanel = new Profile(systemSession);
        filesPanel = new Files(systemSession);
        classesPanel = new Classes(systemSession);
        directoryPanel = new Directory(systemSession);
                
        add(mainPanel());
        
    }
    
    public JPanel mainPanel(){
        switchPanel.setOpaque(true);
        switchPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        switchPanel.add(profilePanel, "Profile");
        switchPanel.add(filesPanel, "Files");
        switchPanel.add(classesPanel, "Classes");
        switchPanel.add(directoryPanel, "Directory");
        return switchPanel;
    }
    
    public void switchToProfile(){
        panelSwitcher.show(switchPanel, "Profile");
    }
    
    public void switchToFiles(){
        panelSwitcher.show(switchPanel, "Files");
    }
    
    public void switchToClasses(){
        panelSwitcher.show(switchPanel, "Classes");
    }
    
    public void switchToDirectory(){
        panelSwitcher.show(switchPanel, "Directory");
    }
    
    public Files getFilesPanel(){
        return filesPanel;
    }
    
   
    
}
