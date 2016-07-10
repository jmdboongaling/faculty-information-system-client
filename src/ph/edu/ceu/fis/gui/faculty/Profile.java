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
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.jdesktop.swingx.JXLabel;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.PanelPreloader;

public class Profile extends JPanel{
    
    private Session systemSession;

    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/reload.png")), FrameWorkUtils.getPrimaryColor().brighter());
    
    private CardLayout panelSwitcher = new CardLayout();
    
    private JPanel displayPanel = new JPanel(panelSwitcher);
    
    private DisplayField employeeStatus,
                         employeePosition,
                         employeeDepartment,
                         lastName,
                         firstName,
                         middleName,
                         employeeGender,
                         civilStatus,
                         birthDay,
                         birthPlace,
                         emailAddress,
                         employeeReligion,
                         employeeCitizenship,
                         emergencyName,
                         emergencyRelationship,
                         emergencyContact;
    
    private WrapField spouseName,
                      presentAddress,
                      presentContact,
                      residenceAddress,
                      residenceContact;
    
    private  HashMap<String, String> profileInformation;
    
    private boolean inEditMode = false;
    
    public Profile(Session systemSession){
        super(new BorderLayout());
        this.systemSession = systemSession;
        profileInformation = this.systemSession.getProfileInformation();
        setOpaque(false);
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //reloadCourses();
            }
        });
        
        JLabel titleLabel = new JLabel(new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/profile.png")));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Profile", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        JPanel navigatorPanel = new JPanel(new GridLayout(1, 4));
        navigatorPanel.setOpaque(false);
        
        
        
        displayPanel.setOpaque(false);
        displayPanel.add(new CustomScrollPane(basicInformation()), "Basic Information");
        displayPanel.add(new CustomScrollPane(basicInformation()), "Basic Information");
        displayPanel.add(new CustomScrollPane(basicInformation()), "Basic Information");
        displayPanel.add(new PanelPreloader("Loading Profile"), "Load");
        displayPanel.add(new PanelPreloader("Saving Changes"), "Save");
        
        MenuButton basicButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator_hover.png")), FrameWorkUtils.getPrimaryColor()),
                   workButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator_hover.png")), FrameWorkUtils.getPrimaryColor()),
                   researchButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator.png")), new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/navigator_hover.png")), FrameWorkUtils.getPrimaryColor()),
                   editButton = new MenuButton("", 0f, new ImageIcon(getClass().getResource("/ph/edu/ceu/fis/res/images/edit.png")), FrameWorkUtils.getPrimaryColor());
        
        basicButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Basic Information");

            }
        });
        
        workButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Load");
            }
        });
        
        researchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Save");
            }
        });
        
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(inEditMode){
                    enterEditMode(false);
                }else{
                    enterEditMode(true);
                }
            }
        });
        navigatorPanel.add(basicButton);
        navigatorPanel.add(workButton);
        navigatorPanel.add(researchButton);
        navigatorPanel.add(editButton);
        
        JPanel profileContainer = new JPanel(new BorderLayout());
        profileContainer.setOpaque(false);
        profileContainer.add(navigatorPanel, BorderLayout.NORTH);
        profileContainer.add(displayPanel, BorderLayout.CENTER);
        
        add(titlePanel, BorderLayout.NORTH);
        add(profileContainer, BorderLayout.CENTER);
    }
    
    private JPanel basicInformation(){
        JPanel mainContainer = new JPanel();
        mainContainer.setOpaque(false);
        
       
        
                
        DesignGridLayout basicInformationLayout = new DesignGridLayout(mainContainer);
        
        emergencyContact = new DisplayField(profileInformation.get("emergency_contact"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeeStatus = new DisplayField(profileInformation.get("employee_status"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeePosition = new DisplayField(profileInformation.get("employee_position"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeeDepartment = new DisplayField(profileInformation.get("employee_department"), FrameWorkUtils.getSecondaryColor(), 13f);
        lastName = new DisplayField(profileInformation.get("last_name"), FrameWorkUtils.getSecondaryColor(), 13f);
        firstName = new DisplayField(profileInformation.get("first_name"), FrameWorkUtils.getSecondaryColor(), 13f);
        middleName = new DisplayField(profileInformation.get("middle_name"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeeGender = new DisplayField(profileInformation.get("gender"), FrameWorkUtils.getSecondaryColor(), 13f);
        civilStatus = new DisplayField(profileInformation.get("civil_status"), FrameWorkUtils.getSecondaryColor(), 13f);
        birthDay = new DisplayField(profileInformation.get("birthday"), FrameWorkUtils.getSecondaryColor(), 13f);
        birthPlace = new DisplayField(profileInformation.get("birth_place"), FrameWorkUtils.getSecondaryColor(), 13f);
        emailAddress = new DisplayField(profileInformation.get("email"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeeReligion = new DisplayField(profileInformation.get("religion"), FrameWorkUtils.getSecondaryColor(), 13f);
        employeeCitizenship = new DisplayField(profileInformation.get("citizenship"), FrameWorkUtils.getSecondaryColor(), 13f);        
        emergencyName = new DisplayField(profileInformation.get("emergency_name"), FrameWorkUtils.getSecondaryColor(), 13f);
        emergencyRelationship = new DisplayField(profileInformation.get("emergency_relationship"), FrameWorkUtils.getSecondaryColor(), 13f);
        emergencyContact = new DisplayField(profileInformation.get("emergency_contact"), FrameWorkUtils.getSecondaryColor(), 13f);
        
        spouseName = new WrapField(profileInformation.get("spouse_name"), FrameWorkUtils.getSecondaryColor(), 13f);
        presentAddress = new WrapField(profileInformation.get("present_address"), FrameWorkUtils.getSecondaryColor(), 13f);
        presentContact = new WrapField(profileInformation.get("present_contact"), FrameWorkUtils.getSecondaryColor(), 13f);
        residenceAddress = new WrapField(profileInformation.get("residence_address"), FrameWorkUtils.getSecondaryColor(), 13f);
        residenceContact = new WrapField(profileInformation.get("residence_contact"), FrameWorkUtils.getSecondaryColor(), 13f);

        basicInformationLayout.labelAlignment(LabelAlignment.LEFT);
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.row().left().add(new FormLabel("<HTML><B>Work Status</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().grid(new FormLabel("Status: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeeStatus);
        basicInformationLayout.row().grid(new FormLabel("Position: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeePosition);
        basicInformationLayout.row().grid(new FormLabel("Department: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeeDepartment);
        basicInformationLayout.row().left().add(new JSeparator());
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.row().left().add(new FormLabel("<HTML><B>Basic Information</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().grid(new FormLabel("Last Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(lastName);
        basicInformationLayout.row().grid(new FormLabel("First Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(firstName);
        basicInformationLayout.row().grid(new FormLabel("Middle Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(middleName);
        basicInformationLayout.row().grid(new FormLabel("Gender: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeeGender);
        basicInformationLayout.row().grid(new FormLabel("Civil Stauts: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(civilStatus);
        basicInformationLayout.row().grid(new FormLabel("Birthday: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(birthDay);
        basicInformationLayout.row().grid(new FormLabel("Birth Place: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(birthPlace);
        basicInformationLayout.row().grid(new FormLabel("E-Mail: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(emailAddress);
        basicInformationLayout.row().grid(new FormLabel("Religion: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeeReligion);
        basicInformationLayout.row().grid(new FormLabel("Citizenship: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(employeeCitizenship);
        basicInformationLayout.row().grid(new FormLabel("Spouse: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(spouseName);
        basicInformationLayout.row().grid(new FormLabel("Present Address: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(presentAddress);
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(presentContact);
        basicInformationLayout.row().grid(new FormLabel("Residential Address", FrameWorkUtils.getSecondaryColor(), 13f)).add(residenceAddress);
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(residenceContact);
        basicInformationLayout.row().left().add(new JSeparator());
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.row().left().add(new FormLabel("<HTML><B>Emergency Contact</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(emergencyName);
        basicInformationLayout.row().grid(new FormLabel("Relationship: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(emergencyRelationship);
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(emergencyContact);
        basicInformationLayout.row().left().add(new JSeparator());
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.row().left().add(new FormLabel("<HTML><B>Children</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().left().add(new ChildEntry("", "Chantelle Macalino Dizon", "Female", 1));
        basicInformationLayout.row().left().add(new ChildEntry("", "Atoy Macalino Dizon", "Male", 2));
        basicInformationLayout.row().left().add(new ChildEntry("", "Joaquin Armand Philippe Peralta Dizon", "Male", 3));
        basicInformationLayout.row().left().add(new JSeparator());
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.row().left().add(new FormLabel("<HTML><B>Specialization Fields(s)</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().left().add(specializationFields(profileInformation.get("specialization_fields")));
        basicInformationLayout.row().left().add(new JSeparator());
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
        basicInformationLayout.emptyRow();
       
        return mainContainer;
    }
    
    private JPanel specializationFields(String specializationFields){
        String[] skillList = specializationFields.split(",");
        
        JPanel specializationPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 5, 5));
        specializationPanel.setOpaque(false);
        
        
        for(int i = 0; i < skillList.length; i++){
            
            JLabel skillEntry = new JLabel(skillList[i].trim());
            skillEntry.setOpaque(true);
            skillEntry.setForeground(FrameWorkUtils.getSecondaryColor());
            skillEntry.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
            skillEntry.setFont(FrameWorkUtils.getSystemFont().deriveFont(13f));
            skillEntry.setBorder(new EmptyBorder(5, 5, 5, 5));
            specializationPanel.add(skillEntry);
            
        }
        
        return specializationPanel;
    }
    
    private void enterEditMode(boolean editMode){
            employeeStatus.setEnabled(editMode);
            employeePosition.setEnabled(editMode);
            employeeDepartment.setEnabled(editMode);
            lastName.setEnabled(editMode);
            firstName.setEnabled(editMode);
            middleName.setEnabled(editMode);
            employeeGender.setEnabled(editMode);
            civilStatus.setEnabled(editMode);
            birthDay.setEnabled(editMode);
            birthPlace.setEnabled(editMode);
            emailAddress.setEnabled(editMode);
            employeeReligion.setEnabled(editMode);
            employeeCitizenship.setEnabled(editMode);
            emergencyName.setEnabled(editMode);
            emergencyRelationship.setEnabled(editMode);
            emergencyContact.setEnabled(editMode);
        
    }
}
