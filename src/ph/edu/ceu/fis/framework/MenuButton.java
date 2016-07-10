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
import java.awt.event.*;

public class MenuButton extends JButton{
    
    public MenuButton(String buttonText, float fontSize, ImageIcon buttonIcon, Color buttonBackground){
        
        setText(buttonText);
        setIcon(buttonIcon);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(buttonBackground);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setBackground(buttonBackground);
        setFocusPainted(false);
        setBorderPainted(false);
        
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
		setForeground(FrameWorkUtils.getSecondaryColor());
                setBackground(buttonBackground.brighter());                           
            }

            @Override
            public void mouseExited(MouseEvent e){
                setForeground(buttonBackground);
                setBackground(buttonBackground);               
            }
        });
    }
    
    public MenuButton(String buttonText, float fontSize, ImageIcon buttonIcon, ImageIcon hoverIcon, Color buttonBackground){
        
        setText(buttonText);
        setIcon(buttonIcon);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
        setForeground(buttonBackground);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setBackground(buttonBackground);
        setFocusPainted(false);
        setBorderPainted(false);
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setIcon(hoverIcon);
		setForeground(FrameWorkUtils.getSecondaryColor());
                setBackground(buttonBackground.brighter()); 
            }

            @Override
            public void mouseExited(MouseEvent e){
                setIcon(buttonIcon);
                setForeground(buttonBackground);
                setBackground(buttonBackground);     
            }
        });
    }
    
    public void setIcon(ImageIcon buttonIcon, Color buttonBackground){
        super.setIcon(buttonIcon);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
		setForeground(FrameWorkUtils.getSecondaryColor());
                setBackground(buttonBackground.brighter()); 
            }

            @Override
            public void mouseExited(MouseEvent e){
                setForeground(buttonBackground);
                setBackground(buttonBackground);     
            }
        });
    }
    public void refresh(ImageIcon buttonIcon, ImageIcon hoverIcon, Color buttonBackground){
        setIcon(buttonIcon);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setIcon(hoverIcon);
		setForeground(FrameWorkUtils.getSecondaryColor());
                setBackground(buttonBackground.brighter()); 
            }

            @Override
            public void mouseExited(MouseEvent e){
                setIcon(buttonIcon);
                setForeground(buttonBackground);
                setBackground(buttonBackground);     
            }
        });
    
    }

}
