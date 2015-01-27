/*
 * CanvasPanel.java
 *
 * Created on October 8, 2008, 1:28 PM
 */
package rplotgallary.pub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <p>CanvasPanel</p>
 * <p>Copyright (c) 2008. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.9
 */
public class CanvasPanel extends javax.swing.JPanel {
    private Dimension preferredSize = new Dimension(600, 600);
 public float rate = 1.0f;
    private Image image;
    private LinkedList pointList1;
    private int r1;
    private Color color1;
    private LinkedList pointList2;
    private int r2;
    private Color color2;
 
    private Image tempImage;
    private int currentX=0;
     private int currentY=0;
  
    
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Image getTempImage() {
        return tempImage;
    }

    public void setTempImage(Image tempImage) {
        this.tempImage = tempImage;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    /** Creates new form CanvasPanel */
    public CanvasPanel() {
        pointList1 = new LinkedList();
        pointList2 = new LinkedList();
        initComponents();
    }

    public void setPointList1(LinkedList pointList1) {
        this.pointList1 = pointList1;
    }

    public void setPointList2(LinkedList pointList2) {
        this.pointList2 = pointList2;
    }

//    public void zoomIn() {
//        rate = rate * 1.25;
//        ImageIcon icon = new ImageIcon(image);
//        int width = (int) (icon.getIconWidth() * rate);
//        int height = (int) (icon.getIconHeight() * rate);
//        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
//        this.setPreferredSize(new Dimension(width, height));
//        repaint();
//    }
 
//    public   void zoomOutScale(double drate) {
////        int width = (int) Math.round((this.getWidth() * drate / rate) / 100) * 100;
////      System.out.println("drate :"+drate+" rate :"+rate );
////        int height = (int) Math.round((width / whRatio) / 100) * 100;// (this.getHeight() * drate / rate);//
//         int lwidth = (int) Math.round( 3000* drate  ) ;
//        
//        int lheight = (int) Math.round( 3000*drate   )  ; 
//      
//
//        this.setPreferredSize(new Dimension(lwidth, lheight));
//        this.setSize(lwidth, lheight);
//
//        repaint();
//  rate = (float) drate;
//    }
    

//    public void zoomOut() {
//        rate = rate * 0.8;
//        ImageIcon icon = new ImageIcon(image);
//        int width = (int) (icon.getIconWidth() * rate);
//        int height = (int) (icon.getIconHeight() * rate);
//        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
//        this.setPreferredSize(new Dimension(width, height));
//        repaint();
//    }

    public void setImage(Image image) {
         this.image = image;
        ImageIcon icon = new ImageIcon(image);
        int width = (int) (icon.getIconWidth() * 0.3);
        int height = (int) (icon.getIconHeight() * 0.3);
        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tempImage != null) {
            g. drawImage(tempImage, 0,0, this);//,
            
            //g.setXORMode(Color.black);
            g.setColor(color1);
            for (Iterator it = pointList1.iterator(); it.hasNext();) {
                Point point = (Point) it.next();
                g.fillOval((int) (point.getX() * rate - r1 / 2.0), (int) (point.getY() * rate - r1 / 2.0), r1, r1);
            }
            g.setColor(color2);
            for (Iterator it = pointList2.iterator(); it.hasNext();) {
                Point point = (Point) it.next();
                g.fillOval((int) (point.getX() * rate - r2 / 2.0), (int) (point.getY() * rate - r2 / 2.0), r2, r2);
            }
        }
    }

    public void outputImage(String outputFilePath, String imageType) {
        try {
            int width = this.getWidth();
            int height = this.getHeight();
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            if (imageType.equals("PNG")) {
                bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            } else {
                bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            }
            Graphics2D g2d = bi.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setBackground(Color.white);
            g2d.fillRect(0, 0, width, height);
            paintComponent(g2d);
            int high = 40;
            if (pointList1.size() > 0) {
                g2d.setColor(color1);
                g2d.drawString(String.valueOf(pointList1.size()), 20, high);
                high = 80;
            }
            if (pointList2.size() > 0) {
                g2d.setColor(color2);
                g2d.drawString(String.valueOf(pointList2.size()), 20, high);
            }
            g2d.dispose();
            bi.flush();
            ImageIO.write(bi, imageType, new File(outputFilePath));
        } catch (IOException ex) {
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
