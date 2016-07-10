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
package ph.edu.ceu.fis.framework; 

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import ph.edu.ceu.fis.utils.ClientUtils;


public class ChildEntry extends JPanel{
    
    public ChildEntry(String childID, String childName, String childGender, int birthOrder){
        super(new GridLayout(1, 2));
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(new WrapField(childName, FrameWorkUtils.getSecondaryColor(), 13f));
        infoPanel.add(new FormLabel(childGender, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        
        add(infoPanel, BorderLayout.WEST);
        add(new FormLabel(ClientUtils.ordinal(birthOrder), FrameWorkUtils.getAccentColor(), 20f, SwingConstants.RIGHT, 0), BorderLayout.EAST);
    }
}
