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

public class AdminAppointmentEntry extends JPanel{

          
    private String adminAppointmentID;

    private FormLabel adminAppointmentPositionField,
                      institutionField,
                      departmentField,
                      startDateField,
                      endDateField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public AdminAppointmentEntry(HashMap<String, String> adminAppointmentInfo){
        super(new BorderLayout());
        this.adminAppointmentID = adminAppointmentInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        adminAppointmentPositionField = new FormLabel(adminAppointmentInfo.get("employee_position"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        institutionField = new FormLabel(adminAppointmentInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        departmentField = new FormLabel(adminAppointmentInfo.get("department"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        startDateField = new FormLabel(adminAppointmentInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        endDateField = new FormLabel(adminAppointmentInfo.get("end_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
       
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(adminAppointmentPositionField);
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
        mainContainerLayout.row().grid(new FormLabel("Position: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(adminAppointmentPositionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Department: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(departmentField);
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(startDateField);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(endDateField);
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
