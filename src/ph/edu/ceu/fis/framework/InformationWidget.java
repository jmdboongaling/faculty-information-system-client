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

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Date;

public class InformationWidget extends JPanel{
    private FormLabel titleLabel,
                      valueLabel;
    public InformationWidget(String widgetTitle, ImageIcon widgetIcon, String widgetValue){
        super(new BorderLayout());
        setOpaque(true);
        setBackground(Color.WHITE);
        
        
        add(widgetIcon(widgetIcon), BorderLayout.WEST);
        add(informationPanel(widgetTitle, widgetValue), BorderLayout.CENTER);
        setBorder(new EmptyBorder(20, 20, 20, 20));
    }
    
    
    public JPanel informationPanel(String widgetTitle, String widgetValue){
        JPanel informationPanel = new JPanel(new GridBagLayout());
        informationPanel.setOpaque(false);
        informationPanel.setBorder(new MatteBorder(0, 1, 0, 0, new Color(204, 204, 204)));
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        
        titleLabel = new FormLabel(widgetTitle, Color.BLACK, 20f, SwingConstants.LEFT, 20);
        titleLabel.setFont(FrameWorkUtils.getSystemFont().deriveFont(FrameWorkUtils.getSystemFont().getStyle() | Font.BOLD).deriveFont(40f));
        valueLabel = new FormLabel(widgetValue, Color.BLACK, 20f, SwingConstants.CENTER, 20);
        informationPanel.add(titleLabel, gridbagConstraints);
        informationPanel.add(valueLabel, gridbagConstraints);
        
        return informationPanel;
    }
    public JLabel widgetIcon(ImageIcon widgetIcon){
        JLabel widgetLabel = new JLabel(widgetIcon);
        widgetLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        return widgetLabel;
    }
    
    
    
    
    
    

}
