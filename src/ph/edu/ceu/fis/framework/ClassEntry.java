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

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ClassEntry extends JPanel{
    
    private String courseCode, 
                   courseDescription, 
                   courseSchedule, 
                   semesterSchoolYear, 
                   campus;
            
    public ClassEntry(String courseCode, String courseDescription, String courseSchedule, String semesterSchoolYear, String campus){
        super(new GridLayout(1, 1));
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.courseSchedule = courseSchedule;
        this.semesterSchoolYear = semesterSchoolYear;
        this.campus = campus;
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(new CustomShadowBorder());
        add(mainContainer());
    }
    
    private JPanel mainContainer(){
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints gridbagConstraints = new GridBagConstraints();
        gridbagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridbagConstraints.weightx = 1;
        gridbagConstraints.gridx = 0;
        gridbagConstraints.insets = new Insets(5, 0, 5, 0);
        
        FormLabel courseCodeLabel = new FormLabel("<HTML><B>Course Code: </B>" + courseCode + "</HTML>", Color.BLACK, 15f, SwingConstants.LEFT, 20),
                  courseDescriptionLabel = new FormLabel("<HTML><B>Course Description: </B>" + courseDescription + "</HTML>", Color.BLACK, 15f, SwingConstants.LEFT, 20),
                  courseSchedule = new FormLabel("<HTML><B>Schedule: </B>" + this.courseSchedule + "</HTML>", Color.BLACK, 15f, SwingConstants.LEFT, 20),
                  semesterSchoolyearLabel = new FormLabel("<HTML><B>" + semesterSchoolYear + "</B></HTML>", Color.BLACK, 15f, SwingConstants.LEFT, 20),
                  campusLabel = new FormLabel("<HTML><B>" + campus + "</B></HTML>", Color.BLACK, 15f, SwingConstants.LEFT, 20);
        
        mainContainer.add(courseCodeLabel, gridbagConstraints);
        mainContainer.add(courseDescriptionLabel, gridbagConstraints);
        mainContainer.add(courseSchedule, gridbagConstraints);
        mainContainer.add(semesterSchoolyearLabel, gridbagConstraints);
        mainContainer.add(campusLabel, gridbagConstraints);
        
        
        return mainContainer;
    }
}
