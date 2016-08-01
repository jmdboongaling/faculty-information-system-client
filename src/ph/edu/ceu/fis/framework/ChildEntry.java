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
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.java.dev.designgridlayout.DesignGridLayout;


public class ChildEntry extends JPanel{
    
    
    private WrapField childName;
    private FormLabel childGender,
                      birthOrder;
                      
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

    public ChildEntry(String childID, String childName, String childGender, String birthOrder){
        super(new BorderLayout());
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        
        this.childName = new WrapField(childName, FrameWorkUtils.getSecondaryColor(), 13f, false);
        this.childGender = new FormLabel(childGender, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        this.birthOrder = new FormLabel(birthOrder, FrameWorkUtils.getAccentColor(), 20f, SwingConstants.LEFT, 0);

        infoPanel.add(this.childName);
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(viewButton, viewButton.getX()-50, viewButton.getY());
            }
        });
      
        add(infoPanel, BorderLayout.WEST);
        add(viewButton, BorderLayout.EAST);
    }
    
    
    private void showDetails(Component componentInvoker, int x, int y){
        JPopupMenu detailsPopup = new JPopupMenu();
        detailsPopup.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        detailsPopup.setBorder(new LineBorder(FrameWorkUtils.getAccentColor()));
        JPanel mainContainer = new JPanel(){
            @Override
            public void remove(int index){
                //Do Nothing
            }
        };
        mainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainContainer.setOpaque(true);
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        DesignGridLayout mainContainerLayout = new DesignGridLayout(mainContainer);
        
        
        mainContainerLayout.row().bar().left(birthOrder);
        mainContainerLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(childName.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Gender: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(childGender);

        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
    }
}
