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
import ph.edu.ceu.fis.utils.Constants;

public class SystemFrame extends JFrame{
    
    public SystemFrame(){
        initComponents();
    }
    
    private void initComponents(){
        setTitle(Constants.getAppTitle());
        setExtendedState(super.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new JButton("Top Menu"), BorderLayout.NORTH);
        add(new JLabel("CONTENT"), BorderLayout.CENTER);
        add(new JLabel("Status Panel"), BorderLayout.SOUTH);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "WELCOME!");
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e){
                Main.main(new String[]{});
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

           
          
        });
        setVisible(true);

    }
}
