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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import ph.edu.ceu.fis.data.Children;


public class ChildEntry extends JPanel{
    
    
    private WrapField childName;
    private FormLabel childGender,
                      birthOrder;
                      
    
    private MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), FrameWorkUtils.getPrimaryColor().brighter());
    

    public ChildEntry(String childID, String childName, String childGender, String birthOrder){
        super(new BorderLayout());
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        JPanel infoPanel = new JPanel(new GridLayout(1, 1));
        infoPanel.setOpaque(false);
        
        this.childName = new WrapField(childName, FrameWorkUtils.getSecondaryColor(), 13f, false);
        this.childGender = new FormLabel(childGender, FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0);
        this.birthOrder = new FormLabel(birthOrder, FrameWorkUtils.getAccentColor(), 20f, SwingConstants.LEFT, 0);

        infoPanel.add(this.childName);
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(viewButton, viewButton.getX()-50, viewButton.getY());
            }
        });
      
        add(infoPanel, BorderLayout.WEST);
        add(viewButton, BorderLayout.EAST);
    }
    
    public ChildEntry(String childID, String childName, String childGender, String birthOrder, boolean inEditMode){
        super(new BorderLayout());
        setOpaque(true);
        setBackground(FrameWorkUtils.getPrimaryColor());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(5, 5));
        
        MenuButton deleteButton = new MenuButton("Delete", 13f, new ImageIcon("images/trash.png"), FrameWorkUtils.getPrimaryColor());
        MenuButton saveButton = new MenuButton("Save", 13f, new ImageIcon("images/save.png"), FrameWorkUtils.getPrimaryColor());

        
        FormField childNameField = new FormField(childName, FrameWorkUtils.getSecondaryColor(), 13f);
        FormComboBox childGenderField = new FormComboBox(new Object[]{"", "Male", "Female"});
        FormComboBox birthOrderField = new FormComboBox(new Object[]{"", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th"});
        childGenderField.setSelectedItem(childGender);
        birthOrderField.setSelectedItem(birthOrder);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDelete(deleteButton, deleteButton.getX()-50, deleteButton.getY(), childID, childName, childGender, birthOrder);
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!childNameField.getText().trim().equals("") || !childNameField.getText().isEmpty()) && !childGenderField.getSelectedItem().toString().equals("") && !birthOrderField.getSelectedItem().toString().equals("")){
                    Children.updateChild(childID, childNameField.getText().trim(), childGenderField.getSelectedItem().toString(), birthOrderField.getSelectedItem().toString());
                }else{
                    MessageDialog.showMessage("Problem Encountered", "Please make sure that all fields have been accomplished.");
                }         
            }
        });
        DesignGridLayout informationLayout = new DesignGridLayout(this);
        informationLayout.row().right().add(deleteButton).add(saveButton);
        informationLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(childNameField);
        informationLayout.row().grid(new FormLabel("Gender: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(childGenderField);
        informationLayout.row().grid(new FormLabel("Birth Order: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(birthOrderField);
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
        
        
        mainContainerLayout.row().bar().left(birthOrder);
        mainContainerLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(childName.getText(), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Gender: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(childGender);

        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
    }
    
    private void confirmDelete(Component componentInvoker, int x, int y, String childID, String childName, String childGender, String childBirthOrder){
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
                Children.deleteChild(childID);
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
        
        mainContainerLayout.row().left().add(new FormLabel("Are you sure you want delete this record?", FrameWorkUtils.getSecondaryColor(), 18f));
        mainContainerLayout.row().grid(new FormLabel("Name: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(childName, FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Gender: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(childGender, FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid(new FormLabel("Birth Order: ", FrameWorkUtils.getSecondaryColor(), 15f)).add(new FormLabel(childBirthOrder, FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 0));
        mainContainerLayout.row().grid().add(yesButton).add(noButton);
        detailsPopup.add(mainContainer);
        detailsPopup.show(componentInvoker, x, y);
       
    }
    
    
    
}
