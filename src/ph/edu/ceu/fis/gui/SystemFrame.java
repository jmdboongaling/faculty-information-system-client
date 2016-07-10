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
import ph.edu.ceu.fis.data.DataUtils;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.gui.faculty.CenterPanel;
import ph.edu.ceu.fis.gui.faculty.LeftPanel;
import ph.edu.ceu.fis.gui.faculty.RightPanel;

public class SystemFrame extends JFrame{
    
    
    
    private GridBagLayout gridBagLayout = new GridBagLayout();
    
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private Session systemSession;
    
    public SystemFrame(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
    }
    
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(DataUtils.getAppTitle());
        setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        /*setLayout(gridBagLayout);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = .20;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets=new Insets(10, 10, 10, 5);
        
        add(leftContainer(), gridBagConstraints);
        
        gridBagConstraints.gridx++;
        gridBagConstraints.weightx = .30;
        gridBagConstraints.insets=new Insets(10, 5, 10, 5);
        
        add(centerContainer(), gridBagConstraints);
        
        gridBagConstraints.gridx++;
        gridBagConstraints.weightx = .50;
        gridBagConstraints.insets=new Insets(10, 5, 10, 5);
        
        add(rightContainer(), gridBagConstraints);*/
        JPanel mainContainer = new JPanel(new GridLayout(1, 3, 10, 10));
        mainContainer.setOpaque(false);
        mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(1, 1));
        mainContainer.add(leftContainer());
        mainContainer.add(centerContainer());
        mainContainer.add(rightContainer());
        add(mainContainer);
        /*
        addWindowListener(new WindowListener(){
           @Override
            public void windowClosing(WindowEvent e){
                doLogout();
            }
            @Override
            public void windowDeiconified(WindowEvent e){
                // Do nothing.
            }

            @Override
            public void windowActivated(WindowEvent e){
                // Do nothing.
            }

            @Override
            public void windowDeactivated(WindowEvent e){
                // Do nothing.
            }

            @Override
            public void windowOpened(WindowEvent e){
                // Do nothing.
            }

            @Override
            public void windowClosed(WindowEvent e){
                // Do nothing.
            }

            @Override
            public void windowIconified(WindowEvent e){
                // Do nothing.
            }
        })*/
        setResizable(false);
        setLocationRelativeTo(null);
        //setVisible(true);
        
    }
    
    private JPanel leftContainer(){
        JPanel leftContainer = new JPanel(new GridLayout(1, 1));
        JButton test = new JButton("Left");
        
        leftContainer.add(test);
        
        return new LeftPanel(systemSession);
    }
    
    private JPanel centerContainer(){
        JPanel centerContainer = new JPanel(new GridLayout(1, 1));
        JButton test = new JButton("Center");
        
        centerContainer.add(test);
        
        return new CenterPanel(systemSession);
    }
    
    private JPanel rightContainer(){
        JPanel rightContainer = new JPanel(new GridLayout(1, 1));
        JButton test = new JButton("Right");
        
        rightContainer.add(test);
        
        return new RightPanel(systemSession);
    }
    
    private void doLogout(){
        new LogoutPopup(this);
    }
    
}
