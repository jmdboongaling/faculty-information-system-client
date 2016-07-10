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
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;

public class PanelPreloader extends JPanel{
    
    public PanelPreloader(String loadingText){
        super(new GridBagLayout());
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor());
        
        JPanel loaderContaier = new JPanel(new BorderLayout(5, 5));
        loaderContaier.setOpaque(false);
        loaderContaier.add(new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/panel_preloader.gif"))), BorderLayout.CENTER);
        loaderContaier.add(new FormLabel(loadingText, FrameWorkUtils.getSecondaryColor(), 15f), BorderLayout.SOUTH);
        add(loaderContaier);
    }
}
