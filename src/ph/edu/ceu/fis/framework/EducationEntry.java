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

public class EducationEntry extends JPanel{

          
    private String educationID;

    private FormLabel degreeField,
                      institutionField,
                      honorsField,
                      startDateField,
                      endDateField,
                      unitsEarnedField,
                      scholarshipField,
                      stateUnitsField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public EducationEntry(HashMap<String, String> educationInfo){
        super(new BorderLayout());
        this.educationID = educationInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        degreeField = new FormLabel(educationInfo.get("degree"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        institutionField = new FormLabel(educationInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        honorsField = new FormLabel(educationInfo.get("honors"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        startDateField = new FormLabel(educationInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        endDateField = new FormLabel(educationInfo.get("end_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        unitsEarnedField = new FormLabel(educationInfo.get("units_earned"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        scholarshipField = new FormLabel(educationInfo.get("scholarship"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        stateUnitsField = new FormLabel(educationInfo.get("state_units"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(degreeField);
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
        mainContainerLayout.row().grid(new FormLabel("Degree: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(degreeField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Honors: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(honorsField);
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(startDateField);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(endDateField);
        mainContainerLayout.row().grid(new FormLabel("Units Earned: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(unitsEarnedField);  
        mainContainerLayout.row().grid(new FormLabel("Scholarship: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(scholarshipField);  
        mainContainerLayout.row().grid(new FormLabel("Stated ACRED Units: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(stateUnitsField);  
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
