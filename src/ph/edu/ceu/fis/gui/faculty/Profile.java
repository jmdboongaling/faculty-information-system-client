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
import net.java.dev.designgridlayout.*;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.LoginForm;
import ph.edu.ceu.fis.gui.PanelPreloader;
import ph.edu.ceu.fis.utils.ClientUtils;

public class Profile extends JPanel{
    
    private Session systemSession;

    private MenuButton reloadButton = new MenuButton("", 0f, new ImageIcon("images/reload.png"), FrameWorkUtils.getPrimaryColor().brighter()),
                       basicButton = new MenuButton("", 0f, new ImageIcon("images/basic.png"), FrameWorkUtils.getPrimaryColor()),
                       workButton = new MenuButton("", 0f, new ImageIcon("images/work.png"), FrameWorkUtils.getPrimaryColor()),
                       researchButton = new MenuButton("", 0f, new ImageIcon("images/research.png"), FrameWorkUtils.getPrimaryColor()),
                       editButton = new MenuButton("", 0f, new ImageIcon("images/edit.png"), FrameWorkUtils.getPrimaryColor());
        
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
    
    private ArrayList<ArrayList<String>> childEntries;
    
    private ArrayList<HashMap<String, String>> educationEntries,
                                               workEntries,
                                               adminAppointmentEntries,
                                               licensureEntries,
                                               consultancyEntries,
                                               communityWorkEntries,
                                               professionalOrganizationEntries,
                                               eventAttendanceEntries,
                                               researchEntries,
                                               researchFellowshipAwardsEntries;
    
    
    public Profile(Session systemSession){
        super(new BorderLayout());
        this.systemSession = systemSession;
        profileInformation = this.systemSession.getProfileInformation();
        childEntries = this.systemSession.getChildren();
        educationEntries = this.systemSession.getEducation();
        workEntries = this.systemSession.getWork();
        adminAppointmentEntries = this.systemSession.getAdminAppointment();
        licensureEntries = this.systemSession.getLicensure();
        consultancyEntries = this.systemSession.getConsultancy();
        communityWorkEntries = this.systemSession.getCommunityWork();
        professionalOrganizationEntries = this.systemSession.getProfessionalOrganization();
        eventAttendanceEntries = this.systemSession.getEventAttendance();
        researchEntries = this.systemSession.getResearch();
        researchFellowshipAwardsEntries = this.systemSession.getResearchFellowshipAwards();
        setOpaque(false);
        
        JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        titlePanel.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshProfile();
            }
        });
        
        JLabel titleLabel = new JLabel(new ImageIcon("images/profile.png"));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        titleLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleLabel.add(reloadButton);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(new FormLabel("Profile", FrameWorkUtils.getSecondaryColor(), 18f, SwingConstants.CENTER, 0), BorderLayout.CENTER);
        
        JPanel navigatorPanel = new JPanel(new GridLayout(1, 4));
        navigatorPanel.setOpaque(false);

        displayPanel.setOpaque(false);
        displayPanel.add(new CustomScrollPane(basicInformation()), "Basic Information");
        displayPanel.add(new CustomScrollPane(experienceInformation()), "Experience Information");
        displayPanel.add(new CustomScrollPane(researchInformation()), "Research Information");
        displayPanel.add(new PanelPreloader("Loading Profile"), "Load");
        
        basicButton.setToolTipText("Basic Information");
        basicButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Basic Information");

            }
        });
        
        workButton.setToolTipText("Experience");
        workButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Experience Information");
            }
        });
        
        researchButton.setToolTipText("Research");
        researchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panelSwitcher.show(displayPanel, "Research Information");
            }
        });
        
        editButton.setToolTipText("Edit Information");
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                LoginForm.systemFrame.switchToEditMode();
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
        
        emergencyContact = new DisplayField(profileInformation.get("emergency_contact"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeeStatus = new DisplayField(profileInformation.get("employee_status"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeePosition = new DisplayField(profileInformation.get("employee_position"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeeDepartment = new DisplayField(profileInformation.get("employee_department"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        lastName = new DisplayField(profileInformation.get("last_name"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        firstName = new DisplayField(profileInformation.get("first_name"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        middleName = new DisplayField(profileInformation.get("middle_name"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeeGender = new DisplayField(profileInformation.get("gender"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        civilStatus = new DisplayField(profileInformation.get("civil_status"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        birthDay = new DisplayField(profileInformation.get("birthday"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        birthPlace = new DisplayField(profileInformation.get("birth_place"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        emailAddress = new DisplayField(profileInformation.get("email"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeeReligion = new DisplayField(profileInformation.get("religion"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        employeeCitizenship = new DisplayField(profileInformation.get("citizenship"), FrameWorkUtils.getSecondaryColor(), 13f, false);        
        emergencyName = new DisplayField(profileInformation.get("emergency_name"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        emergencyRelationship = new DisplayField(profileInformation.get("emergency_relationship"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        emergencyContact = new DisplayField(profileInformation.get("emergency_contact"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        
        spouseName = new WrapField(profileInformation.get("spouse_name"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        presentAddress = new WrapField(profileInformation.get("present_address"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        presentContact = new WrapField(profileInformation.get("present_contact"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        residenceAddress = new WrapField(profileInformation.get("residence_address"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        residenceContact = new WrapField(profileInformation.get("residence_contact"), FrameWorkUtils.getSecondaryColor(), 13f, false);
        
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
        
        JPanel childrenPanel = new JPanel();
        childrenPanel.setOpaque(false);
        childrenPanel.setLayout(new BoxLayout(childrenPanel, BoxLayout.Y_AXIS));
        
        if(childEntries.size() > 0){
            for(int i = 0; i < childEntries.size(); i++){
                childrenPanel.add(new ChildEntry(childEntries.get(i).get(0), childEntries.get(i).get(1), childEntries.get(i).get(2), childEntries.get(i).get(3)));
                childrenPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            childrenPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
    
        basicInformationLayout.row().left().add(childrenPanel);
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
    
    private JPanel experienceInformation(){
        JPanel mainContainer = new JPanel();
        mainContainer.setOpaque(false);
        
       
        
                
        DesignGridLayout experienceInformationLayout = new DesignGridLayout(mainContainer);
        
       
        
        experienceInformationLayout.labelAlignment(LabelAlignment.LEFT);
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Education</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        JPanel educationPanel = new JPanel();
        educationPanel.setOpaque(false);
        educationPanel.setLayout(new BoxLayout(educationPanel, BoxLayout.Y_AXIS));
        
        if(educationEntries.size() > 0){
            for(int i = 0; i < educationEntries.size(); i++){
                educationPanel.add(new EducationEntry(educationEntries.get(i)));
                educationPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            educationPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(educationPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Work Experience</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel workPanel = new JPanel();
        workPanel.setOpaque(false);
        workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
        
        if(workEntries.size() > 0){
            for(int i = 0; i < workEntries.size(); i++){
                workPanel.add(new WorkEntry(workEntries.get(i)));
                workPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            workPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
       
        experienceInformationLayout.row().left().add(workPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Administrative Appointment</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel adminAppointmentPanel = new JPanel();
        adminAppointmentPanel.setOpaque(false);
        adminAppointmentPanel.setLayout(new BoxLayout(adminAppointmentPanel, BoxLayout.Y_AXIS));
        
        if(adminAppointmentEntries.size() > 0){
            for(int i = 0; i < adminAppointmentEntries.size(); i++){
                adminAppointmentPanel.add(new AdminAppointmentEntry(adminAppointmentEntries.get(i)));
                adminAppointmentPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            adminAppointmentPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(adminAppointmentPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Licensure</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel licensurePanel = new JPanel();
        licensurePanel.setOpaque(false);
        licensurePanel.setLayout(new BoxLayout(licensurePanel, BoxLayout.Y_AXIS));
        
        if(licensureEntries.size() > 0){
            for(int i = 0; i < licensureEntries.size(); i++){
                licensurePanel.add(new LicensureEntry(licensureEntries.get(i)));
                licensurePanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            licensurePanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(licensurePanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Consultancy Work</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel consultancyPanel = new JPanel();
        consultancyPanel.setOpaque(false);
        consultancyPanel.setLayout(new BoxLayout(consultancyPanel, BoxLayout.Y_AXIS));
        
        if(consultancyEntries.size() > 0){
            for(int i = 0; i < consultancyEntries.size(); i++){
                consultancyPanel.add(new ConsultancyEntry(consultancyEntries.get(i)));
                consultancyPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            consultancyPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(consultancyPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        
        experienceInformationLayout.emptyRow();
        
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Community Work</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel communityWorkPanel = new JPanel();
        communityWorkPanel.setOpaque(false);
        communityWorkPanel.setLayout(new BoxLayout(communityWorkPanel, BoxLayout.Y_AXIS));
        
        if(communityWorkEntries.size() > 0){
            for(int i = 0; i < communityWorkEntries.size(); i++){
                communityWorkPanel.add(new CommunityWorkEntry(communityWorkEntries.get(i)));
                communityWorkPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            communityWorkPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(communityWorkPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Professional Organizations</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel professionalOrganizationPanel = new JPanel();
        professionalOrganizationPanel.setOpaque(false);
        professionalOrganizationPanel.setLayout(new BoxLayout(professionalOrganizationPanel, BoxLayout.Y_AXIS));
        
        if(professionalOrganizationEntries.size() > 0){
            for(int i = 0; i < professionalOrganizationEntries.size(); i++){
                professionalOrganizationPanel.add(new ProfessionalOrganizationEntry(professionalOrganizationEntries.get(i)));
                professionalOrganizationPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            professionalOrganizationPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(professionalOrganizationPanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        
        experienceInformationLayout.row().left().add(new FormLabel("<HTML><B>Event Attendance</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel eventAttendancePanel = new JPanel();
        eventAttendancePanel.setOpaque(false);
        eventAttendancePanel.setLayout(new BoxLayout(eventAttendancePanel, BoxLayout.Y_AXIS));
        
        if(eventAttendanceEntries.size() > 0){
            for(int i = 0; i < eventAttendanceEntries.size(); i++){
                eventAttendancePanel.add(new EventAttendanceEntry(eventAttendanceEntries.get(i)));
                eventAttendancePanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            eventAttendancePanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        experienceInformationLayout.row().left().add(eventAttendancePanel);
        experienceInformationLayout.row().left().add(new JSeparator());
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        experienceInformationLayout.emptyRow();
        return mainContainer;
    }
    
    private JPanel researchInformation(){
        JPanel mainContainer = new JPanel();
        mainContainer.setOpaque(false);
          
        DesignGridLayout researchInformationLayout = new DesignGridLayout(mainContainer);

        researchInformationLayout.labelAlignment(LabelAlignment.LEFT);
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.row().left().add(new FormLabel("<HTML><B>Research</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        JPanel researchPanel = new JPanel();
        researchPanel.setOpaque(false);
        researchPanel.setLayout(new BoxLayout(researchPanel, BoxLayout.Y_AXIS));
        
        if(researchEntries.size() > 0){
            for(int i = 0; i < researchEntries.size(); i++){
                researchPanel.add(new ResearchEntry(researchEntries.get(i)));
                researchPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            researchPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
        
        researchInformationLayout.row().left().add(researchPanel);
        researchInformationLayout.row().left().add(new JSeparator());
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.row().left().add(new FormLabel("<HTML><B>Research Fellowship Awards</B></HTML>", FrameWorkUtils.getSecondaryColor(), 16f, SwingConstants.LEFT, 0));
        
        JPanel researchFellowshipAwardsPanel = new JPanel();
        researchFellowshipAwardsPanel.setOpaque(false);
        researchFellowshipAwardsPanel.setLayout(new BoxLayout(researchFellowshipAwardsPanel, BoxLayout.Y_AXIS));
        
        if(researchFellowshipAwardsEntries.size() > 0){
            for(int i = 0; i < researchFellowshipAwardsEntries.size(); i++){
                researchFellowshipAwardsPanel.add(new ResearchFellowshipAwardEntry(researchFellowshipAwardsEntries.get(i)));
                researchFellowshipAwardsPanel.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            researchFellowshipAwardsPanel.add(new FormLabel("No Records To Show", FrameWorkUtils.getSecondaryColor(), 15f));
        }
       
        researchInformationLayout.row().left().add(researchFellowshipAwardsPanel);
        researchInformationLayout.row().left().add(new JSeparator());
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        researchInformationLayout.emptyRow();
        
        return mainContainer;
    }
    
    private void refreshProfile(){
        Thread refreshProfile = new Thread(new Runnable() {
            @Override
            public void run() {
                basicButton.setEnabled(false);
                workButton.setEnabled(false);
                researchButton.setEnabled(false);
                editButton.setEnabled(false);
                panelSwitcher.show(displayPanel, "Load");
                displayPanel.remove(new CustomScrollPane(basicInformation()));
                displayPanel.remove(new CustomScrollPane(experienceInformation()));
                displayPanel.remove(new CustomScrollPane(researchInformation()));
                profileInformation = systemSession.getProfileInformation();
                childEntries = systemSession.getChildren();
                educationEntries = systemSession.getEducation();
                workEntries = systemSession.getWork();
                adminAppointmentEntries = systemSession.getAdminAppointment();
                licensureEntries = systemSession.getLicensure();
                consultancyEntries = systemSession.getConsultancy();
                communityWorkEntries = systemSession.getCommunityWork();
                professionalOrganizationEntries = systemSession.getProfessionalOrganization();
                eventAttendanceEntries = systemSession.getEventAttendance();
                researchEntries = systemSession.getResearch();
                researchFellowshipAwardsEntries = systemSession.getResearchFellowshipAwards();
                displayPanel.add(new CustomScrollPane(basicInformation()), "Basic Information");
                displayPanel.add(new CustomScrollPane(experienceInformation()), "Experience Information");
                displayPanel.add(new CustomScrollPane(researchInformation()), "Research Information");
                displayPanel.revalidate();
                ClientUtils.wait(2);
                panelSwitcher.show(displayPanel, "Basic Information");
                basicButton.setEnabled(true);
                workButton.setEnabled(true);
                researchButton.setEnabled(true);
                editButton.setEnabled(true);
            }
        });
        
        refreshProfile.start();
    }
    
}
