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
import ph.edu.ceu.fis.data.UserClasses;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.utils.ClientUtils;


public class Classes extends JPanel{

    private Session systemSession;

    private MenuButton searchButton = new MenuButton("", 0f, new ImageIcon("images/search16.png"), FrameWorkUtils.getPrimaryColor()),
                       userClassesButton = new MenuButton("", 0f, new ImageIcon("images/classes16.png"), FrameWorkUtils.getPrimaryColor());
        
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel displayPanel = new JPanel(panelSwitcher),
                   browseContainer = new JPanel(),
                   resultsPanel = new JPanel(),
                   coursesContainer = new JPanel(),
                   classesPanel = new JPanel();
    
    private DesignGridLayout browseLayout = new DesignGridLayout(browseContainer),
                             coursesLayout = new DesignGridLayout(coursesContainer);
    
    private ArrayList<HashMap<String, String>> fileEntries;
    
    private UserClasses userClasses;
    
  
           
    public Classes(Session systemSession){
        super(new BorderLayout());
        this.systemSession = systemSession;
        this.userClasses = new UserClasses(systemSession.getUserID());
        setOpaque(false);
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
       
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/record.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Classes", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        JPanel navigatorPanel = new JPanel(new GridLayout(1, 2));
        navigatorPanel.setOpaque(false);
        
        
        
        displayPanel.setOpaque(false);
        displayPanel.add(new CustomScrollPane(browseContainer()), "Browse Courses");
        displayPanel.add(new CustomScrollPane(coursesContainer()), "User Courses");
        displayPanel.add(new PanelPreloader("Searching for courses"), "Search Load");
        displayPanel.add(new PanelPreloader("Fetching your courses"), "Course Load");
        
        searchButton.setToolTipText("Browse Classes");
        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Browse Courses");

            }
        });
        
        userClassesButton.setToolTipText("Your Classes");
        userClassesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "User Courses");
            }
        });
        
        
        
        
        navigatorPanel.add(searchButton);
        navigatorPanel.add(userClassesButton);
        
        JPanel classContainer = new JPanel(new BorderLayout());
        classContainer.setOpaque(false);
        classContainer.add(navigatorPanel, BorderLayout.NORTH);
        classContainer.add(displayPanel, BorderLayout.CENTER);
        
        add(titlePanel, BorderLayout.NORTH);
        add(classContainer, BorderLayout.CENTER);
    }
    
    private JPanel browseContainer(){
        
        browseContainer.setOpaque(false);      
        resultsPanel.setOpaque(false);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        FormComboBox campusChoice = new FormComboBox(new Object[]{"", "Manila", "Makati", "Malolos"}),
                     semesterChoice = new FormComboBox(new Object[]{"", "1st", "2nd", "Summer"}),
                     schoolYearChoice = new FormComboBox(new Object[]{"", "2013 - 2014", "2014 - 2015", "2015 - 2016", "2016 - 2017"});
        
        FormField searchField = new FormField("", FrameWorkUtils.getSecondaryColor(), 15f);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getSearchClasses(searchField.getText().trim(), campusChoice.getSelectedItem().toString(), semesterChoice.getSelectedItem().toString(), schoolYearChoice.getSelectedItem().toString());
            }
        });
        
        
        
        browseLayout.labelAlignment(LabelAlignment.LEFT);
        browseLayout.emptyRow();
        browseLayout.emptyRow();
        browseLayout.row().center().add(new JLabel(new ImageIcon("images/search64.png")));
        browseLayout.row().center().add(new FormLabel("<HTML><B>Browse Open Classes</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        browseLayout.row().grid(new FormLabel("Search: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(searchField);
        browseLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(campusChoice);
        browseLayout.row().grid(new FormLabel("Semester: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(semesterChoice);
        browseLayout.row().grid(new FormLabel("School Year: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(schoolYearChoice);
        browseLayout.row().left().add(resultsPanel);

       
    
       
        
        return browseContainer;
    }
    
    private JPanel coursesContainer(){
        
        coursesContainer.setOpaque(false);      
        classesPanel.setOpaque(false);
        classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.Y_AXIS));
        FormComboBox campusChoice = new FormComboBox(new Object[]{"", "Manila", "Makati", "Malolos"}),
                     semesterChoice = new FormComboBox(new Object[]{"", "1st", "2nd", "Summer"}),
                     schoolYearChoice = new FormComboBox(new Object[]{"", "2013 - 2014", "2014 - 2015", "2015 - 2016", "2016 - 2017"});
        
        FormField filterField = new FormField("", FrameWorkUtils.getSecondaryColor(), 15f);
        filterField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getClasses(filterField.getText().trim(), campusChoice.getSelectedItem().toString(), semesterChoice.getSelectedItem().toString(), schoolYearChoice.getSelectedItem().toString());
            }
        });
        
        
        
        coursesLayout.labelAlignment(LabelAlignment.LEFT);
        coursesLayout.emptyRow();
        coursesLayout.emptyRow();
        coursesLayout.row().center().add(new JLabel(new ImageIcon("images/classes64.png")));
        coursesLayout.row().center().add(new FormLabel("<HTML><B>Your Classes</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f));
        coursesLayout.row().grid(new FormLabel("Filter: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(filterField);
        coursesLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(campusChoice);
        coursesLayout.row().grid(new FormLabel("Semester: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(semesterChoice);
        coursesLayout.row().grid(new FormLabel("School Year: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(schoolYearChoice);
        coursesLayout.row().left().add(classesPanel);

       
    
       
        
        return coursesContainer;
    }
    
   
    public void getSearchClasses(String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        Thread getSearchClasses = new Thread(new Runnable() {
            @Override
            public void run() {
                searchButton.setEnabled(false);
                userClassesButton.setEnabled(false);
                panelSwitcher.show(displayPanel, "Search Load");
                resultsPanel.removeAll();
                ArrayList<HashMap<String, String>> classesResults = new ArrayList<HashMap<String, String>>();
                classesResults = userClasses.getSearchResults(courseQuery, courseCampus, courseSemester, courseSchoolYear);
                if(classesResults.size() > 0){
                    for(int i = 0; i < classesResults.size(); i++){
                        resultsPanel.add(new ClassEntry(classesResults.get(i), i));
                        resultsPanel.add(Box.createRigidArea(new Dimension(5,5)));
                    } 
                }else{
                    resultsPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
                    resultsPanel.add(Box.createRigidArea(new Dimension(5,5)));
                }

                resultsPanel.revalidate();
                ClientUtils.wait(2);
                panelSwitcher.show(displayPanel, "Browse Courses");
                searchButton.setEnabled(true);
                userClassesButton.setEnabled(true);
            }
        });
        
        getSearchClasses.start();
        
        
    }
    
    public void getClasses(String courseQuery, String courseCampus, String courseSemester, String courseSchoolYear){
        Thread getClasses = new Thread(new Runnable() {
            @Override
            public void run() {
                searchButton.setEnabled(false);
                userClassesButton.setEnabled(false);
                panelSwitcher.show(displayPanel, "Course Load");
                classesPanel.removeAll();
                ArrayList<HashMap<String, String>> classesResults = new ArrayList<HashMap<String, String>>();
                classesResults = userClasses.getCourses(systemSession.getUserID(), courseQuery, courseCampus, courseSemester, courseSchoolYear);
                if(classesResults.size() > 0){
                    for(int i = 0; i < classesResults.size(); i++){
                        classesPanel.add(new ClassEntry(classesResults.get(i), i));
                        classesPanel.add(Box.createRigidArea(new Dimension(5,5)));
                    } 
                }else{
                    classesPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
                    classesPanel.add(Box.createRigidArea(new Dimension(5,5)));
                }

                classesPanel.revalidate();
                ClientUtils.wait(2);
                panelSwitcher.show(displayPanel, "User Courses");
                searchButton.setEnabled(true);
                userClassesButton.setEnabled(true);
            }
        });
        
        getClasses.start();
        
       
    }

    
}   
