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

public class WorkEntry extends JPanel{

          
    private String workID;

    private FormLabel workPositionField,
                      institutionField,
                      addressField,
                      startDateField,
                      endDateField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public WorkEntry(HashMap<String, String> workInfo){
        super(new BorderLayout());
        this.workID = workInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        workPositionField = new FormLabel(workInfo.get("work_position"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        institutionField = new FormLabel(workInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        addressField = new FormLabel(workInfo.get("address"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        startDateField = new FormLabel(workInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        endDateField = new FormLabel(workInfo.get("end_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
       
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(workPositionField);
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
        mainContainerLayout.row().grid(new FormLabel("Position: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(workPositionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Address: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(addressField);
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(startDateField);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(endDateField);
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
