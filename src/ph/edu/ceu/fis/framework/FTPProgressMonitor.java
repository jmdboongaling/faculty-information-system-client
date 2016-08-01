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

import com.jcraft.jsch.SftpProgressMonitor;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class FTPProgressMonitor implements SftpProgressMonitor{
    private long totalFileSize = 0,
                 count = 0;
    
    private JProgressBar progressBar;
    
    public static final int UPLOAD_MODE = 1,
                            DOWNLOAD_MODE = 2;
    
    private int progressMode;
    
    private JButton cancelButton;
    public FTPProgressMonitor(long totalFileSize, TransferEntry transferEntry, int progressMode){
        this.totalFileSize = totalFileSize; 
        this.progressBar = transferEntry.getTransferProgressBar();
        this.cancelButton = transferEntry.getCancelButton();
        this.progressMode = progressMode;
    }

    public void init(int op, java.lang.String src, java.lang.String dest, long totalFileSize) {
        switch(this.progressMode){
            case FTPProgressMonitor.UPLOAD_MODE:
                progressBar.setString("Uploading");
                break;
            case FTPProgressMonitor.DOWNLOAD_MODE:
                progressBar.setString("Downloading");
                break;
            default:
                throw new IllegalArgumentException("INVALID MODE");
        }
    }

    public boolean count(long bytes){
        this.count += bytes; 
        Long progressPercentage = count * 100/totalFileSize;
        progressBar.setValue(progressPercentage.intValue());
        progressBar.setString(Integer.toString(progressPercentage.intValue()) + "%");
        System.out.println(progressPercentage.intValue() + "% of " + totalFileSize/1000000 + "MB " + "uploaded");
        return(true);
    }

    public void end(){
        cancelButton.setEnabled(false);
        progressBar.setString("Complete");
    }
}
