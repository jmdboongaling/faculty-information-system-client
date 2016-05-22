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
    
    private ArrayList<FormButton> subMenuButtons = new ArrayList<FormButton>();
    private JXCollapsiblePane subMenu = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);
    private FormButton menuButton;
    
    public SubMenu(String menuButtonText, ArrayList<FormButton> subMenuButtons){
        super(new BorderLayout());
        this.subMenuButtons = subMenuButtons;
        subMenu.setCollapsed(true);
        menuButton = new FormButton(menuButtonText, new Color(244, 186, 112), new Color(255, 128, 128), 16f, 50);
        menuButton.setToggleIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/toggle.png")));
        menuButton.setForeground(Color.WHITE);
        menuButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(subMenu.isCollapsed()){
                    menuButton.setToggleIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/untoggle.png")));
                }else{
                    menuButton.setToggleIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/toggle.png")));
                }
                new JButton(subMenu.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION)).doClick();
            }
        });
        setOpaque(false);
        add(menuButton, BorderLayout.NORTH);
        JPanel subMenuContainer = new JPanel();
        subMenuContainer.setOpaque(true);
        subMenuContainer.setBackground(new Color(204, 204, 204).darker().darker());
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        /*DesignGridLayout subMenuLayout = new DesignGridLayout(subMenuContainer);
        subMenuLayout.margins(0);*/
        subMenuContainer.setLayout(new GridBagLayout());
        for(int i = 0; i < this.subMenuButtons.size(); i++){
            //subMenuLayout.row().grid().add(this.subMenuButtons.get(i));
            subMenuContainer.add(this.subMenuButtons.get(i), gridbagConstraints);
        }
        
         
        
        subMenu.add(subMenuContainer);
        add(subMenu, BorderLayout.CENTER);
    }
    
    public JButton getMenuButton(){
        return menuButton;
    }
    
    public ArrayList<FormButton> getSubMenuButtons(){
        return subMenuButtons;
    }
    public JButton getSubMenuButtonAt(int index){
        return subMenuButtons.get(index);
    }
}
