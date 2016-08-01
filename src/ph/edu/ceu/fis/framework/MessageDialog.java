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
import javax.swing.text.*;
import net.java.dev.designgridlayout.DesignGridLayout;



public class MessageDialog{
    
    
    public static void showMessage(String title, String message){
        JFrame messageDialogFrame = new JFrame(title);
        messageDialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageDialogFrame.setAlwaysOnTop(true);
        messageDialogFrame.getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        messageDialogFrame.setLayout(new GridLayout(1, 1));
        FormButton okayButton = new FormButton("OK", 15f);
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageDialogFrame.dispose();
            }
        });
        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        
        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
        try {
            document.insertString(document.getLength(), message, style);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        JTextPane messagePane = new JTextPane(document);
        messagePane.setText(message);
        messagePane.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        messagePane.setForeground(FrameWorkUtils.getSecondaryColor());
        messagePane.setFont(FrameWorkUtils.getSystemFont().deriveFont(14f));
        messagePane.setEditable(false);
        messagePane.setEnabled(false);
        contentPane.setOpaque(false);
       
        contentPane.add(new FormLabel("System Message", FrameWorkUtils.getAccentColor(), 25f), BorderLayout.NORTH);
        
        
        contentPane.add(new CustomScrollPane(messagePane), BorderLayout.CENTER);
       
        contentPane.add(okayButton, BorderLayout.SOUTH);
        
        messageDialogFrame.add(contentPane);
        messageDialogFrame.setSize(new Dimension(400, 200));
        messageDialogFrame.setResizable(false);
        messageDialogFrame.setLocationRelativeTo(null);
        messageDialogFrame.setVisible(true);
        
    }
    
    
    public static void showExitDialog(JFrame frame, String title, String message){
        frame.setEnabled(false);
        JFrame messageDialogFrame = new JFrame(title);
        messageDialogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageDialogFrame.setAlwaysOnTop(true);
        messageDialogFrame.getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        messageDialogFrame.setLayout(new GridLayout(1, 1));
        FormButton okayButton = new FormButton("OK", 15f);
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        
        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
        try {
            document.insertString(document.getLength(), message, style);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        JTextPane messagePane = new JTextPane(document);
        messagePane.setText(message);
        messagePane.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        messagePane.setForeground(FrameWorkUtils.getSecondaryColor());
        messagePane.setFont(FrameWorkUtils.getSystemFont().deriveFont(14f));
        messagePane.setEditable(false);
        messagePane.setEnabled(false);
        contentPane.setOpaque(false);
       
        contentPane.add(new FormLabel("System Message", FrameWorkUtils.getAccentColor(), 25f), BorderLayout.NORTH);
        
        
        contentPane.add(new CustomScrollPane(messagePane), BorderLayout.CENTER);
       
        contentPane.add(okayButton, BorderLayout.SOUTH);
        
        messageDialogFrame.add(contentPane);
        messageDialogFrame.setSize(new Dimension(400, 200));
        messageDialogFrame.setResizable(false);
        messageDialogFrame.setLocationRelativeTo(null);
        messageDialogFrame.setVisible(true);
        
    }
    
    
    

}
