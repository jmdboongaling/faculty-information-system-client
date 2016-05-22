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
import java.awt.geom.RoundRectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.coobird.thumbnailator.*;

public class PictureLabel extends JLabel{
    
    public PictureLabel(URL image, int width, int height, boolean roundedCorners){
        try{
            if(roundedCorners){
                setIcon(new ImageIcon(makeRoundedCorner(Thumbnails.of(image).size(width, height).asBufferedImage(), 15)));
            }else{
                setIcon(new ImageIcon(Thumbnails.of(image).size(width, height).asBufferedImage()));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(20, 0, 20, 0));
    }
    
    public PictureLabel(File image, int width, int height, boolean roundedCorners){
        try{
            if(roundedCorners){
                setIcon(new ImageIcon(makeRoundedCorner(Thumbnails.of(image).size(width, height).asBufferedImage(), 15)));
            }else{
                setIcon(new ImageIcon(Thumbnails.of(image).size(width, height).asBufferedImage()));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(20, 0, 20, 0));
    }
    
    public PictureLabel(String image, int width, int height, boolean roundedCorners){
        try{
            if(roundedCorners){
                setIcon(new ImageIcon(makeRoundedCorner(Thumbnails.of(image).size(width, height).asBufferedImage(), 15)));
            }else{
                setIcon(new ImageIcon(Thumbnails.of(image).size(width, height).asBufferedImage()));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(20, 0, 20, 0));
    }
    
    public PictureLabel(InputStream image, int width, int height, boolean roundedCorners){
        try{
            if(roundedCorners){
                setIcon(new ImageIcon(makeRoundedCorner(Thumbnails.of(image).size(width, height).asBufferedImage(), 15)));
            }else{
                setIcon(new ImageIcon(Thumbnails.of(image).size(width, height).asBufferedImage()));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(20, 0, 20, 0));
    }
    
    public PictureLabel(BufferedImage image, int width, int height, boolean roundedCorners){
        try{
            if(roundedCorners){
                setIcon(new ImageIcon(makeRoundedCorner(Thumbnails.of(image).size(width, height).asBufferedImage(), 15)));
            }else{
                setIcon(new ImageIcon(Thumbnails.of(image).size(width, height).asBufferedImage()));
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(20, 0, 20, 0));
    }
    
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius){
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = output.createGraphics();
    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);

    g2.dispose();

    return output;
}
    
    

}
