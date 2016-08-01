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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.BulletinEntry;
import ph.edu.ceu.fis.framework.CustomScrollPane;
import ph.edu.ceu.fis.framework.FormCheckBox;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MenuButton;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.utils.ClientUtils;

public class LeftPanel extends JPanel{
    
    private Session systemSession;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel switchPanel = new JPanel(panelSwitcher),
                   bulletinPanel = new JPanel();
    
    private DesignGridLayout bulletinPanelLayout= new DesignGridLayout(bulletinPanel);

    private FormCheckBox makatiCheckBox = new FormCheckBox("Makati"),
                         manilaCheckBox = new FormCheckBox("Manila"),
                         malolosCheckBox = new FormCheckBox("Malolos");
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon("images/reload.png"), FrameWorkUtils.getPrimaryColor().brighter());
    
    private ArrayList<HashMap<String, String>> bulletinEntries = new ArrayList<HashMap<String, String>>();
    
    public LeftPanel(Session systemSession){
        this.systemSession = systemSession;
        setLayout(gridBagLayout);
        setOpaque(false);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = .70;
        gridBagConstraints.insets=new Insets(0, 0, 5, 0);
        
        add(topPanel(), gridBagConstraints);
        
        gridBagConstraints.gridy++;
        gridBagConstraints.weighty = .30;
        gridBagConstraints.insets=new Insets(5, 0, 0, 0);
        
        add(bottomPanel(), gridBagConstraints);
        
    }
    
    public JPanel topPanel(){
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(true);
        topPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reloadBulletin();
            }
        });
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/bulletin.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Bulletin", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        
        
        bulletinPanel.setOpaque(false);
        bulletinEntries = systemSession.getBulletins(true, true, true);
        for(int i = 0; i < bulletinEntries.size(); i++){
            HashMap<String, String> bulletinInfo = bulletinEntries.get(i);
            bulletinPanelLayout.row().grid().add(new BulletinEntry(bulletinInfo, i));
        }
        
        
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        filterPanel.setOpaque(false);
        filterPanel.add(manilaCheckBox);
        filterPanel.add(makatiCheckBox);
        filterPanel.add(malolosCheckBox);
        
        JPanel bulletinContainer = new JPanel(new BorderLayout(5, 5));
        bulletinContainer.setOpaque(false);
        bulletinContainer.add(filterPanel, BorderLayout.NORTH);
        bulletinContainer.add(new CustomScrollPane(bulletinPanel), BorderLayout.CENTER);
        switchPanel.setOpaque(false);
        switchPanel.add(bulletinContainer, "Main");
        switchPanel.add(new PanelPreloader("Loading Announcements"), "Load");
        
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(switchPanel, BorderLayout.CENTER);
        
        topPanel.setPreferredSize(new Dimension(500, 500));
        
        return topPanel;
    }
    
    public JPanel bottomPanel(){
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(FrameWorkUtils.getPrimaryColor());
        MenuButton profileButton = new MenuButton("Profile", 13f, new  ImageIcon("images/profile.png"), FrameWorkUtils.getPrimaryColor()),
                   serviceRecordButton = new MenuButton("Classes", 13f, new  ImageIcon("images/record.png"), FrameWorkUtils.getPrimaryColor()),
                   filesButton = new MenuButton("Files", 13f, new  ImageIcon("images/files.png"), FrameWorkUtils.getPrimaryColor()),
                   directoryButton = new MenuButton("Directory", 13f, new  ImageIcon("images/directory.png"), FrameWorkUtils.getPrimaryColor());
        
        profileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FacultyMode.rightPanel.switchToProfile();
            }
        });
        
        filesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FacultyMode.rightPanel.switchToFiles();
            }
        });
        
        serviceRecordButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FacultyMode.rightPanel.switchToClasses();
            }
        });
        
        directoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FacultyMode.rightPanel.switchToDirectory();
            }
        });
        bottomPanel.add(profileButton);
        bottomPanel.add(serviceRecordButton);
        bottomPanel.add(filesButton);
        bottomPanel.add(directoryButton);
        return bottomPanel;
    }
    
    private void reloadBulletin(){
        Thread reloadBulletin = new Thread(new Runnable(){
            @Override
            public void run(){
                panelSwitcher.show(switchPanel, "Load");
                bulletinEntries = systemSession.getBulletins(manilaCheckBox.isChecked(), makatiCheckBox.isChecked(), malolosCheckBox.isChecked());
                bulletinPanelLayout = null;
                bulletinPanel.setLayout(null);
                bulletinPanel.removeAll();
                bulletinPanelLayout = new DesignGridLayout(bulletinPanel);
                for(int i = 0; i < bulletinEntries.size(); i++){
                    HashMap<String, String> bulletinInfo = bulletinEntries.get(i);
                    bulletinPanelLayout.row().grid().add(new BulletinEntry(bulletinInfo, i));
                }
                
                bulletinPanel.revalidate();
                ClientUtils.wait(1);
                panelSwitcher.show(switchPanel, "Main");
            }
        });
        reloadBulletin.start();
    }
}
