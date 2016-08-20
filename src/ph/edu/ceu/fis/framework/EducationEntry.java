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
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.apache.commons.lang3.StringUtils;
import ph.edu.ceu.fis.data.Children;
import ph.edu.ceu.fis.data.Education;
import ph.edu.ceu.fis.utils.ClientUtils;

public class EducationEntry extends JPanel{

          
    private String educationID;

    private FormLabel degreeLabel,
                      institutionLabel,
                      honorsLabel,
                      startDateLabel,
                      endDateLabel,
                      unitsEarnedLabel,
                      scholarshipLabel,
                      stateUnitsLabel;
    FormField degreeField,
                  institutionField,
                  honorsField,
                  unitsEarnedField,
                  scholarshipField,
                  stateUnitsField;
    
    FormComboBox startDateMonth,
                     endDateMonth,
                     startDateYear,
                     endDateYear;
        
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());

   
    public EducationEntry(HashMap<String, String> educationInfo){
        super(new BorderLayout());
        this.educationID = educationInfo.get("id");
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        degreeLabel = new FormLabel(educationInfo.get("degree"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        institutionLabel = new FormLabel(educationInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        honorsLabel = new FormLabel(educationInfo.get("honors"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        startDateLabel = new FormLabel(educationInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        endDateLabel = new FormLabel(educationInfo.get("end_date"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        unitsEarnedLabel = new FormLabel(educationInfo.get("units_earned"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        scholarshipLabel = new FormLabel(educationInfo.get("scholarship"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        stateUnitsLabel = new FormLabel(educationInfo.get("state_units"), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(degreeLabel);
        infoPanel.add(institutionLabel);
  
        
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(viewButton, viewButton.getX()-50, viewButton.getY());
            }
        });
        
        
        add(infoPanel, BorderLayout.CENTER);
        add(viewButton, BorderLayout.EAST);
    }
    
    public EducationEntry(HashMap<String, String> educationInfo, boolean inEditMode){
        super(new BorderLayout());
        this.educationID = educationInfo.get("id");
        
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        MenuButton deleteButton = new MenuButton("Delete", 13f, new ImageIcon("images/trash.png"), FrameWorkUtils.getPrimaryColor());
        MenuButton saveButton = new MenuButton("Save", 13f, new ImageIcon("images/save.png"), FrameWorkUtils.getPrimaryColor());

        degreeField = new FormField(educationInfo.get("degree"), FrameWorkUtils.getSecondaryColor(), 15f);
        institutionField = new FormField(educationInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 15f);
        honorsField = new FormField(educationInfo.get("honors"), FrameWorkUtils.getSecondaryColor(), 15f);
        unitsEarnedField = new FormField(educationInfo.get("units_earned"), FrameWorkUtils.getSecondaryColor(), 15f);
        scholarshipField = new FormField(educationInfo.get("scholarship"), FrameWorkUtils.getSecondaryColor(), 15f);
        stateUnitsField = new FormField(educationInfo.get("state_units"), FrameWorkUtils.getSecondaryColor(), 15f);
        
        startDateMonth = new FormComboBox(new Object[]{"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        endDateMonth = new FormComboBox(new Object[]{"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        startDateYear = new FormComboBox(ClientUtils.generateYears());
        endDateYear = new FormComboBox(ClientUtils.generateYears());
        
        degreeField.setEnabled(false);
        institutionField.setEnabled(false);
        startDateMonth.setEnabled(false);
        startDateYear.setEnabled(false);
        startDateMonth.setSelectedItem(educationInfo.get("start_date").split(" ")[0]);
        startDateYear.setSelectedItem(educationInfo.get("start_date").split(" ")[1]);
        
        if(!StringUtils.isBlank(educationInfo.get("end_date").replaceAll("\\s+",""))){
            endDateMonth.setSelectedItem(educationInfo.get("end_date").split(" ")[0]);
            endDateYear.setSelectedItem(educationInfo.get("end_date").split(" ")[1]);
        }
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> updateInfo = new HashMap<String, String>();                
                    updateInfo.put("education_id", educationID);
                    updateInfo.put("honors", honorsField.getText());
                    updateInfo.put("end_date", endDateMonth.getSelectedItem().toString() + " " + endDateYear.getSelectedItem().toString());
                    updateInfo.put("units_earned", unitsEarnedField.getText());
                    updateInfo.put("scholarship", scholarshipField.getText());
                    updateInfo.put("state_units", stateUnitsField.getText());
                confirmDelete(deleteButton, deleteButton.getX()-50, deleteButton.getY(), updateInfo);
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(ClientUtils.numericVerify(new String[]{unitsEarnedField.getText(), stateUnitsField.getText()})){
                    HashMap<String, String> updateInfo = new HashMap<String, String>();                
                    updateInfo.put("education_id", educationID);
                    updateInfo.put("honors", honorsField.getText());
                    updateInfo.put("end_date", endDateMonth.getSelectedItem().toString() + " " + endDateYear.getSelectedItem().toString());
                    updateInfo.put("units_earned", unitsEarnedField.getText());
                    updateInfo.put("scholarship", scholarshipField.getText());
                    updateInfo.put("state_units", stateUnitsField.getText());
                    Education.updateEducation(updateInfo);
                }else{
                    throw new UnsupportedOperationException();
                }
                //if(ClientUtils.numericVerify(new String[]{unitsEarnedField.getText(), stateUnitsField.getText()})){
                    /*HashMap<String, String> updateInfo = new HashMap<String, String>(){{                   
                    put("education_id", educationID);
                    put("honors", honorsField.getText());
                    put("end_date", endDateMonth.getSelectedItem().toString() + " " + endDateYear.getSelectedItem().toString());
                    put("units_earned", unitsEarnedField.getText());
                    put("scholarship", scholarshipField.getText());
                    put("state_units", stateUnitsField.getText());}};
                    Education.updateEducation(updateInfo);*/
                //}else{
                    
                //}
                
            }
        });
        
        DesignGridLayout informationLayout = new DesignGridLayout(this);
        informationLayout.row().right().add(deleteButton).add(saveButton);
        informationLayout.row().grid(new FormLabel("Degree: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(degreeField);
        informationLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(institutionField);
        informationLayout.row().grid(new FormLabel("Honors: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(honorsField);
        informationLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(startDateMonth).add(startDateYear).grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(endDateMonth).add(endDateYear);
        informationLayout.row().grid(new FormLabel("Scholarship/s: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(scholarshipField);
        informationLayout.row().grid(new FormLabel("Units: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(unitsEarnedField).grid(new FormLabel("State ACRED Units: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(stateUnitsField);

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
        mainContainerLayout.row().grid(new FormLabel("Degree: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(degreeLabel.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(institutionLabel.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Honors: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(honorsLabel);
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(startDateLabel);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(endDateLabel);
        mainContainerLayout.row().grid(new FormLabel("Units Earned: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(unitsEarnedLabel);  
        mainContainerLayout.row().grid(new FormLabel("Scholarship: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(scholarshipLabel);  
        mainContainerLayout.row().grid(new FormLabel("Stated ACRED Units: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(stateUnitsLabel);  
        
        
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
    }
    
    private void confirmDelete(Component componentInvoker, int x, int y, HashMap<String, String> educationInfo){
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
         FormButton yesButton = new FormButton("Yes", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f),
                    noButton = new FormButton("No", FrameWorkUtils.getPrimaryColor(), FrameWorkUtils.getSecondaryColor(), 15f);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Education.deleteEducation(educationInfo.get("id"));
                setVisible(false);
                detailsPopup.setVisible(false);
            }
        });
        
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsPopup.setVisible(false);
            }
        });
        DesignGridLayout mainContainerLayout = new DesignGridLayout(mainContainer);
        degreeLabel = new FormLabel(educationInfo.get("degree"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        institutionLabel = new FormLabel(educationInfo.get("institution"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        honorsLabel = new FormLabel(educationInfo.get("honors"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        startDateLabel = new FormLabel(educationInfo.get("start_date"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        endDateLabel = new FormLabel(educationInfo.get("end_date"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        unitsEarnedLabel = new FormLabel(educationInfo.get("units_earned"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        scholarshipLabel = new FormLabel(educationInfo.get("scholarship"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        stateUnitsLabel = new FormLabel(educationInfo.get("state_units"), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0);
        
        mainContainerLayout.row().left().add(new FormLabel("Are you sure you want delete this record?", FrameWorkUtils.getSecondaryColor(), 18f));
        mainContainerLayout.row().grid(new FormLabel("Degree: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(degreeLabel.getText(), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Institution: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(institutionLabel.getText(), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));    
        mainContainerLayout.row().grid(new FormLabel("Honors: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(honorsLabel);
        mainContainerLayout.row().grid(new FormLabel("Start Date: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(startDateLabel);
        mainContainerLayout.row().grid(new FormLabel("End Date: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(endDateLabel);
        mainContainerLayout.row().grid(new FormLabel("Units Earned: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(unitsEarnedLabel);  
        mainContainerLayout.row().grid(new FormLabel("Scholarship: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(scholarshipLabel);  
        mainContainerLayout.row().grid(new FormLabel("Stated ACRED Units: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(stateUnitsLabel);  
        
        mainContainerLayout.row().grid().add(yesButton).add(noButton);
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
    }
}
