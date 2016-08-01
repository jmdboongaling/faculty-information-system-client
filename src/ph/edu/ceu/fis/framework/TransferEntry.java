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
import javax.swing.*;
import javax.swing.border.*;
import org.apache.commons.lang3.StringUtils;


public class TransferEntry extends JPanel{
    

    
    private FormLabel fileName;
                      
    
    private MenuButton cancelButton = new MenuButton("Cancel", 10f, new ImageIcon("images/cancel.png"), FrameWorkUtils.getPrimaryColor().brighter());
    
    public static final int UPLOAD_MODE = 1,
                            DOWNLOAD_MODE = 2;
    
    private JProgressBar transferProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    
    public TransferEntry(String fileName, int transferMode){
        super(new BorderLayout());
       
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        
        this.transferProgressBar.setStringPainted(true);
        this.transferProgressBar.setFont(FrameWorkUtils.getSystemFont().deriveFont(13f));
        this.transferProgressBar.setBackground(FrameWorkUtils.getPrimaryColor());
        this.transferProgressBar.setForeground(FrameWorkUtils.getSecondaryColor());
        this.transferProgressBar.setBorderPainted(false);
        switch(transferMode){
            case TransferEntry.UPLOAD_MODE:
                this.fileName = new FormLabel("Uploading " + StringUtils.abbreviate(fileName, 16), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
                break;
            case TransferEntry.DOWNLOAD_MODE:
                this.fileName = new FormLabel("Downloading " + StringUtils.abbreviate(fileName, 16), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
                break;
            default:
                
        }
        infoPanel.add(this.fileName);
        infoPanel.add(transferProgressBar);
        
      
        add(infoPanel, BorderLayout.CENTER);
        add(cancelButton, BorderLayout.EAST);
    }
    
    public JProgressBar getTransferProgressBar(){
        return transferProgressBar;
    }
    
    public JButton getCancelButton(){
        return cancelButton;
    }
    
    
}
