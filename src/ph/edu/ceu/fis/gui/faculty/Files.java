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
package ph.edu.ceu.fis.gui.faculty; 

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.data.UserFiles;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.utils.ClientUtils;


public class Files extends JPanel{

    private Session systemSession;

    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon("images/reload.png"), FrameWorkUtils.getPrimaryColor().brighter()),
                       filesButton = new MenuButton("", 0f, new ImageIcon("images/download.png"), FrameWorkUtils.getPrimaryColor()),
                       uploadButton = new MenuButton("", 0f, new ImageIcon("images/upload.png"), new ImageIcon("images/upload.png"), FrameWorkUtils.getPrimaryColor()),
                       progressButton = new MenuButton("", 0f, new ImageIcon("images/progress.png"), new ImageIcon("images/progress.png"), FrameWorkUtils.getPrimaryColor());
        
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel displayPanel = new JPanel(panelSwitcher);
    
    private ArrayList<HashMap<String, String>> fileEntries;
    
    private UserFiles userFiles;
    
    private FormField filePath = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                      fileName = new FormField(FrameWorkUtils.getSecondaryColor(), 15f);
    
    private FormLabel fileSize = new FormLabel("0.00", FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
                         
    private JFileChooser fileBrowser;
    
    private File selectedFile = null;
    
    private JPanel progressContainer = new JPanel();
    
    private DesignGridLayout progressLayout = new DesignGridLayout(progressContainer);
    
    public Files(Session systemSession){
        super(new BorderLayout());
        this.systemSession = systemSession;
        this.userFiles = new UserFiles(systemSession.getUserID());
        this.fileEntries = userFiles.getFileEntries();
        setOpaque(false);
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshFiles();
            }
        });
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/files.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Files", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        JPanel navigatorPanel = new JPanel(new GridLayout(1, 3));
        navigatorPanel.setOpaque(false);
        
        
        
        displayPanel.setOpaque(false);
        displayPanel.add(new CustomScrollPane(filesContainer()), "User Files");
        displayPanel.add(new CustomScrollPane(uploadContainer()), "Upload File");
        displayPanel.add(new CustomScrollPane(progressContainer()), "FTP Progress");
        displayPanel.add(new PanelPreloader("Loading Files"), "Load");
        
        filesButton.setToolTipText("User Files");
        filesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "User Files");

            }
        });
        
        uploadButton.setToolTipText("Upload a File");
        uploadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Upload File");
            }
        });
        
        progressButton.setToolTipText("Upload/Download Progress");
        progressButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "FTP Progress");
            }
        });
        
        
        navigatorPanel.add(filesButton);
        navigatorPanel.add(uploadButton);
        navigatorPanel.add(progressButton);
        
        JPanel filesContainer = new JPanel(new BorderLayout());
        filesContainer.setOpaque(false);
        filesContainer.add(navigatorPanel, BorderLayout.NORTH);
        filesContainer.add(displayPanel, BorderLayout.CENTER);
        
        add(titlePanel, BorderLayout.NORTH);
        add(filesContainer, BorderLayout.CENTER);
    }
    
    private JPanel filesContainer(){
        JPanel mainContainer = new JPanel();
        mainContainer.setOpaque(false);      
        DesignGridLayout filesLayout = new DesignGridLayout(mainContainer);
       
        filesLayout.labelAlignment(LabelAlignment.LEFT);
        filesLayout.emptyRow();
        filesLayout.emptyRow();
        filesLayout.row().center().add(new JLabel(new ImageIcon("images/download64.png")));
        filesLayout.row().center().add(new FormLabel("<HTML><B>User Files</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        filesLayout.row().grid(new FormLabel("Total Capacity: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel("2.5GB", FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        filesLayout.row().grid(new FormLabel("Used: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(userFiles.getUsedCapacity(), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        filesLayout.row().grid(new FormLabel("Percentage: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(userFiles.getUsedPercentage(), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));


        if(fileEntries.size() > 0){
            for(int i = 0; i < fileEntries.size(); i++){
                filesLayout.row().left().add(new FileEntry(fileEntries.get(i)));
            }
        }else{
            filesLayout.row().left().add(new FormLabel("No Files", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
    
       
        
        return mainContainer;
    }
    
    private JPanel uploadContainer(){
        JPanel mainContainer = new JPanel();
        mainContainer.setOpaque(false); 
        
        
        filePath.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectFile();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectFile();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        FormButton uploadButton = new FormButton("Upload", 15f);
        uploadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(selectedFile != null && selectedFile.isFile()){
                    doUpload(fileName.getText());
                }else{
                    selectFile();
                } 
            }
        });

        
        DesignGridLayout filesLayout = new DesignGridLayout(mainContainer);
        
        filesLayout.labelAlignment(LabelAlignment.LEFT);
        filesLayout.emptyRow();
        filesLayout.emptyRow();
        filesLayout.row().center().add(new JLabel(new ImageIcon("images/upload64.png")));
        filesLayout.row().center().add(new FormLabel("<HTML><B>Upload a File</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        
    
        filesLayout.row().grid(new FormLabel("File: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(filePath);
        filesLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(fileName);
        filesLayout.row().grid(new FormLabel("Size: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(fileSize);
        filesLayout.row().center().add(uploadButton);
        
        return mainContainer;
    }
    
    public void selectFile(){
        fileBrowser = null;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            fileBrowser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
        int userChoice = fileBrowser.showOpenDialog(new JFrame("File Upload"));
        if(userChoice == JFileChooser.APPROVE_OPTION){
            selectedFile = fileBrowser.getSelectedFile();
            filePath.setText(StringUtils.abbreviate(selectedFile.getAbsolutePath(), 36));
            fileName.setText(StringUtils.abbreviate(FilenameUtils.getName(selectedFile.getAbsolutePath()), 36));
            FilenameUtils.getExtension("");
            fileSize.setText(ClientUtils.getFileSize(selectedFile.length()));
        }
    }
    
    private JPanel progressContainer(){
        progressContainer.setOpaque(false); 
        
        progressLayout.labelAlignment(LabelAlignment.LEFT);
        progressLayout.emptyRow();
        progressLayout.emptyRow();
        progressLayout.row().center().add(new JLabel(new ImageIcon("images/progress64.png")));
        progressLayout.row().center().add(new FormLabel("<HTML><B>File Transfer Progress</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        
    

        
        return progressContainer;
    }
    
    private void doUpload(String fileName){
        
        if(selectedFile.length() < 25000000){
            if(selectedFile.length() < userFiles.getFreeSpace()){
                if(UserFiles.isAllowedFile(FilenameUtils.getExtension(selectedFile.getAbsolutePath()))){
                    TransferEntry transferEntry = new TransferEntry(fileName, TransferEntry.UPLOAD_MODE);
                    userFiles.uploadFile(selectedFile, fileName, transferEntry);
                    progressLayout.row().grid().add(transferEntry);
                    progressContainer.revalidate();
                    panelSwitcher.show(displayPanel, "FTP Progress"); 
                }else{
                    MessageDialog.showMessage("Prohibited File Format", "The file you are is of a prohibited file type.\n\nFiltering files that can be uploaded to the system makes sure all data is secure and not vulnerable to malicious programs or attacks that may be in the uploaded file/s.");
                }
                
            }else{
                MessageDialog.showMessage("File Is Too Big", "You do not have sufficient space in your account to upload this file.");
            }
            
        }else{
            MessageDialog.showMessage("File Is Too Big", "The file you are trying to upload is larger than 25MB.");
        }
        
    }
    
    public void doDownload(FileEntry fileEntry){
        TransferEntry transferEntry = new TransferEntry(fileEntry.getFileName(), TransferEntry.DOWNLOAD_MODE);
        progressLayout.row().grid().add(transferEntry);
        userFiles.downloadFile(systemSession.getUserID(), fileEntry.getFileRelPath(), fileEntry.getFileSize(), transferEntry);
        progressContainer.revalidate();
        panelSwitcher.show(displayPanel, "FTP Progress");
    }
    
    public void refreshFiles(){
        Thread refreshFiles = new Thread(new Runnable() {
            @Override
            public void run() {
                filesButton.setEnabled(false);
                uploadButton.setEnabled(false);
                progressButton.setEnabled(false);
                panelSwitcher.show(displayPanel, "Load");
                displayPanel.remove(new CustomScrollPane(filesContainer()));
                fileEntries = userFiles.getFileEntries();
                displayPanel.add(new CustomScrollPane(filesContainer()), "User Files");
                displayPanel.revalidate();
                ClientUtils.wait(2);
                panelSwitcher.show(displayPanel, "User Files");
                filesButton.setEnabled(true);
                uploadButton.setEnabled(true);
                progressButton.setEnabled(true);
            }
        });
        
        refreshFiles.start();
    }
    
}   
