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

public class DateWidget extends JPanel{
    
    public DateWidget(){
        super(new GridLayout(1, 1));
        setOpaque(true);
        setBackground(new Color(133, 193, 245));//Color.WHITE);
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5));
        mainContainer.setOpaque(false);
        mainContainer.add(widgetIcon(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/dashboard.png"))), BorderLayout.WEST);
        mainContainer.add(informationPanel(), BorderLayout.CENTER);
        mainContainer.setBorder(new EmptyBorder(30, 10, 30, 10)); 
        add(mainContainer);
        //setBorder(new CustomShadowBorder());
    }
    
    public JPanel informationPanel(){
        JPanel informationPanel = new JPanel(new GridBagLayout());
        informationPanel.setOpaque(false);
        informationPanel.setBorder(new MatteBorder(0, 1, 0, 0, new Color(204, 204, 204)));
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        informationPanel.add(clockLabel(), gridbagConstraints);
        informationPanel.add(dateLabel(), gridbagConstraints);
        
        return informationPanel;
    }
    
    private FormLabel clockLabel(){
        FormLabel clockLabel = new FormLabel(" ", Color.WHITE, 20f, SwingConstants.LEFT, 20);
        clockLabel.setFont(FrameWorkUtils.getSystemFont().deriveFont(FrameWorkUtils.getSystemFont().getStyle() | Font.BOLD).deriveFont(50f));
        DateFormat timeFormat = new SimpleDateFormat("hh:mma");  
        ActionListener timerListener = new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e)  
            {  
                Date date = new Date();  
                clockLabel.setText(timeFormat.format(date));  
                clockLabel.revalidate();
            }  
        };  
        Timer timer = new Timer(1000, timerListener);  
        timer.setInitialDelay(0);  
        timer.start();  
        return clockLabel;
    }
    
    private FormLabel dateLabel(){
        FormLabel dateLabel = new FormLabel(" ", Color.WHITE, 25f, SwingConstants.LEFT, 20);
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        DateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy");  
        ActionListener timerListener = new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e)  
            {  
                Date date = new Date();  
                dateLabel.setText(timeFormat.format(date));  
            }  
        };  
        Timer timer = new Timer(1000, timerListener);  
        timer.setInitialDelay(0);  
        timer.start();  
        return dateLabel;
    }
    
    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setOpaque(false);
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        
        return buttonPanel;
    }
    
    private JLabel widgetIcon(ImageIcon widgetIcon){
        JLabel widgetLabel = new JLabel(widgetIcon);
        widgetLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        return widgetLabel;
    }
    
    

}
