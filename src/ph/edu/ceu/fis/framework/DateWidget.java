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
        super(new BorderLayout());
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor());
        add(clockLabel(), BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);
        setPreferredSize(new Dimension(300, 150));
        
    }
    
    private FormLabel clockLabel(){
        FormLabel clockLabel = new FormLabel(" ", Color.WHITE, 80f);
        DateFormat timeFormat = new SimpleDateFormat("hh:mma");  
        ActionListener timerListener = new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e)  
            {  
                Date date = new Date();  
                clockLabel.setText(timeFormat.format(date));  
            }  
        };  
        Timer timer = new Timer(1000, timerListener);  
        timer.setInitialDelay(0);  
        timer.start();  
        clockLabel.setBorder(new MatteBorder(0, 0, 5, 0, Color.WHITE));
        return clockLabel;
    }
    
    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setOpaque(false);
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        buttonPanel.add(new FormButton("Calendar", Color.BLACK, 18f));
        
        return buttonPanel;
    }
    
    

}
