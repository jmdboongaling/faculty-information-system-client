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
import javax.swing.*;

public class FileBrowser extends JFrame{
    JFileChooser fileBrowser = new JFileChooser();
        
    public FileBrowser(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        setLayout(new GridLayout(1, 1, 0, 0));
        setIconImages(new FrameWorkUtils().getAppIcons());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        fileBrowser();
    }
    
    private JFileChooser fileBrowser(){
        getJList(fileBrowser);
        int userChoice = fileBrowser.showOpenDialog(this);
        if(userChoice == JFileChooser.APPROVE_OPTION){
            System.out.println("OK CLICKED");
        }else if(userChoice == JFileChooser.CANCEL_OPTION){
            System.out.println("CANCEL CLICKED");
            System.exit(0);
        }
        return fileBrowser;
    }
    
    public boolean getJList(Container c)
{
    Component[] cmps = c.getComponents();
    for (Component cmp : cmps)
    {
        if (cmp instanceof JList)
        {
            modifyJList((JList)cmp);
            return true;
        }
        if (cmp instanceof Container)
        {
            if(getJList((Container) cmp)) return true;
        }
    }
    return false;
}
private void modifyJList(JList list)
{
    


   
}

private Component getJScrollPane(Component comp) {
    if (comp.getClass() == JScrollPane.class) {
        System.out.println("JSP");
        return comp;
    }
    if (comp instanceof Container) {
        Component[] components = ((Container) comp).getComponents();
        for (int i = 0; i < components.length; i++) {
            Component child = getJScrollPane(components[i]);
            System.out.println(i);
            if (child != null) {
                
                return child;
            }
        }
    }
    return null;
}
    public static final int getComponentIndex(Component component) {
    if (component != null && component.getParent() != null) {
      Container c = component.getParent();
      for (int i = 0; i < c.getComponentCount(); i++) {
        if (c.getComponent(i) == component)
          return i;
      }
    }

    return -1;
  }
    
}
