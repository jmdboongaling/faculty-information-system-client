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
import ph.edu.ceu.fis.utils.ClientUtils;

public class LicensureEntry extends JPanel{

          
    private String licensureID;

    private FormLabel professionField,
                      yearTakenField,
                      licenseNumberField,
                      prcExpDateField,
                      gradeField,
                      rankField;
   
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public LicensureEntry(HashMap<String, String> licensureInfo){
        super(new BorderLayout());
        this.licensureID = licensureInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
   
        professionField = new FormLabel(licensureInfo.get("profession"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        yearTakenField = new FormLabel(licensureInfo.get("year_taken"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        licenseNumberField = new FormLabel(licensureInfo.get("license_number"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        prcExpDateField = new FormLabel(licensureInfo.get("prc_exp_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        gradeField = new FormLabel(licensureInfo.get("grade"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        rankField = new FormLabel(ClientUtils.ordinal(Integer.parseInt(licensureInfo.get("rank"))), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(professionField);
        infoPanel.add(yearTakenField);
  
        
        
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
        mainContainerLayout.row().grid(new FormLabel("Profession: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(professionField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Year Taken: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(yearTakenField.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("License No.: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(licenseNumberField);
        mainContainerLayout.row().grid(new FormLabel("PRC Exp. Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(prcExpDateField);
        mainContainerLayout.row().grid(new FormLabel("Grade: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(gradeField);
        mainContainerLayout.row().grid(new FormLabel("Rank: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(rankField);

        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }

}
