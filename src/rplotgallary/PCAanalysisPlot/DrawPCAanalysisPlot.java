/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rplotgallary.PCAanalysisPlot;

import rplotgallary.CorrelationPlot.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import rplotgallary.pub.ImageFrame;
import rplotgallary.pub.Rexe;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-26 11:06:00
 * @version 1.6.0
 */
public class DrawPCAanalysisPlot {
 public DrawPCAanalysisPlot(String datafile,String conditionstr,String isText,String outpath,boolean isgui){
     try {
         boolean isText2=true;
         if(isText.equals("F")){
             isText2=false;
         }
         RscriptGenerator rg=new RscriptGenerator(datafile, conditionstr, isText2, outpath);
         String rscriptpath=rg.getRscriptpath();
         String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isgui){
              Image img=ImageIO.read(new File(plotpath));
              new ImageFrame(img);
          }
         System.out.println("Your plot is located in "+plotpath);
     } catch (IOException ex) {
         Logger.getLogger(DrawPCAanalysisPlot.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
 
    public static void main(String[] args) {
     new DrawPCAanalysisPlot("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","c,c,c,T,T,T","T","C:\\Users\\Administrator\\Desktop\\fornie\\pca",true);   
    }
}
