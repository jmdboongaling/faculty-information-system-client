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
import javax.swing.*;

public class ViewRecord extends JFrame{
    
    private JFrame systemFrame;
   
    private Container originalContentPane;
    
    private JPanel mainContainer;
    
    public ViewRecord(JFrame parentFrame, JPanel recordPanel){
        this.systemFrame = parentFrame;
        this.originalContentPane = parentFrame.getContentPane();
        this.mainContainer = recordPanel;
        
        JLayer<Component> blurLayer = new JLayer<>(parentFrame.getContentPane(), new BlurLayerUI());
        parentFrame.setContentPane(blurLayer);  
        parentFrame.revalidate();
        
        parentFrame.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setSize(new Dimension(getWidth(), 300));
        getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(mainContainer());
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    
    public JPanel mainContainer(){
        JPanel recordContainer = new JPanel(new GridLayout(3, 1, 5, 5));
        recordContainer.setOpaque(false);
        recordContainer.add(new FormLabel("View Record", FrameWorkUtils.getSecondaryColor(), 30f, JLabel.LEFT, 0));
        recordContainer.add(mainContainer);
        recordContainer.add(buttonPanel());
        return recordContainer;
    
    }

    
    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);
        FormButton doneButton = new FormButton("Done", 20f);
       
        
        doneButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                systemFrame.setEnabled(true);
                systemFrame.setContentPane(originalContentPane);
                systemFrame.revalidate();
                dispose();
            }
        });
        
        buttonPanel.add(doneButton);
        
        
        return buttonPanel;
    }
    
   
}
