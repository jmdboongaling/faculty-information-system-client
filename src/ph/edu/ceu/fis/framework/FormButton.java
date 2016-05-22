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

public class FormButton extends JButton{
    
    public FormButton(String buttonText, Color textColor, float textSize){
        super(buttonText);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(textSize));
        setForeground(textColor);
        setBorder(new MatteBorder(0, 0, 1, 0, textColor));
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
                setForeground(Color.WHITE);
            }
        });
    }
    
    public FormButton(String buttonText, Color textColor, Color focusColor, float textSize){
        super(buttonText);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setFont(FrameWorkUtils.getSystemFont().deriveFont(textSize));
        setForeground(textColor);
        setBorder(new MatteBorder(0, 0, 0, 3, textColor));
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBorder(new MatteBorder(0, 0, 0, 3, focusColor));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBorder(new MatteBorder(0, 0, 0, 3, focusColor));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBorder(new MatteBorder(0, 0, 0, 3, textColor));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(new MatteBorder(0, 0, 0, 3, focusColor));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(new MatteBorder(0, 0, 0, 3, textColor));
            }
        });
    }
    
    public FormButton(ImageIcon buttonIcon, ImageIcon buttonIconFocus, Color focusColor){
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new MatteBorder(0, 0, 1, 0, focusColor));
        setIcon(buttonIcon);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setIcon(buttonIcon);
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(buttonIcon);
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
            }
        });
        
    }
    public FormButton(ImageIcon buttonIcon, ImageIcon buttonIconFocus){
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        setIcon(buttonIcon);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(buttonIconFocus);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(buttonIconFocus);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setIcon(buttonIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e){
                setIcon(buttonIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(buttonIcon);
            }
        });
        
    }
    
    public void setIcon(ImageIcon buttonIcon, ImageIcon buttonIconFocus){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(buttonIconFocus);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(buttonIconFocus);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setIcon(buttonIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(buttonIconFocus);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(buttonIcon);
            }
        });
    }
    public void setIcons(ImageIcon buttonIcon, ImageIcon buttonIconFocus){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                setIcon(buttonIcon);
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(buttonIconFocus);
                setContentAreaFilled(true);
                setOpaque(true);
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(buttonIcon);
                setContentAreaFilled(false);
                setOpaque(false);
                setBackground(null);
            }
        });
    }
    
}
