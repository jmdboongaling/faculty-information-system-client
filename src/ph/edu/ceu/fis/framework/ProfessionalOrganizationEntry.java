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

public class ProfessionalOrganizationEntry extends JPanel{

          
    private String professionalOrganizationID;

    private FormLabel organizationField,
                      officerField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public ProfessionalOrganizationEntry(HashMap<String, String> professionalOrganizationInfo){
        super(new BorderLayout());
        this.professionalOrganizationID = professionalOrganizationInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        organizationField = new FormLabel(professionalOrganizationInfo.get("organization"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        if(Boolean.valueOf(professionalOrganizationInfo.get("officer"))){
            officerField = new FormLabel("Yes", FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        }else{
            officerField = new FormLabel("No", FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        }
        
        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(organizationField);
  
        
        
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
        mainContainerLayout.row().grid(new FormLabel("Officer: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(officerField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
