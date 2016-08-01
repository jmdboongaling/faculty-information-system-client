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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.lang3.StringUtils;

public class ClassEntry extends JPanel{
    
    
    
    
    private String courseID,
                   courseCode,
                   courseDescription,
                   courseDay,
                   courseTime,
                   courseBuilding,
                   courseRoom,
                   courseYearSection,
                   courseSemester,
                   courseSchoolYear,
                   courseCampus,
                   courseLecUnits,
                   courseLabUnits;
                
    public ClassEntry(HashMap<String, String> courseInfo, int listIndex){
        super(new BorderLayout(5, 5));
        
        this.courseID = courseInfo.get("course_id");
        this.courseCode = courseInfo.get("course_code");
        this.courseDescription = courseInfo.get("course_description");
        this.courseDay = courseInfo.get("course_day");
        this.courseTime = courseInfo.get("course_time");
        this.courseBuilding = courseInfo.get("course_building");
        this.courseRoom = courseInfo.get("course_room");
        this.courseYearSection = courseInfo.get("course_year_section");
        this.courseSemester = courseInfo.get("course_semester");
        this.courseSchoolYear = courseInfo.get("course_school_year");
        this.courseCampus = courseInfo.get("course_campus");
        this.courseLecUnits = courseInfo.get("lec_units");
        this.courseLabUnits = courseInfo.get("lab_units");
        
        setOpaque(true);
        Color backgroundColor;
        if(listIndex % 2 != 0){
            backgroundColor = FrameWorkUtils.getPrimaryColor();
        }else{
            backgroundColor = FrameWorkUtils.getPrimaryColor().brighter();
        }
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        infoPanel.setOpaque(false);
        DesignGridLayout infoLayout = new DesignGridLayout(infoPanel);
        
        infoLayout.row().grid(new FormLabel("Course Code: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseCode, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        infoLayout.row().grid(new FormLabel("Course Description: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(courseDescription, 18), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));;
        infoLayout.row().grid(new FormLabel("Semester: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseSemester, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        infoLayout.row().grid(new FormLabel("School Year: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseSchoolYear, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        infoLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseCampus, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        
        MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), backgroundColor);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(viewButton, viewButton.getX(), viewButton.getY());
            }
        });
        add(new FormLabel(new ImageIcon("images/bullet.png"), 5), BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(viewButton, BorderLayout.EAST);
    }
    
    private void showDetails(Component componentInvoker, int x, int y){
        JPopupMenu detailsPopup = new JPopupMenu();
        detailsPopup.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        detailsPopup.setBorder(new LineBorder(FrameWorkUtils.getAccentColor()));
        JPanel mainContainer = new JPanel(){
            @Override
            public void remove(int index){
                //Do Nothing
            }
        };
        mainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainContainer.setOpaque(true);
        mainContainer.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        DesignGridLayout mainContainerLayout = new DesignGridLayout(mainContainer);
        mainContainerLayout.row().grid(new FormLabel("Course Code: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseCode, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Course Description: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseDescription, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));;
        mainContainerLayout.row().grid(new FormLabel("Semester: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseSemester, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("School Year: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseSchoolYear, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Day: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseDay, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Time: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseTime, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Building: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseBuilding, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Room: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseRoom, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Section: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseYearSection, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseCampus, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Lec. Units: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseLecUnits, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Lab Units: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(courseLabUnits, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
    }
}
