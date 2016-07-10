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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.java.dev.designgridlayout.DesignGridLayout;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.BlurLayerUI;
import ph.edu.ceu.fis.framework.BulletinEntry;
import ph.edu.ceu.fis.framework.ClassEntry;
import ph.edu.ceu.fis.framework.CustomScollBar;
import ph.edu.ceu.fis.framework.CustomScrollPane;
import ph.edu.ceu.fis.framework.FormLabel;
import ph.edu.ceu.fis.framework.FrameWorkUtils;
import ph.edu.ceu.fis.framework.MenuButton;
import ph.edu.ceu.fis.framework.PictureLabel;
import ph.edu.ceu.fis.gui.LogoutPopup;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.gui.SystemFrame;
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

        
    private ArrayList<ArrayList<String>> courseEntries = new ArrayList<ArrayList<String>>();
    
    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/reload.png")), FrameWorkUtils.getPrimaryColor().brighter());

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
        
        PictureLabel y = new PictureLabel(systemSession.getProfilePicture(), 100, 100, true);
        FormLabel nameLabel =  new FormLabel(systemSession.getProfileInformation().get("first_name") + " " + systemSession.getProfileInformation().get("last_name"), FrameWorkUtils.getSecondaryColor(), 20f);
        y.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        FormLabel positionLabel = new FormLabel(systemSession.getProfileInformation().get("employee_position"), FrameWorkUtils.getSecondaryColor(), 15f);
        FormLabel departmentLabel = new FormLabel(systemSession.getProfileInformation().get("employee_department"), FrameWorkUtils.getSecondaryColor(), 12f);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setAlignmentX(SwingConstants.CENTER);
        infoPanel.setOpaque(false);
        
        DesignGridLayout infoPanelLayout = new DesignGridLayout(infoPanel);

        infoPanelLayout.row().center().add(y);
        infoPanelLayout.row().center().add(nameLabel);
        infoPanelLayout.row().center().add(positionLabel);
        infoPanelLayout.row().center().add(departmentLabel);
        
        MenuButton logoutButton = new MenuButton("Logout", 12f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/logout16.png")), FrameWorkUtils.getPrimaryColor()),
                   modeButton = new MenuButton("Mode", 12f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/session.png")), FrameWorkUtils.getPrimaryColor()),
                   lockButton = new MenuButton("Lock", 12f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/lock.png")), FrameWorkUtils.getPrimaryColor());
        
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
        
        JLabel titleLabel = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/latest_class.png")));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Latest Service Record", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        coursePanel.setOpaque(false);
        courseEntries = systemSession.getCourses();
        for(int i = 0; i < courseEntries.size(); i++){
            ArrayList<String> courseInfo = courseEntries.get(i);
            /*
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_id"));0
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_code"));1
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_description"));2
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_day"));3
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_time"));4
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_building"));5
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_room"));6
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_year_section"));7
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_semester"));8
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_school_year"));9
            courseInfo.add(serverResponse.getJSONObject(i).getString("course_campus"));10
            courseInfo.add(serverResponse.getJSONObject(i).getString("lec_units"));11
            courseInfo.add(serverResponse.getJSONObject(i).getString("lab_units"));12
            */
            //String courseID, String courseCode, String courseDescription, String courseDay, 
            //String courseTime, String courseRoom, String courseCampus, String courseSemesterYear, 
            //int listIndex
            
            coursePanelLayout.row().grid().add(new ClassEntry(courseInfo.get(0), courseInfo.get(1), courseInfo.get(2), courseInfo.get(3), courseInfo.get(4), courseInfo.get(5) + courseInfo.get(6), courseInfo.get(10), courseInfo.get(8) + " Semester S.Y. " + courseInfo.get(9), i));
        }
        switchPanel.setOpaque(false);
        switchPanel.add(new CustomScrollPane(coursePanel), "Main");
        switchPanel.add(new PanelPreloader("Getting Latest Classes"), "Load");
        bottomPanel.add(titlePanel, BorderLayout.NORTH);
        bottomPanel.add(switchPanel, BorderLayout.CENTER);
        return bottomPanel;
    }
    
    private void doLogout(){
        
      new LogoutPopup((JFrame) SwingUtilities.getWindowAncestor(this));
    }
    
    private void doSystemLock(){
        new SystemLock((JFrame) SwingUtilities.getWindowAncestor(this), systemSession);
    }
    private void reloadCourses(){
        Thread reloadBulletin = new Thread(new Runnable(){
            @Override
            public void run(){
                panelSwitcher.show(switchPanel, "Load");
                courseEntries = systemSession.getCourses();
                coursePanelLayout = null;
                coursePanel.setLayout(null);
                coursePanel.removeAll();
                coursePanelLayout = new DesignGridLayout(coursePanel);
                for(int i = 0; i < courseEntries.size(); i++){
                    ArrayList<String> courseInfo = courseEntries.get(i);
                    /*
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_id"));0
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_code"));1
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_description"));2
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_day"));3
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_time"));4
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_building"));5
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_room"));6
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_year_section"));7
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_semester"));8
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_school_year"));9
                    courseInfo.add(serverResponse.getJSONObject(i).getString("course_campus"));10
                    courseInfo.add(serverResponse.getJSONObject(i).getString("lec_units"));11
                    courseInfo.add(serverResponse.getJSONObject(i).getString("lab_units"));12
                    */
                    //String courseID, String courseCode, String courseDescription, String courseDay, 
                    //String courseTime, String courseRoom, String courseCampus, String courseSemesterYear, 
                    //int listIndex

                    coursePanelLayout.row().grid().add(new ClassEntry(courseInfo.get(0), courseInfo.get(1), courseInfo.get(2), courseInfo.get(3), courseInfo.get(4), courseInfo.get(5) + courseInfo.get(6), courseInfo.get(10), courseInfo.get(8) + " Semester S.Y. " + courseInfo.get(9), i));
                }
                
                coursePanel.revalidate();
                ClientUtils.wait(1);
                panelSwitcher.show(switchPanel, "Main");
            }
        });
        reloadBulletin.start();
    }
}
