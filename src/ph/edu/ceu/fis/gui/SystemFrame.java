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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import ph.edu.ceu.fis.data.DataUtils;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.gui.faculty.FacultyMode;

public class SystemFrame extends JFrame{
    
    
    
    private GridBagLayout gridBagLayout = new GridBagLayout();
    
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private Session systemSession;
    
    private JPanel mainContainer = new JPanel(new BorderLayout());
    
    private static CardLayout modeSwitcher = new CardLayout();
    
    public SystemFrame(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
    }
    
    private void initComponents(){
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(DataUtils.getAppTitle());
        setIconImages(new FrameWorkUtils().getAppIcons());
        setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 1));
        
        
        mainContainer.setOpaque(false);
        mainContainer.setLayout(new BorderLayout(10, 10));
        mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        
        mainContainer.add(titlePanel(), BorderLayout.NORTH);
        mainContainer.add(panelContainer(), BorderLayout.CENTER);
        add(mainContainer);
        
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
        });
        //setResizable(false);
        setLocationRelativeTo(null);
        
    }
    
    private JPanel titlePanel(){
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor());
        titlePanel.add(new JLabel(new ImageIcon("images/icon64.png")));
        titlePanel.add(new FormLabel("Faculty Information System", FrameWorkUtils.getSecondaryColor(), 25f, SwingConstants.LEFT, 0));
        return titlePanel;
    }
    
    private JPanel panelContainer(){
        JPanel panelContainer = new JPanel(modeSwitcher);
        panelContainer.setOpaque(false);
        
        FacultyMode facultyMode = new FacultyMode(systemSession);
        panelContainer.add(facultyMode, "Faculty");

        return panelContainer;
    }
    
    
    private void doLogout(){  
        new LogoutPopup(this, systemSession);
    }
  
    
}
