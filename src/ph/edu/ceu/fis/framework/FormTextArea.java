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
import java.awt.*;
import javax.swing.text.*;

public class FormTextArea extends JTextArea{
    
    public FormTextArea(String text, float fontSize){
        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
        try {
            document.insertString(document.getLength(), text.replace("\\n", "\n").replace("\\t", "\t"), style);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        setText(text);
        setWrapStyleWord(true);
        setLineWrap(true);
        setBackground(FrameWorkUtils.getPrimaryColor().brighter());
        setForeground(FrameWorkUtils.getSecondaryColor());
        setFont(FrameWorkUtils.getSystemFont().deriveFont(fontSize));
    }
}
