package rplotgallary.pub;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-11 18:43:10
 * @version 1.8.0
 */
public class ImageFrame extends javax.swing.JFrame {
    public int currentindexrate = 9;
    public static final String DELIMIT = "#&%";
    private CanvasPanel canvasPanel=new CanvasPanel();
    private Image image = null;
    private float zoomrate;
 //public float rate = 1.0f;

    
 
    public int getCurrentindexrate() {
        return currentindexrate;
    }

    public void setCurrentindexrate(int currentindexrate) {
        this.currentindexrate = currentindexrate;
    }
 
     public ImageFrame() {
        this.setPreferredSize(new java.awt.Dimension(800, 800));
        initComponents();
        this.setVisible(true);
     }
     public ImageFrame(Image image) {
         this.image=image;
        this.setPreferredSize(new java.awt.Dimension(800, 800));
        initComponents();
         fitWidth();
        this.setVisible(true);
     }

     
     private void fitWidth() {
        ImageIcon icon = new ImageIcon(image);
        double width = icon.getIconWidth();
        double zoomRate = (canvasScrollPane.getWidth() - 10) / width;
        ((CanvasPanel) canvasPanel).setImage(this.image);
        ((CanvasPanel) canvasPanel).repaint();
        canvasScrollPane.setViewportView(canvasPanel);
    }

   

    private void exprotImage() {
        try {
           
                JFileChooser fc = new JFileChooser(Tools.CURRENT_FILE_PATH);
                fc.setAcceptAllFileFilterUsed(false);
                fc.addChoosableFileFilter(new Filter("PNG", "PNG (*.PNG)"));
                fc.addChoosableFileFilter(new Filter("GIF", "GIF (*.GIF)"));
                fc.addChoosableFileFilter(new Filter("JPG", "JPG (*.JPG)"));

                int returnVal = fc.showSaveDialog(ImageFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String outputPath = fc.getSelectedFile().getPath();
                    if (fc.getFileFilter().getDescription().equals("PNG (*.PNG)")) {
                        if (!outputPath.toLowerCase().endsWith(".png")) {
                            outputPath = outputPath + ".PNG";
                        }
                    } else if (fc.getFileFilter().getDescription().equals("JPG (*.JPG)")) {
                        if (!outputPath.toLowerCase().endsWith(".jpg")) {
                            outputPath = outputPath + ".JPG";
                        }
                    } else if (fc.getFileFilter().getDescription().equals("GIF (*.GIF)")) {
                        if (!outputPath.toLowerCase().endsWith(".gif")) {
                            outputPath = outputPath + ".GIF";
                        }
                    }

                    File exportfile = new File(outputPath);
                    int confirm = JOptionPane.YES_OPTION;
                    if (!exportfile.exists()) {
                        exportfile.createNewFile();
                    } else {
                        confirm = JOptionPane.showConfirmDialog(null,
                                "Image file already exists.Would you like to overwrite it?", "Warning",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    }

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (fc.getFileFilter().getDescription().equals("PNG (*.PNG)")) {
                            ((CanvasPanel) canvasPanel).outputImage(outputPath, "PNG");
                        } else if (fc.getFileFilter().getDescription().equals("JPG (*.JPG)")) {
                            ((CanvasPanel) canvasPanel).outputImage(outputPath, "JPG");
                        } else if (fc.getFileFilter().getDescription().equals("GIF (*.GIF)")) {
                            ((CanvasPanel) canvasPanel).outputImage(outputPath, "GIF");
                        }
                        Tools.CURRENT_FILE_PATH = outputPath;
                        JOptionPane.showMessageDialog(null, "Image save to  " + outputPath + "  successfully !");
                    }

                }
            
        } catch (IOException ex) {
            Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Tools.WindowCenter(new ImageFrame());
            }
        });
    }

     
     
    /** Creates new form DOGFrame */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        canvasScrollPane = new javax.swing.JScrollPane();
        consolePanel = new javax.swing.JPanel();
        OKButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" Demo");
        setMinimumSize(new java.awt.Dimension(800, 600));

        mainPanel.setLayout(new java.awt.BorderLayout());

        canvasScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        canvasScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Canvas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        mainPanel.add(canvasScrollPane, java.awt.BorderLayout.CENTER);

        consolePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Console", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        consolePanel.setPreferredSize(new java.awt.Dimension(100, 69));

        OKButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        OKButton.setText("OK");
        OKButton.setMaximumSize(new java.awt.Dimension(200, 23));
        OKButton.setPreferredSize(new java.awt.Dimension(150, 23));
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });
        consolePanel.add(OKButton);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        consolePanel.add(jButton1);

        mainPanel.add(consolePanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void demoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoMenuItemActionPerformed
    //Tools.WindowCenter(new ImageFrame());
}//GEN-LAST:event_demoMenuItemActionPerformed

private void moveUpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpMenuItemActionPerformed
    
}//GEN-LAST:event_moveUpMenuItemActionPerformed

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
this.dispose();
    }//GEN-LAST:event_OKButtonActionPerformed

    private void canvasPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_canvasPanelMouseWheelMoved
    
    }//GEN-LAST:event_canvasPanelMouseWheelMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.exprotImage();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

     public void setCanvasSize() {
        String size = "Canvas : " + canvasPanel.getWidth() + "×" + canvasPanel.getHeight();
        canvasScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, size, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.JScrollPane canvasScrollPane;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
