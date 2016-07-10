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
package ph.edu.ceu.fis.gui; 

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import ph.edu.ceu.fis.framework.*;

public class LogoutPopup extends JFrame{
    
    //private Session systemSession;
    JFrame systemFrame;
    
    Container originalContentPane;
    public LogoutPopup(JFrame parentFrame/*Session systemSession*/){
        this.systemFrame = parentFrame;
        this.originalContentPane = parentFrame.getContentPane();
         JLayer<Component> blurLayer = new JLayer<>(parentFrame.getContentPane(), new BlurLayerUI());
        parentFrame.setContentPane(blurLayer);  
        parentFrame.revalidate();
        
        parentFrame.setEnabled(false);
        //this.systemSession = systemSession;
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
    
    
    private JPanel mainContainer(){
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5));
        mainContainer.setOpaque(false);
        mainContainer.setBorder(new EmptyBorder(30, 0, 50, 0));
        mainContainer.add(new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/logout.png"))), BorderLayout.WEST);
        mainContainer.add(messageContainer(), BorderLayout.CENTER);
        mainContainer.add(buttonPanel(), BorderLayout.SOUTH);
        
        return mainContainer;
    }
    private JPanel messageContainer(){
        JPanel messageContainer = new JPanel(new GridLayout(2, 1, 5, 5));
        messageContainer.setOpaque(false);
        messageContainer.add(new FormLabel("You are about to log out.", FrameWorkUtils.getSecondaryColor(), 30f, JLabel.LEFT, 0));
        messageContainer.add(new FormLabel("<HTML><B>This will cause your current session to end. Do you want to continue?</B><HTML>", FrameWorkUtils.getSecondaryColor(), 20f, JLabel.LEFT, 0));
        
        return messageContainer;
    }
    
    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);
        FormButton yesButton = new FormButton("Yes", 20f),
                   noButton = new FormButton("No", 20f);
        
        yesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                systemFrame.dispose();
                dispose();
                String[] args = null;
                Main.main(args);
            }
        });
        
        noButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                systemFrame.setEnabled(true);
                systemFrame.setContentPane(originalContentPane);
                systemFrame.revalidate();
                dispose();
            }
        });
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
        return buttonPanel;
    }
}
