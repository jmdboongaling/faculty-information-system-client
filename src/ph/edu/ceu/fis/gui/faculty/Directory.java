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
import ph.edu.ceu.fis.data.DirectoryData;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.utils.ClientUtils;


public class Directory extends JPanel{

    private Session systemSession;

    private MenuButton searchButton = new MenuButton("", 0f, new ImageIcon("images/search16.png"), FrameWorkUtils.getPrimaryColor());
        
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel displayPanel = new JPanel(panelSwitcher),
                   browseContainer = new JPanel(),
                   resultsPanel = new JPanel();
    
    private DesignGridLayout browseLayout = new DesignGridLayout(browseContainer);
    
    private ArrayList<HashMap<String, String>> directoryEntries = new ArrayList<HashMap<String, String>>();
    
    private DirectoryData directoryData = new DirectoryData();
  
           
    public Directory(Session systemSession){
        super(new BorderLayout());
        setOpaque(false);
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
       
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/directory.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Directory", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        
        
        
        displayPanel.setOpaque(false);
        displayPanel.add(new CustomScrollPane(browseContainer()), "Browse Directory");
        displayPanel.add(new PanelPreloader("Searching for employees"), "Search Load");
        
        searchButton.setToolTipText("Browse Directory");
        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Browse Directory");

            }
        });
        
       
        
        
        
        
        JPanel classContainer = new JPanel(new BorderLayout());
        classContainer.setOpaque(false);
        classContainer.add(displayPanel, BorderLayout.CENTER);
        
        add(titlePanel, BorderLayout.NORTH);
        add(classContainer, BorderLayout.CENTER);
    }
    
    private JPanel browseContainer(){
        
        browseContainer.setOpaque(false);      
        resultsPanel.setOpaque(false);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        
        FormField searchField = new FormField("", FrameWorkUtils.getSecondaryColor(), 15f);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getDirectoryResults(searchField.getText().trim());
            }
        });
        
       
        
        browseLayout.labelAlignment(LabelAlignment.LEFT);
        browseLayout.emptyRow();
        browseLayout.emptyRow();
        browseLayout.row().center().add(new JLabel(new ImageIcon("images/search64.png")));
        browseLayout.row().center().add(new FormLabel("<HTML><B>Search for Employees</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        browseLayout.row().grid(new FormLabel("Search: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(searchField);
        browseLayout.row().center().add(resultsPanel);

      
        return browseContainer;
    }
    
    public void getDirectoryResults(String directoryQuery){
        Thread getDirectoryResults = new Thread(new Runnable() {
            @Override
            public void run() {
                searchButton.setEnabled(false);
                panelSwitcher.show(displayPanel, "Search Load");
                resultsPanel.removeAll();
                directoryEntries = directoryData.getSearchResults(directoryQuery);
                if(directoryEntries.size() > 0){
                    for(int i = 0; i < directoryEntries.size(); i++){
                        resultsPanel.add(new DirectoryEntry(directoryData.getProfilePicture(directoryEntries.get(i).get("user_id"), directoryEntries.get(i).get("gender")), directoryEntries.get(i), i));
                        resultsPanel.add(Box.createRigidArea(new Dimension(5,5)));
                    } 
                }else{
                    resultsPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
                    resultsPanel.add(Box.createRigidArea(new Dimension(5,5)));
                }

                resultsPanel.revalidate();
                ClientUtils.wait(2);
                panelSwitcher.show(displayPanel, "Browse Directory");
                searchButton.setEnabled(true);
            }
        });
        
        getDirectoryResults.start();
        
        
    }
    
    
}   
