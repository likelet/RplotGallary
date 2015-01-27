/*
 * To change this license header, choose License Headers in Project Properties.
 
 */

package rplotgallary.heatmap;

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
public class DrawHeatmap {
 public DrawHeatmap(String datafile, String outpath,boolean isgui){
     try {
         RscriptGenerator rg=new RscriptGenerator(datafile,  outpath);
         String rscriptpath=rg.getRscriptpath();
         String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isgui){
              Image img=ImageIO.read(new File(plotpath));
              new ImageFrame(img);
          }
         System.out.println("Your plot is located in "+plotpath);
     } catch (IOException ex) {
         Logger.getLogger(DrawHeatmap.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
  public DrawHeatmap(String datafile, int headnumber,String iskey, String displaynumber,String outpath,boolean isgui){
     try {
         String iskey2="TRUE";
         if(iskey.equals("F")){
            iskey2="FALSE";
         }
         boolean displaynumber2=false;
         if(displaynumber.equals("T")){
            displaynumber2=true;
         }
         RscriptGenerator rg=new RscriptGenerator(datafile,headnumber,iskey,displaynumber2,  outpath);
         String rscriptpath=rg.getRscriptpath();
         String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isgui){
              Image img=ImageIO.read(new File(plotpath));
              new ImageFrame(img);
          }
         System.out.println("Your plot is located in "+plotpath);
     } catch (IOException ex) {
         Logger.getLogger(DrawHeatmap.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    public static void main(String[] args) {
//      new DrawHeatmap("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","C:\\Users\\Administrator\\Desktop\\fornie\\heatmap",true);
      new DrawHeatmap("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv",50,"T","F","C:\\Users\\Administrator\\Desktop\\fornie\\heatmap",true);
    }
}
