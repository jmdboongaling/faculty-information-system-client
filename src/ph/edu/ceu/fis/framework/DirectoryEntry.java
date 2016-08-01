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
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.lang3.StringUtils;

public class DirectoryEntry extends JPanel{
    
        String userID, 
               lastName,
               firstName, 
               middleName, 
               gender, 
               email,
               employeeStatus,
               employeePosition,
               employeeDepartment;
        
                
        
    public DirectoryEntry(BufferedImage userPicture, HashMap<String, String> employeeInfo, int listIndex){
        super(new BorderLayout(5, 5));
        this.userID = employeeInfo.get("user_id");
        this.lastName = employeeInfo.get("last_name");
        this.firstName = employeeInfo.get("first_name");
        this.middleName = employeeInfo.get("middle_name");
        this.gender = employeeInfo.get("gender");
        this.email = employeeInfo.get("email");
        this.employeeStatus = employeeInfo.get("employee_status");
        this.employeePosition = employeeInfo.get("employee_position");
        this.employeeDepartment = employeeInfo.get("employee_department");
        
        setOpaque(true);
        Color backgroundColor;
        if(listIndex % 2 != 0){
            backgroundColor = FrameWorkUtils.getPrimaryColor();
        }else{
            backgroundColor = FrameWorkUtils.getPrimaryColor().brighter();
        }
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        DesignGridLayout infoLayout = new DesignGridLayout(infoPanel);
        infoLayout.row().grid(new FormLabel("Last Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(lastName, 24), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("First Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(firstName, 24), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Middle Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(middleName, 24), FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Email: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(email, 24), FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Position: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(employeePosition, 24), FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Department: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(employeeDepartment, 24), FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));

        MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), backgroundColor);
        viewButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //showBulletinPost();
            }
        });
        add(new PictureLabel(userPicture, 60, 60, true), BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
    }
    
    
}
