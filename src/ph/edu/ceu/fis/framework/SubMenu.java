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

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import org.jdesktop.swingx.JXCollapsiblePane;
public class SubMenu extends JPanel{
    //FormButton toggleButton = new FormButton
    private ArrayList<FormButton> subMenuButtons = new ArrayList<FormButton>();
    private JXCollapsiblePane subMenu = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);
    private FormButton menuButton;
    public SubMenu(String menuButtonText, ArrayList<FormButton> subMenuButtons){
        super(new BorderLayout());
        this.subMenuButtons = subMenuButtons;
        menuButton = new FormButton(menuButtonText, Color.WHITE, new Color(255, 128, 128), 20f);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JButton(subMenu.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION)).doClick();
            }
        });
        setOpaque(false);
        
        
        JPanel subMenuContainer = new JPanel(new GridLayout(this.subMenuButtons.size(), 1));
        subMenuContainer.setOpaque(false);
        for(int i = 0; i < this.subMenuButtons.size(); i++){
            subMenuContainer.add(this.subMenuButtons.get(i));
        }
        subMenu.add(subMenuContainer);
        add(menuButton, BorderLayout.NORTH);
        add(subMenu, BorderLayout.CENTER);
    }
    
    private JButton getMenuButton(){
        return menuButton;
    }
    private JButton getSubMenuButtonAt(int index){
        return subMenuButtons.get(index);
    }
}
