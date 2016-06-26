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
    
    private Color foregroundColor;
    public InformationWidget(String widgetTitle, ImageIcon widgetIcon, String widgetValue, Color backgroundColor, Color foregroundColor){
        super(new GridLayout(1, 1));
        setOpaque(true);
        setBackground(backgroundColor);
        this.foregroundColor = foregroundColor;
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5));
        mainContainer.setOpaque(false);
        mainContainer.add(widgetIcon(widgetIcon), BorderLayout.WEST);
        mainContainer.add(informationPanel(widgetTitle, widgetValue), BorderLayout.CENTER);
        mainContainer.setBorder(new EmptyBorder(30, 10, 30, 10));
        add(mainContainer);
        //setBorder(new CustomShadowBorder());
                
    }
    
    
    public JPanel informationPanel(String widgetTitle, String widgetValue){
        JPanel informationPanel = new JPanel(new GridBagLayout());
        informationPanel.setOpaque(false);
        informationPanel.setBorder(new MatteBorder(0, 1, 0, 0, new Color(204, 204, 204)));
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        
        titleLabel = new FormLabel(widgetTitle, foregroundColor, 20f, SwingConstants.LEFT, 20);
        titleLabel.setFont(FrameWorkUtils.getSystemFont().deriveFont(FrameWorkUtils.getSystemFont().getStyle() | Font.BOLD).deriveFont(20f));
        valueLabel = new FormLabel(widgetValue, foregroundColor, 16f, SwingConstants.LEFT, 20);
        informationPanel.add(titleLabel, gridbagConstraints);
        informationPanel.add(valueLabel, gridbagConstraints);
        
        return informationPanel;
    }
    private JLabel widgetIcon(ImageIcon widgetIcon){
        JLabel widgetLabel = new JLabel(widgetIcon);
        widgetLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        return widgetLabel;
    }
    
    
    
    
    
    

}
