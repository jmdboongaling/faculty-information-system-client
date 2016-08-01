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

public class BulletinEntry extends JPanel{
    
        String bulletinID, 
               bulletinSubject,
               bulletinContent, 
               bulletinPoster, 
               bulletinCampus, 
               bulletinTime;
        
    public BulletinEntry(HashMap<String, String> bulletinInfo, int listIndex){
        super(new BorderLayout(5, 5));
        this.bulletinID = bulletinInfo.get("bulletin_id"); 
        this.bulletinSubject = bulletinInfo.get("bulletin_subject");
        this.bulletinContent = bulletinInfo.get("bulletin_content"); 
        this.bulletinPoster = bulletinInfo.get("bulletin_poster"); 
        this.bulletinCampus = bulletinInfo.get("bulletin_campus"); 
        this.bulletinTime = bulletinInfo.get("bulletin_time");
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
        infoLayout.row().grid(new FormLabel("Subject: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(bulletinSubject, 18), FrameWorkUtils.getSecondaryColor(), 15f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Posted by: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(StringUtils.abbreviate(bulletinPoster, 18), FrameWorkUtils.getSecondaryColor(), 13f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Date: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(bulletinTime, FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));
        infoLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 13f)).add(new FormLabel(bulletinCampus, FrameWorkUtils.getSecondaryColor(), 12f, SwingConstants.LEFT, 5));

        MenuButton viewButton = new MenuButton("View", 10f, new ImageIcon("images/view.png"), backgroundColor);
        viewButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showBulletinPost();
            }
        });
        add(new FormLabel(new ImageIcon("images/bullet.png"), 5), BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(viewButton, BorderLayout.EAST);
    }
    
    public void showBulletinPost(){
        JFrame messageDialogFrame = new JFrame("View Bulletin Post");
        messageDialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageDialogFrame.setAlwaysOnTop(true);
        messageDialogFrame.getContentPane().setBackground(FrameWorkUtils.getPrimaryColor());
        messageDialogFrame.setLayout(new BorderLayout(5, 5));
        
        JPanel contentPane = new JPanel();

        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
        try {
            document.insertString(document.getLength(), bulletinContent.replace("\\n", "\n").replace("\\t", "\t"), style);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        JTextArea messagePane = new JTextArea(document);
        
        messagePane.setWrapStyleWord(true);
        messagePane.setLineWrap(true);
        messagePane.setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        messagePane.setForeground(FrameWorkUtils.getSecondaryColor());
        messagePane.setFont(FrameWorkUtils.getSystemFont().deriveFont(14f));
        messagePane.setEditable(false);
        contentPane.setOpaque(false);

        
        CustomScrollPane messageContainer = new CustomScrollPane(messagePane);
        messageContainer.setPreferredSize(new Dimension(500, 300));
        FormButton closeButton = new FormButton("Close", 15f);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageDialogFrame.dispose();
            }
        });
        DesignGridLayout contentLayout = new DesignGridLayout(contentPane);
        contentLayout.row().center().add(new JLabel(new ImageIcon("images/bullet.png")));
        contentLayout.row().center().add(new FormLabel("Bulletin Message", FrameWorkUtils.getAccentColor(), 25f));
        
        contentLayout.row().grid(new FormLabel("Subject: ", FrameWorkUtils.getSecondaryColor(), 14f)).add(new FormLabel(bulletinSubject, FrameWorkUtils.getSecondaryColor(), 14f, SwingConstants.LEFT, 0));
        contentLayout.row().grid(new FormLabel("Posted by: ", FrameWorkUtils.getSecondaryColor(), 14f)).add(new FormLabel(bulletinPoster, FrameWorkUtils.getSecondaryColor(), 14f, SwingConstants.LEFT, 0));
        contentLayout.row().grid(new FormLabel("Date: ", FrameWorkUtils.getSecondaryColor(), 14f)).add(new FormLabel(bulletinTime, FrameWorkUtils.getSecondaryColor(), 14f, SwingConstants.LEFT, 0));
        contentLayout.row().grid(new FormLabel("Campus: ", FrameWorkUtils.getSecondaryColor(), 14f)).add(new FormLabel(bulletinCampus, FrameWorkUtils.getSecondaryColor(), 14f, SwingConstants.LEFT, 0));

        messageDialogFrame.add(contentPane, BorderLayout.NORTH);
        messageDialogFrame.add(messageContainer, BorderLayout.CENTER);
        messageDialogFrame.add(closeButton, BorderLayout.SOUTH);
        messageDialogFrame.pack();
        messageDialogFrame.setResizable(false);
        messageDialogFrame.setLocationRelativeTo(null);
        messageDialogFrame.setVisible(true);
        
    }
}
