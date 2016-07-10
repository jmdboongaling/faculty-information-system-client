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
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClassEntry extends JPanel{
    
    public ClassEntry(String courseID, String courseCode, String courseDescription, String courseDay, String courseTime, String courseRoom, String courseCampus, String courseSemesterYear, int listIndex){
        super(new BorderLayout(5, 5));
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
        infoPanel.add(new FormLabel(courseCode, FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 5));
        infoPanel.add(new FormLabel(courseDescription, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));
        infoPanel.add(new FormLabel("<HTML><B>Day: </B>"+ courseDay + " <B>Time: </B>" + courseTime + " <B>Room: </B>" + courseRoom +"</HTML>", FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));
        infoPanel.add(new FormLabel(courseCampus + " Campus", FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));
        infoPanel.add(new FormLabel(courseSemesterYear, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));

        MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/view.png")), backgroundColor);
        add(new FormLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/bullet.png")), 5), BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(viewButton, BorderLayout.EAST);
    }
}
