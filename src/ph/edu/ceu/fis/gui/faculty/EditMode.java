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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.lang3.StringUtils;
import ph.edu.ceu.fis.data.BasicInformation;
import ph.edu.ceu.fis.data.Children;
import ph.edu.ceu.fis.data.Education;
import ph.edu.ceu.fis.data.Session;
import ph.edu.ceu.fis.framework.*;
import ph.edu.ceu.fis.gui.LoginForm;
import ph.edu.ceu.fis.utils.ClientUtils;
import ph.edu.ceu.fis.utils.ErrorReport;

public class EditMode extends JPanel{
    
    private Session systemSession;
    
    private CardLayout panelSwitcher = new CardLayout();
                    
    private JPanel mainContainer = new JPanel(panelSwitcher);
    
    private MenuButton previousButton = new MenuButton("", 0f, new ImageIcon("images/prev.png"), FrameWorkUtils.getPrimaryColor()),
                       nextButton = new MenuButton("", 0f, new ImageIcon("images/next.png"), FrameWorkUtils.getPrimaryColor());
    
    private FormField workPosition,
                      workDepartment,
                      lastName,
                      firstName,
                      middleName,
                      birthPlace,
                      emailAddress,
                      religion,
                      citizenship,
                      presentAddress,
                      presentContact,
                      residenceAddress,
                      residenceContact,
                      spouseName,
                      emergencyName,
                      emergencyContact,
                      emergencyRelationship;
    
    private FormComboBox userType,
                         employeeStatus,
                         gender,
                         civilStatus;
    
    
    private FormTextArea specializationFields;             
                      
    public EditMode(Session systemSession){
        this.systemSession = systemSession;
        initComponents();
    }
    
    private void initComponents(){
        setLayout(new BorderLayout(5, 5));
        setOpaque(false);
        FormButton returnButton = new FormButton("Return", 25f);
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);
        header.add(new FormLabel("Information Edit", FrameWorkUtils.getPrimaryColor(), 25f));
        header.add(returnButton);
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.systemFrame.switchToFacultyMode();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSwitcher.next(mainContainer);
            }
        });
        
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSwitcher.previous(mainContainer);
            }
        });
        mainContainer.setOpaque(false);
        mainContainer.add(basicInformation(systemSession.getProfileInformation()), "Basic Information");
        mainContainer.add(employeeChildren(systemSession.getChildren()), "Children");
        mainContainer.add(employeeEducation(systemSession.getEducation()), "Education");
        add(header, BorderLayout.NORTH);
        add(previousButton, BorderLayout.WEST);
        add(mainContainer, BorderLayout.CENTER);
        add(nextButton, BorderLayout.EAST);
        
        
        
        
    }
    
    
    private CustomScrollPane basicInformation(HashMap<String, String> profileInformation){
        JPanel basicInformation = new JPanel();
        basicInformation.setOpaque(false);
        CustomScrollPane basicInformationContainer = new CustomScrollPane(basicInformation);
        basicInformationContainer.getViewport().setBackground(Color.WHITE);
        basicInformationContainer.setOpaque(false);
        basicInformationContainer.setBorder(null);
        
        MenuButton saveButton = new MenuButton("Save", 15f, new ImageIcon("images/save.png"), FrameWorkUtils.getPrimaryColor());
        
        userType = new FormComboBox(new Object[]{"Faculty", "Registrar", "Administrator"});
        employeeStatus = new FormComboBox(new Object[]{"Full-Time", "Part-Time"});
        employeeStatus.setSelectedItem(profileInformation.get("employee_status"));
        gender = new FormComboBox(new Object[]{"Male", "Female"});
        civilStatus = new FormComboBox(new Object[]{"Single", "Married", "Widow/Widower", "Annulled/Divorced"});
        
        workPosition = new FormField(profileInformation.get("employee_position"), FrameWorkUtils.getSecondaryColor(), 14f);
        workDepartment = new FormField(profileInformation.get("employee_department"), FrameWorkUtils.getSecondaryColor(), 14f);
        lastName = new FormField(profileInformation.get("last_name"), FrameWorkUtils.getSecondaryColor(), 14f);
        firstName = new FormField(profileInformation.get("first_name"), FrameWorkUtils.getSecondaryColor(), 14f);
        middleName = new FormField(profileInformation.get("middle_name"), FrameWorkUtils.getSecondaryColor(), 14f);
        birthPlace = new FormField(profileInformation.get("birth_place"), FrameWorkUtils.getSecondaryColor(), 14f);
        emailAddress = new FormField(profileInformation.get("email"), FrameWorkUtils.getSecondaryColor(), 14f);
        religion = new FormField(profileInformation.get("religion"), FrameWorkUtils.getSecondaryColor(), 14f);
        citizenship = new FormField(profileInformation.get("citizenship"), FrameWorkUtils.getSecondaryColor(), 14f);
        presentAddress = new FormField(profileInformation.get("present_address"), FrameWorkUtils.getSecondaryColor(), 14f);
        presentContact = new FormField(profileInformation.get("present_contact"), FrameWorkUtils.getSecondaryColor(), 14f);
        residenceAddress = new FormField(profileInformation.get("residence_address"), FrameWorkUtils.getSecondaryColor(), 14f);
        residenceContact = new FormField(profileInformation.get("residence_contact"), FrameWorkUtils.getSecondaryColor(), 14f);
        spouseName = new FormField(profileInformation.get("spouse_name"), FrameWorkUtils.getSecondaryColor(), 14f);
        emergencyName = new FormField(profileInformation.get("emergency_name"), FrameWorkUtils.getSecondaryColor(), 14f);
        emergencyContact = new FormField(profileInformation.get("emergency_contact"), FrameWorkUtils.getSecondaryColor(), 14f);
        emergencyRelationship = new FormField(profileInformation.get("emergency_relationship"), FrameWorkUtils.getSecondaryColor(), 14f);
        
        specializationFields = new FormTextArea(profileInformation.get("specialization_fields"), 14f);
        CustomScrollPane specializationPane = new CustomScrollPane(specializationFields);
        specializationPane.setPreferredSize(new Dimension(700, 100));
        
        workPosition.setEnabled(false);
        workDepartment.setEnabled(false);
        firstName.setEnabled(false);
        birthPlace.setEnabled(false);
        emailAddress.setEnabled(false);
        residenceAddress.setEnabled(false);
        residenceContact.setEnabled(false);
        gender.setEnabled(false);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
            if(!lastName.getText().equals("") || !lastName.getText().isEmpty()){
                if(!middleName.getText().equals("") || !middleName.getText().isEmpty()){
                    if(civilStatus.getSelectedItem().equals("Single")){
                        HashMap<String, String> updateInformation = new HashMap<String, String>();
                        updateInformation.put("user_id", systemSession.getUserID());
                        updateInformation.put("last_name", lastName.getText());
                        updateInformation.put("middle_name", middleName.getText());
                        updateInformation.put("civil_status", civilStatus.getSelectedItem().toString());
                        updateInformation.put("employee_status", employeeStatus.getSelectedItem().toString());
                        updateInformation.put("employee_position", workPosition.getText());
                        updateInformation.put("employee_department", workDepartment.getText());
                        updateInformation.put("religion", religion.getText());
                        updateInformation.put("present_address", presentAddress.getText());
                        updateInformation.put("present_contact", presentContact.getText());
                        updateInformation.put("spouse_name", spouseName.getText());
                        updateInformation.put("emergency_name", emergencyName.getText());
                        updateInformation.put("emergency_relationship", emergencyRelationship.getText());
                        updateInformation.put("emergency_contact", emergencyContact.getText());
                        updateInformation.put("specialization_fields", specializationFields.getText());
                        updateProfile(updateInformation);
                    }else if(!civilStatus.getSelectedItem().equals("Single")){
                        if(!spouseName.getText().equals("") || !spouseName.getText().isEmpty()){
                            HashMap<String, String> updateInformation = new HashMap<String, String>();
                            updateInformation.put("user_id", systemSession.getUserID());
                            updateInformation.put("last_name", lastName.getText());
                            updateInformation.put("middle_name", middleName.getText());
                            updateInformation.put("civil_status", civilStatus.getSelectedItem().toString());
                            updateInformation.put("employee_status", employeeStatus.getSelectedItem().toString());
                            updateInformation.put("employee_position", workPosition.getText());
                            updateInformation.put("employee_department", workDepartment.getText());
                            updateInformation.put("religion", religion.getText());
                            updateInformation.put("present_address", presentAddress.getText());
                            updateInformation.put("present_contact", presentContact.getText());
                            updateInformation.put("spouse_name", spouseName.getText());
                            updateInformation.put("emergency_name", emergencyName.getText());
                            updateInformation.put("emergency_relationship", emergencyRelationship.getText());
                            updateInformation.put("emergency_contact", emergencyContact.getText());
                            updateInformation.put("specialization_fields", specializationFields.getText());
                            updateProfile(updateInformation);
                        }else{
                            MessageDialog.showMessage("Update Error", ErrorReport.getErrorReport("BI-00010"));
                        }
                    }
                }else{
                    MessageDialog.showMessage("Update Error", ErrorReport.getErrorReport("BI-00007"));
                }
           
            }else{
                MessageDialog.showMessage("Update Error", ErrorReport.getErrorReport("BI-00005"));
            }
            
                                                            
            }
        });
        DesignGridLayout basicInformationLayout = new DesignGridLayout(basicInformation);
        
        
        basicInformationLayout.row().left().add(new FormLabel("Personal Details", FrameWorkUtils.getPrimaryColor(), 25f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().grid(new JLabel(new ImageIcon("images/idea.png"))).add(new WrapLabel("To further ensure the accuracy of the data below, please provide only factual data that can be verified by the administrators of the organization and official documents. Failure to provide truthful information will result to your account being flagged by administrators which will then be escalated to immediate supervisors.", FrameWorkUtils.getPrimaryColor(), 13f));        
        
        basicInformationLayout.emptyRow();
        
        basicInformationLayout.row().left().add(new FormLabel("Basic Information", FrameWorkUtils.getPrimaryColor(), 20f, SwingConstants.LEFT, 0));
        
        basicInformationLayout.row().grid(new FormLabel("Status: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(employeeStatus, 1)
                                    .grid(new FormLabel("Position: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(workPosition, 1)
                                    .grid(new FormLabel("Department: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(workDepartment, 1);
        
        basicInformationLayout.row().grid(new FormLabel("Last Name: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(lastName, 1)
                                    .grid(new FormLabel("First Name: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(firstName, 1)
                                    .grid(new FormLabel("Middle Name: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(middleName, 1);
        
        basicInformationLayout.row().grid(new FormLabel("E-Mail: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(emailAddress, 1)
                                    .grid(new FormLabel("Gender: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(gender, 1)
                                    .grid(new FormLabel("Citizenship: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(citizenship, 1);
        
        basicInformationLayout.row().grid(new FormLabel("Civil Status: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(civilStatus, 1)
                                    .grid(new FormLabel("Birthplace: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(birthPlace, 1)
                                    .grid(new FormLabel("Religion: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(religion, 1);
        
        basicInformationLayout.row().grid(new FormLabel("Present Address", FrameWorkUtils.getPrimaryColor(), 14f)).add(presentAddress);
        
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(presentContact);
        
        basicInformationLayout.row().grid(new FormLabel("Residence Address", FrameWorkUtils.getPrimaryColor(), 14f)).add(residenceAddress);
        
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(residenceContact);
        
        basicInformationLayout.row().grid(new FormLabel("Spouse: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(spouseName);
        
        basicInformationLayout.emptyRow();
        
        basicInformationLayout.row().left().add(new FormLabel("Contact Incase of Emergency", FrameWorkUtils.getPrimaryColor(), 20f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(emergencyName);
        basicInformationLayout.row().grid(new FormLabel("Relationship: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(emergencyRelationship);
        basicInformationLayout.row().grid(new FormLabel("Contact: ", FrameWorkUtils.getPrimaryColor(), 14f)).add(emergencyContact);
        basicInformationLayout.emptyRow();

        basicInformationLayout.row().left().add(new FormLabel("Field/s of Specialization", FrameWorkUtils.getPrimaryColor(), 20f, SwingConstants.LEFT, 0));
        
        basicInformationLayout.row().left().add(new FormLabel("For each field of specialization, separate each with a comma \",\". Ex. Accounting, Mathematics, Calculus", FrameWorkUtils.getPrimaryColor(), 13f, SwingConstants.LEFT, 0));
        basicInformationLayout.row().left().add(specializationPane);
        basicInformationLayout.row().left().add(saveButton);
        
        return basicInformationContainer;
    }
    
    private JPanel employeeChildren(ArrayList<ArrayList<String>> childEntries){
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5)),
               insertPanel = new JPanel(),
               employeeChildren = new JPanel();
        
        
        employeeChildren.setLayout(new BoxLayout(employeeChildren, BoxLayout.Y_AXIS));
        employeeChildren.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContainer.setOpaque(false);
        insertPanel.setOpaque(false);
        employeeChildren.setOpaque(false);
        if(childEntries.size() > 0){
            for(int i = 0; i < childEntries.size(); i++){
                employeeChildren.add(new ChildEntry(childEntries.get(i).get(0), childEntries.get(i).get(1), childEntries.get(i).get(2), childEntries.get(i).get(3), true));
                employeeChildren.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            employeeChildren.add(new FormLabel("No Records to Show", FrameWorkUtils.getPrimaryColor(), 15f));
        }
        
        CustomScrollPane employeeChildrenContainer = new CustomScrollPane(employeeChildren);
        employeeChildrenContainer.getViewport().setBackground(Color.WHITE);
        employeeChildrenContainer.setOpaque(false);
        employeeChildrenContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        FormField childNameField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f);
        FormComboBox childGenderField = new FormComboBox(new Object[]{"", "Male", "Female"});
        FormComboBox birthOrderField = new FormComboBox(new Object[]{"", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th"});
        
        FormButton addButton = new FormButton("Add", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f),
                   clearButton = new FormButton("Clear", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if((!childNameField.getText().trim().equals("") || !childNameField.getText().isEmpty()) && !childGenderField.getSelectedItem().toString().equals("") && !birthOrderField.getSelectedItem().toString().equals("")){
                    Children.addChild(systemSession.getUserID(), childNameField.getText(), childGenderField.getSelectedItem().toString(), birthOrderField.getSelectedItem().toString());
                    MessageDialog.showMessage("Successfully Added Record", "Record added successfully!");
                    childNameField.setText("");
                    childGenderField.setSelectedIndex(0);
                    birthOrderField.setSelectedIndex(0);
                    employeeChildren.removeAll();
                    ArrayList<ArrayList<String>> childEntries = Children.getChildren(systemSession.getUserID());
                    if(childEntries.size() > 0){
                        for(int i = 0; i < childEntries.size(); i++){
                            employeeChildren.add(new ChildEntry(childEntries.get(i).get(0), childEntries.get(i).get(1), childEntries.get(i).get(2), childEntries.get(i).get(3), true));
                            employeeChildren.add(Box.createRigidArea(new Dimension(5,5)));
                        }
                    }else{
                        employeeChildren.add(new FormLabel("No Records to Show", FrameWorkUtils.getPrimaryColor(), 15f));
                    }
                    employeeChildren.revalidate();
                }else{
                    MessageDialog.showMessage("Problem Encountered", "Please make sure that all fields have been accomplished.");
                }
                
                
            }
        });
        
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                childNameField.setText("");
                childGenderField.setSelectedIndex(0);
                birthOrderField.setSelectedIndex(0);
            }
        });
        DesignGridLayout mainLayout = new DesignGridLayout(insertPanel);


        
        mainLayout.row().left().add(new FormLabel("Children", FrameWorkUtils.getPrimaryColor(), 25f, SwingConstants.LEFT, 0));
        mainLayout.emptyRow();
        mainLayout.row().grid(new FormLabel("*Name: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(childNameField);
        mainLayout.row().grid().add(new FormHint("*Required: Indicate full name of child including middle name. Ex. \"Joshua Dizon Santos\"", Color.RED, 13f));
        mainLayout.row().grid(new FormLabel("*Gender: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(childGenderField);
        mainLayout.row().grid().add(new FormHint("*Required", Color.RED, 13f));
        mainLayout.row().grid(new FormLabel("*Birth Order: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(birthOrderField);
        mainLayout.row().grid().add(new FormHint("*Required", Color.RED, 13f));
        mainLayout.row().grid().add(addButton).add(clearButton);
        mainLayout.emptyRow();
        mainLayout.row().left().add(new FormLabel("Children Records", FrameWorkUtils.getPrimaryColor(), 25f, SwingConstants.LEFT, 0));
        mainLayout.row().left().add(new JSeparator());
        
        mainContainer.add(insertPanel, BorderLayout.NORTH);
        mainContainer.add(employeeChildrenContainer, BorderLayout.CENTER);
        
        
        return mainContainer;
    }
    
    private CustomScrollPane employeeEducation(ArrayList<HashMap<String, String>> educationEntries){
        JPanel mainContainer = new JPanel(new BorderLayout(5, 5)),
               insertPanel = new JPanel(),
               employeeEducation = new JPanel();
        
        
        employeeEducation.setLayout(new BoxLayout(employeeEducation, BoxLayout.Y_AXIS));
        employeeEducation.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContainer.setOpaque(false);
        insertPanel.setOpaque(false);
        employeeEducation.setOpaque(false);
        if(educationEntries.size() > 0){
            for(int i = 0; i < educationEntries.size(); i++){
                employeeEducation.add(new EducationEntry(educationEntries.get(i), true));
                employeeEducation.add(Box.createRigidArea(new Dimension(5,5)));
            }
        }else{
            employeeEducation.add(new FormLabel("No Records to Show", FrameWorkUtils.getPrimaryColor(), 15f));
        }
        
        CustomScrollPane employeeEducationContainer = new CustomScrollPane(employeeEducation);
        employeeEducationContainer.getViewport().setBackground(Color.WHITE);
        employeeEducationContainer.setOpaque(false);
        employeeEducationContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        CustomScrollPane mainScrollPane = new CustomScrollPane(mainContainer);
        mainScrollPane.getViewport().setBackground(Color.WHITE);
        mainScrollPane.setOpaque(false);
        mainScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        FormField degreeField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                  institutionField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                  honorsField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                  unitsEarnedField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                  scholarshipField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f),
                  stateUnitsField = new FormField(FrameWorkUtils.getSecondaryColor(), 15f);
        
        FormComboBox startDateMonth = new FormComboBox(new Object[]{"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}),
                     endDateMonth = new FormComboBox(new Object[]{"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}),
                     startDateYear = new FormComboBox(ClientUtils.generateYears()),
                     endDateYear = new FormComboBox(ClientUtils.generateYears());
        
        FormButton addButton = new FormButton("Add", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f),
                   clearButton = new FormButton("Clear", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f);
        
       addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(ClientUtils.informationVerify(new String[]{degreeField.getText(), institutionField.getText(), startDateMonth.getSelectedItem().toString(), startDateYear.getSelectedItem().toString()}) && ClientUtils.numericVerify(new String[]{unitsEarnedField.getText(), stateUnitsField.getText()})){
                    if((!endDateMonth.getSelectedItem().toString().equals("") && !endDateYear.getSelectedItem().toString().equals("")) || (endDateMonth.getSelectedItem().toString().equals("") && endDateYear.getSelectedItem().toString().equals(""))){
                        HashMap<String, String> educationInfo = new HashMap<String, String>();
                        educationInfo.put("user_id", systemSession.getUserID());
                        educationInfo.put("degree", degreeField.getText());
                        educationInfo.put("institution", institutionField.getText());
                        educationInfo.put("start_date", startDateMonth.getSelectedItem().toString() + " " + startDateYear.getSelectedItem().toString());
                        educationInfo.put("honors", honorsField.getText());
                        educationInfo.put("end_date", endDateMonth.getSelectedItem().toString() + " " + endDateYear.getSelectedItem().toString());
                        educationInfo.put("units_earned", unitsEarnedField.getText());
                        educationInfo.put("scholarship", scholarshipField.getText());
                        educationInfo.put("state_units", stateUnitsField.getText());
                        Education.addEducation(educationInfo);
                        employeeEducation.removeAll();
                        ArrayList<HashMap<String, String>> educationEntries = Education.getEducation(systemSession.getUserID());
                        if(educationEntries.size() > 0){
                            for(int i = 0; i < educationEntries.size(); i++){
                                employeeEducation.add(new EducationEntry(educationEntries.get(i), true));
                                employeeEducation.add(Box.createRigidArea(new Dimension(5,5)));
                            }
                        }else{
                            employeeEducation.add(new FormLabel("No Records to Show", FrameWorkUtils.getPrimaryColor(), 15f));
                        }
                        employeeEducation.revalidate();
                        clearButton.doClick();
                    }else{
                        MessageDialog.showMessage("Update Error", ErrorReport.getErrorReport("EI-00003"));

                    }
                }else{
                    MessageDialog.showMessage("Problem Encountered", "Please make sure that all fields have been accomplished.");
                }
                
                
            }
        });
        
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                degreeField.setText("");
                institutionField.setText("");
                honorsField.setText("");
                unitsEarnedField.setText("");
                scholarshipField.setText("");
                stateUnitsField.setText("");
        
                startDateMonth.setSelectedIndex(0);
                endDateMonth.setSelectedIndex(0);
                startDateYear.setSelectedIndex(0);
                endDateYear.setSelectedIndex(0);
        
            }
        });
        DesignGridLayout mainLayout = new DesignGridLayout(insertPanel);

        
        
        mainLayout.row().left().add(new FormLabel("Education", FrameWorkUtils.getPrimaryColor(), 25f, SwingConstants.LEFT, 0));
        mainLayout.emptyRow();
        mainLayout.row().grid(new FormLabel("*Degree: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(degreeField);
        mainLayout.row().grid().add(new FormHint("*Required: Indicate the academic degree. Ex. \"B.S.\", \"M.A.\", ETC", Color.RED, 13f));
        mainLayout.row().grid(new FormLabel("*Institution: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(institutionField);
        mainLayout.row().grid().add(new FormHint("*Required: Indicate branch if institution has multiple branches or campuses", Color.RED, 13f));
        mainLayout.row().grid(new FormLabel("Honors: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(honorsField);
        mainLayout.row().grid().add(new FormHint("Leave blank if none.", FrameWorkUtils.getPrimaryColor(), 13f));
        mainLayout.row().grid(new FormLabel("*Start Date: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(startDateMonth).add(startDateYear).grid(new FormLabel("End Date: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(endDateMonth).add(endDateYear);
        mainLayout.row().grid().add(new FormHint("*Required: Month Started", Color.RED, 13f)).add(new FormHint("*Required: Year Started", Color.RED, 13f)).grid().add(new FormHint("Leave blank if pursuing degree.", FrameWorkUtils.getPrimaryColor(), 13f)).add(new FormHint("Leave blank if pursuing degree.", FrameWorkUtils.getPrimaryColor(), 13f));
        mainLayout.row().grid(new FormLabel("Scholarship/s: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(scholarshipField);
        mainLayout.row().grid().add(new FormHint("Leave blank if none.", FrameWorkUtils.getPrimaryColor(), 13f));
        mainLayout.row().grid(new FormLabel("Units: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(unitsEarnedField).grid(new FormLabel("State ACRED Units: ", FrameWorkUtils.getPrimaryColor(), 15f)).add(stateUnitsField);
        mainLayout.row().grid().add(new FormHint("Total Units Earned", FrameWorkUtils.getPrimaryColor(), 13f)).grid().add(new FormHint("State Required Units", FrameWorkUtils.getPrimaryColor(), 13f));
        
        mainLayout.row().grid().add(addButton).add(clearButton);
        mainLayout.emptyRow();
        mainLayout.row().left().add(new FormLabel("Education Records", FrameWorkUtils.getPrimaryColor(), 25f, SwingConstants.LEFT, 0));
        mainLayout.row().left().add(new JSeparator());

        mainContainer.add(insertPanel, BorderLayout.NORTH);
        mainContainer.add(employeeEducationContainer, BorderLayout.CENTER);
        
        
        return mainScrollPane;
    }
    

    private void updateProfile(HashMap<String, String> updateInformation){
        BasicInformation.updateProfileInformation(updateInformation);
    }
   
}
