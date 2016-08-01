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

public class ConsultancyEntry extends JPanel{

          
    private String consultancyID;

    private FormLabel institutionField,
                      natureField,
                      consultancyDateField;
    
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public ConsultancyEntry(HashMap<String, String> consultancyInfo){
        super(new BorderLayout());
        this.consultancyID = consultancyInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        institutionField = new FormLabel(consultancyInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        natureField = new FormLabel(consultancyInfo.get("nature"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        consultancyDateField = new FormLabel(consultancyInfo.get("consultancy_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(institutionField);
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
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Nature: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(natureField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Consultancy Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(consultancyDateField);
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
