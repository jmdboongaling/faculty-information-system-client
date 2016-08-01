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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.data.UserFiles;
import ph.edu.ceu.fis.framework.ClassEntry;
import ph.edu.ceu.fis.framework.CustomScrollPane;
import ph.edu.ceu.fis.framework.FormButton;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MenuButton;
import ph.edu.ceu.fis.framework.MessageDialog;
import ph.edu.ceu.fis.framework.PictureLabel;
import ph.edu.ceu.fis.framework.TransferEntry;
import ph.edu.ceu.fis.gui.LogoutPopup;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.gui.SystemLock;
import ph.edu.ceu.fis.utils.ClientUtils;

public class CenterPanel extends JPanel{
    
    private Session systemSession;
    
    private GridBagLayout gridBagLayout = new GridBagLayout();
    
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    private CardLayout panelSwitcher = new CardLayout();
        
    private JPanel switchPanel = new JPanel(panelSwitcher),
                   coursePanel = new JPanel();
    
    private DesignGridLayout coursePanelLayout = new DesignGridLayout(coursePanel);

        
    private ArrayList<HashMap<String, String>> courseEntries = new ArrayList<HashMap<String, String>>();
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon("images/reload.png"), FrameWorkUtils.getPrimaryColor().brighter());
    
    private PictureLabel profilePicture;
    
    private JFileChooser fileBrowser;
    public CenterPanel(Session systemSession){
        this.systemSession = systemSession;
        setLayout(gridBagLayout);
        setOpaque(false);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = .10;
        gridBagConstraints.insets=new Insets(0, 0, 5, 0);
        
        add(topPanel(), gridBagConstraints);
        
        gridBagConstraints.gridy++;
        gridBagConstraints.weighty = .90;
        gridBagConstraints.insets=new Insets(5, 0, 0, 0);
        
        add(bottomPanel(), gridBagConstraints);
        
    }
    
    public JPanel topPanel(){
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setOpaque(true);
        topPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        
        profilePicture = new PictureLabel(systemSession.getProfilePicture(), 100, 100, true);
        profilePicture.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeProfilePicture(profilePicture, profilePicture.getX(), profilePicture.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                changeProfilePicture(profilePicture, profilePicture.getX(), profilePicture.getY());
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
        FormLabel nameLabel =  new FormLabel(systemSession.getProfileInformation().get("first_name") + " " + systemSession.getProfileInformation().get("last_name"), FrameWorkUtils.getSecondaryColor(), 20f);
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        FormLabel positionLabel = new FormLabel(systemSession.getProfileInformation().get("employee_position"), FrameWorkUtils.getSecondaryColor(), 15f);
        FormLabel departmentLabel = new FormLabel(systemSession.getProfileInformation().get("employee_department"), FrameWorkUtils.getSecondaryColor(), 12f);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setAlignmentX(SwingConstants.CENTER);
        infoPanel.setOpaque(false);
        
        DesignGridLayout infoPanelLayout = new DesignGridLayout(infoPanel);

        infoPanelLayout.row().center().add(profilePicture);
        infoPanelLayout.row().center().add(nameLabel);
        infoPanelLayout.row().center().add(positionLabel);
        infoPanelLayout.row().center().add(departmentLabel);
        
        MenuButton logoutButton = new MenuButton("Logout", 12f, new ImageIcon("images/logout16.png"), FrameWorkUtils.getPrimaryColor()),
                   modeButton = new MenuButton("Mode", 12f, new ImageIcon("images/session.png"), FrameWorkUtils.getPrimaryColor()),
                   lockButton = new MenuButton("Lock", 12f, new ImageIcon("images/lock.png"), FrameWorkUtils.getPrimaryColor());
        
        logoutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //new LogoutPopup((JFrame) SwingUtilities.getWindowAncestor(this));
                doLogout();
            }
        });
        
        modeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //RightPanel.doIt();
            }
        });
        
        lockButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                doSystemLock();
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setOpaque(false);
        buttonPanel.add(lockButton);
        buttonPanel.add(modeButton);
        buttonPanel.add(logoutButton);
        
        topPanel.add(infoPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        return topPanel;
    }
    
    public JPanel bottomPanel(){
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reloadCourses();
            }
        });
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/latest_class.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Latest Service Record", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        coursePanel.setOpaque(false);
        courseEntries = systemSession.getLatestCourses();
        for(int i = 0; i < courseEntries.size(); i++){
          
            
            coursePanelLayout.row().grid().add(new ClassEntry(courseEntries.get(i), i));
        }
        switchPanel.setOpaque(false);
        switchPanel.add(new CustomScrollPane(coursePanel), "Main");
        switchPanel.add(new PanelPreloader("Getting Latest Classes"), "Load");
        bottomPanel.add(titlePanel, BorderLayout.NORTH);
        bottomPanel.add(switchPanel, BorderLayout.CENTER);
        return bottomPanel;
    }
    
    private void doLogout(){
        
      new LogoutPopup((JFrame) SwingUtilities.getWindowAncestor(this), systemSession);
    }
    
    private void doSystemLock(){
        new SystemLock((JFrame) SwingUtilities.getWindowAncestor(this), systemSession);
    }
    private void reloadCourses(){
        Thread reloadBulletin = new Thread(new Runnable(){
            @Override
            public void run(){
                panelSwitcher.show(switchPanel, "Load");
                courseEntries = systemSession.getLatestCourses();
                coursePanelLayout = null;
                coursePanel.setLayout(null);
                coursePanel.removeAll();
                coursePanelLayout = new DesignGridLayout(coursePanel);
                for(int i = 0; i < courseEntries.size(); i++){
                    HashMap<String, String> courseInfo = courseEntries.get(i);
                    coursePanelLayout.row().grid().add(new ClassEntry(courseInfo, i));
                }
                
                coursePanel.revalidate();
                ClientUtils.wait(1);
                panelSwitcher.show(switchPanel, "Main");
            }
        });
        reloadBulletin.start();
    }
    
    private void changeProfilePicture(Component componentInvoker, int x, int y){
        
        JPopupMenu detailsPopup = new JPopupMenu();
        detailsPopup.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        detailsPopup.setBorder(new LineBorder(FrameWorkUtils.getAccentColor()));
        JPanel mainContainer = new JPanel(){
            @Override
            public void remove(int index){
                //Do Nothing
            }
        };
        
        mainContainer.setOpaque(true);
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        FormButton uploadButton = new FormButton("Change Profile Picture", 14f);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile();
            }
        });
        mainContainer.add(uploadButton);
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    
    }
    
    public void selectFile(){
        fileBrowser = null;
        File selectedFile;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            fileBrowser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
        int userChoice = fileBrowser.showOpenDialog(new JFrame("File Upload"));
        if(userChoice == JFileChooser.APPROVE_OPTION){
            selectedFile = new File(fileBrowser.getSelectedFile().getAbsolutePath());
            if(selectedFile.isFile()){
                if(selectedFile.length() < 2500000){
                    if(UserFiles.isAllowedImage(FilenameUtils.getExtension(selectedFile.getAbsolutePath()))){
                        try{
                            Thread uploadPicture = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    profilePicture.setIcon(new ImageIcon("images/panel_preloader.gif"));
                                    ClientUtils.wait(3);
                                    systemSession.changeProfilePicture(selectedFile);
                                    profilePicture.setIcon(systemSession.getProfilePicture(), 100, 100, true);
                                }
                            });
                            uploadPicture.start();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        MessageDialog.showMessage("Prohibited File Format", "The file you are is of a prohibited file type.\n\nFiltering files that can be uploaded to the system makes sure all data is secure and not vulnerable to malicious programs or attacks that may be in the uploaded file/s. Only files with the .jpg file extension are allowed for this operation.");
                    }
                }else{
                    MessageDialog.showMessage("File Is Too Big", "The file you are trying to upload is larger than 2.5MB.");
                }
            }else{
                selectFile();
            }

        }
    }
}
