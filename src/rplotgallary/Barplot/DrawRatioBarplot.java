/*
 * To change this license header, choose License Headers in Project Properties.
 
 */

package rplotgallary.Barplot;

import rplotgallary.Boxplot.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import rplotgallary.pub.FunctionClass;
import rplotgallary.pub.ImageFrame;
import rplotgallary.pub.Rexe;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-26 11:06:00
 * @version 1.6.0
 */
public class DrawRatioBarplot {
 public DrawRatioBarplot(String datafile, String outpath,boolean isgui){
     try {
         RscriptGenerator rg=new RscriptGenerator(datafile,  outpath);
         String rscriptpath=rg.getRscriptpath();
         String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isgui){
              Image img=ImageIO.read(new File(plotpath));
              new ImageFrame(img);
          }
          FunctionClass.removeRoutFile(rscriptpath);
         System.out.println("Your plot is located in "+plotpath);
     } catch (IOException ex) {
         Logger.getLogger(DrawRatioBarplot.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
 
    public static void main(String[] args) {
      new DrawRatioBarplot("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","C:\\Users\\Administrator\\Desktop\\fornie\\ratioBar",true);
    }
}
