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

public class FacultyMode extends JPanel{
    
    private Session systemSession;
    
    

   
    public static RightPanel rightPanel;
    public FacultyMode(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
    }
    
    private void initComponents(){
        setLayout(new GridLayout(1, 3, 10, 10));
        setOpaque(false);
        setOpaque(false);
        
        LeftPanel leftPanel = new LeftPanel(systemSession);
        CenterPanel centerPanel = new CenterPanel(systemSession);
        rightPanel = new RightPanel(systemSession);
        
        add(leftPanel);
        add(centerPanel);
        add(rightPanel);
    }
   
}
