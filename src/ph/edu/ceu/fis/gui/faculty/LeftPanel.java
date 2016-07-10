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

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/reload.png")), FrameWorkUtils.getPrimaryColor());
    
    private ArrayList<ArrayList<String>> bulletinEntries = new ArrayList<ArrayList<String>>();
    
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
        
        titlePanel.add(new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/bulletin.png"))), BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Bulletin", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        
        
        bulletinPanel.setOpaque(false);
        bulletinEntries = systemSession.getBulletins(true, true, true);
        for(int i = 0; i < bulletinEntries.size(); i++){
            ArrayList<String> bulletinInfo = bulletinEntries.get(i);
            bulletinPanelLayout.row().grid().add(new BulletinEntry(bulletinInfo.get(0), bulletinInfo.get(1), bulletinInfo.get(2), bulletinInfo.get(3), bulletinInfo.get(4), bulletinInfo.get(5), i));
        }
        
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reloadBulletin();
            }
        });
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        filterPanel.setOpaque(false);
        filterPanel.add(manilaCheckBox);
        filterPanel.add(makatiCheckBox);
        filterPanel.add(malolosCheckBox);
        filterPanel.add(reloadButton);
        
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
        MenuButton profileButton = new MenuButton("Profile", 13f, new  ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/profile.png")), FrameWorkUtils.getPrimaryColor()),
                   serviceRecordButton = new MenuButton("Classes", 13f, new  ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/record.png")), FrameWorkUtils.getPrimaryColor()),
                   filesButton = new MenuButton("Files", 13f, new  ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/files.png")), FrameWorkUtils.getPrimaryColor()),
                   settingsButton = new MenuButton("Settings", 13f, new  ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/settings.png")), FrameWorkUtils.getPrimaryColor());
        
        bottomPanel.add(profileButton);
        bottomPanel.add(serviceRecordButton);
        bottomPanel.add(filesButton);
        bottomPanel.add(settingsButton);
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
                    ArrayList<String> bulletinInfo = bulletinEntries.get(i);
                    bulletinPanelLayout.row().grid().add(new BulletinEntry(bulletinInfo.get(0), bulletinInfo.get(1), bulletinInfo.get(2), bulletinInfo.get(3), bulletinInfo.get(4), bulletinInfo.get(5), i));
                }
                
                bulletinPanel.revalidate();
                ClientUtils.wait(1);
                panelSwitcher.show(switchPanel, "Main");
            }
        });
        reloadBulletin.start();
    }
}
