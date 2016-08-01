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

public class ResearchFellowshipAwardEntry extends JPanel{

          
    private String researchFellowshipAwardID;

    private FormLabel honorAwardField,
                      institutionField,
                      awardDateField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public ResearchFellowshipAwardEntry(HashMap<String, String> researchFellowshipAwardInfo){
        super(new BorderLayout());
        this.researchFellowshipAwardID = researchFellowshipAwardInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        honorAwardField = new FormLabel(researchFellowshipAwardInfo.get("honor_award"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        institutionField = new FormLabel(researchFellowshipAwardInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        awardDateField = new FormLabel(researchFellowshipAwardInfo.get("award_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(honorAwardField);
        infoPanel.add(institutionField);
  
        
        
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
        mainContainerLayout.row().grid(new FormLabel("Honor/Award: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(honorAwardField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Award Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(awardDateField);
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
