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
import ph.edu.ceu.fis.framework.DateWidget;
import ph.edu.ceu.fis.framework.InformationWidget;

public class Dashboard extends JPanel{
    
    public Dashboard(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);
        //add(new DateWidget());
        add(new InformationWidget("Last Edit", new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/edit_widget.png")), "06/21/2016"));
        add(new InformationWidget("Last Login", new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/login_widget.png")), "5 Hours Ago"));
       
    }
}
