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
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.lang3.StringUtils;
import ph.edu.ceu.fis.data.UserFiles;
import ph.edu.ceu.fis.gui.faculty.FacultyMode;


public class FileEntry extends JPanel{
    
    
    private String userID,
                   fileID,
                   fileRelPath,
                   fileName,
                   fileType,
                   fileUploadDate;
                   
    private Long fileSize;
    

                      
    
    private MenuButton deleteButton = new MenuButton("Delete", 10f, new ImageIcon("images/trash.png"), FrameWorkUtils.getPrimaryColor().brighter());
    
    private FormButton downloadButton = new FormButton("Download", 15f);
    
    private FileEntry fileEntry = this;
    
    public FileEntry(HashMap<String, String> fileInfo){
        super(new BorderLayout());
        this.userID = fileInfo.get("user_id");
        this.fileID = fileInfo.get("id");
        this.fileRelPath = fileInfo.get("file_rel_path");
        this.fileName = fileInfo.get("file_name");
        this.fileType = fileInfo.get("file_type");
        this.fileSize = Long.valueOf(fileInfo.get("file_size"));
        this.fileUploadDate = fileInfo.get("file_upload_date");
        
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        
        DesignGridLayout infoPanelLayout = new DesignGridLayout(infoPanel);
        infoPanelLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(fileName, 16), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        infoPanelLayout.row().grid(new FormLabel("Type: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(fileType, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        infoPanelLayout.row().grid(new FormLabel("Upload Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(fileUploadDate, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));

        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showConfirmationMessage(deleteButton, deleteButton.getX()-50, deleteButton.getY());
            }
        });
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacultyMode.rightPanel.getFilesPanel().doDownload(fileEntry);
            }
        });
        
        add(new FormLabel(new ImageIcon("images/file.png"), 0), BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.EAST);
        add(downloadButton, BorderLayout.SOUTH);
    }
    
    
    public String getFileID(){
        return fileID;
    }
    
    public String getFileRelPath(){
        return fileRelPath;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public String getFileType(){
        return fileType;
    }
    
    public Long getFileSize(){
        return fileSize;
    }
    
    public String getUploadDate(){
        return fileUploadDate;
    }
    
    public void showConfirmationMessage(Component componentInvoker, int x, int y){
        JPopupMenu detailsPopup = new JPopupMenu();
        detailsPopup.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        detailsPopup.setBorder(new LineBorder(FrameWorkUtils.getAccentColor()));
        JPanel mainContainer = new JPanel(new GridLayout(2, 1, 5, 5)){
            @Override
            public void remove(Component component){
                //Do Nothing
            }
        };
        FormButton yesButton = new FormButton("Yes", 15f),
                   noButton = new FormButton("No", 15f);
        
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsPopup.setVisible(false);
                UserFiles.deleteFile(userID, fileID, fileRelPath);
            }
        });
        
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsPopup.setVisible(false);
            }
        });
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
        mainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainContainer.setOpaque(true);
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        mainContainer.add(new FormLabel("This file will be permanently deleted from the system. Confirm?", FrameWorkUtils.getSecondaryColor(), 15f));
        mainContainer.add(buttonPanel);
       
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
       
    }
    
}
