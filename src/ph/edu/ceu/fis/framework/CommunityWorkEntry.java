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

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;

public class CommunityWorkEntry extends JPanel{

          
    private String communityWorkID;

    private FormLabel organizationField,
                      natureField,
                      communityWorkDateField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public CommunityWorkEntry(HashMap<String, String> communityWorkInfo){
        super(new BorderLayout());
        this.communityWorkID = communityWorkInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        organizationField = new FormLabel(communityWorkInfo.get("organization"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        natureField = new FormLabel(communityWorkInfo.get("nature"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        communityWorkDateField = new FormLabel(communityWorkInfo.get("consultancy_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(organizationField);
        infoPanel.add(natureField);
  
        
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(viewButton, viewButton.getX()-50, viewButton.getY());
            }
        });
        
        
        add(infoPanel, BorderLayout.CENTER);
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
        mainContainerLayout.row().grid(new FormLabel("Organization: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(organizationField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Nature: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(natureField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(communityWorkDateField);
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
