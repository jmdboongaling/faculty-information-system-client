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

public class ResearchEntry extends JPanel{

          
    private String researchID;

    private FormLabel titleField,
                      startDateField,
                      endDateField,
                      fundingAgencyField,
                      researchRoleField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public ResearchEntry(HashMap<String, String> researchInfo){
        super(new BorderLayout());
        this.researchID = researchInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        titleField = new FormLabel(researchInfo.get("title"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        startDateField = new FormLabel(researchInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        endDateField = new FormLabel(researchInfo.get("end_Date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        fundingAgencyField = new FormLabel(researchInfo.get("funding_agency"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        researchRoleField = new FormLabel(researchInfo.get("research_role"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(titleField);
        infoPanel.add(researchRoleField);
  
        
        
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
        mainContainerLayout.row().grid(new FormLabel("Title: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(titleField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Role: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(researchRoleField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(startDateField);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(endDateField);
        mainContainerLayout.row().grid(new FormLabel("Funding Agency: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(fundingAgencyField);

        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
