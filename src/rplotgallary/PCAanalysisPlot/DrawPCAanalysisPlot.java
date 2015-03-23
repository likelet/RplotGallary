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
import rplotgallary.pub.FunctionClass;
import rplotgallary.pub.ImageFrame;
import rplotgallary.pub.Rexe;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-26 11:06:00
 * @version 1.6.0
 */
public class DrawPCAanalysisPlot {
    private  String isText="T";
    private String PClist="PC1,PC2";
    private boolean isGui=false;
    private String datafile;
    private String conditionstr;
    private String outpath="./pca";

    public DrawPCAanalysisPlot(String datafile, String conditionstr) {
        this.datafile = datafile;
        this.conditionstr = conditionstr;
    }
   
    
   
 public DrawPCAanalysisPlot(String datafile,String conditionstr,String isText,String outpath,boolean isgui){
     try {
         boolean isText2=true;
         if(isText.equals("F")){
             isText2=false;
         }
         RscriptGenerator rg=new RscriptGenerator(datafile, conditionstr, outpath);
         rg.setIsText(isText2);
         String rscriptpath=rg.getRscriptpath();
         System.out.println(rscriptpath);
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
 public DrawPCAanalysisPlot(String datafile,String conditionstr,String isText,String outpath,boolean isgui,String PClist){
     try {
         boolean isText2=true;
         if(isText.equals("F")){
             isText2=false;
         }
         RscriptGenerator rg=new RscriptGenerator(datafile, conditionstr, isText2, outpath,PClist);
         String rscriptpath=rg.getRscriptpath();
         System.out.println(rscriptpath);
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
 
  public void process(){
         try {
        
         RscriptGenerator rg=new RscriptGenerator(datafile, conditionstr, outpath);
         boolean isText2=true;
         if(isText.equals("F")){
             isText2=false;
         }
         rg.setIsText(isText2);
         rg.setPlotdim(this.PClist);
         
         rg.process();
         String rscriptpath=rg.getRscriptpath();
         
         
         System.out.println(rscriptpath);
         String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isGui){
              Image img=ImageIO.read(new File(plotpath));
              new ImageFrame(img);
          }
          
          
          //Delete Rout file
//          FunctionClass.removeRoutFile(rscriptpath);
             
         //print functions;
         System.out.println("Your plot is located in "+plotpath);
     } catch (IOException ex) {
         Logger.getLogger(DrawPCAanalysisPlot.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public void setIsText(String isText) {
        this.isText = isText;
    }

    public void setPClist(String PClist) {
        this.PClist = PClist;
    }

    public void setIsGui(boolean isGui) {
        this.isGui = isGui;
    }

    public void setOutpath(String outpath) {
        this.outpath = outpath;
    }
 
  
  
 
    public static void main(String[] args) {
     DrawPCAanalysisPlot test=new DrawPCAanalysisPlot("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","C,C,C,T,T,T"); 
     test.setIsGui(true);
     test.setIsText("T");
     test.setPClist("PC1,PC2");
     test.process();
    }
}
