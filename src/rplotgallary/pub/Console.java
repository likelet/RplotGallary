package rplotgallary.pub;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import rplotgallary.Barplot.DrawRatioBarplot;
import rplotgallary.Boxplot.DrawBoxplot;
import rplotgallary.CorrelationPlot.CorrelationPlot;
import rplotgallary.PCAanalysisPlot.DrawPCAanalysisPlot;
import rplotgallary.heatmap.DrawHeatmap;
import rplotgallary.stackPlot.DrawStackplot;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-14 10:32:12
 * @version 1.6.0
 */
public class Console {
    public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException {
        if(args.length==0){
            System.out.println("Rplot tools, version 1.3\r\n");

            System.out.println("please input args\n type java -jar dataAnalsisTools.jar -h for help\r\n");
        }else if(args[0].endsWith("-h")){
            System.out.println("Java based Data plot tool, version 1.2\r\n");  
            System.out.println("please input args\n type java -jar dataAnalsisTools.jar -h for help\r\n");
            System.out.println("======Draw boxplot:  \r\n\t\tCMD : java -jar RplotGallary.jar -boxplot datafile [outputpath] isgui(T/F)\r\n");
           System.out.println("======Draw correlation plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -correlation datafile samplename1 samplename2 [outputpath] isgui(T/F)\r\n");
             System.out.println("======Draw PCA analysis & plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -pca datafile conditionstr(like:Control,Control,Control,Treated,Treated,Treated) isText(T/F) [outputpath] isgui(T/F)\r\n");
            System.out.println("======Draw Stack plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -stack datafile [outputpath] isgui(T/F)\r\n");
             System.out.println("======Draw barRatio plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -barRatio datafile [outputpath] isgui(T/F)\r\n");
           System.out.println("======Draw heatmap plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -heatmap datafile(T/F)\r\n");
           System.out.println("Type -updateinfor to view update information");
           System.out.println(" ===================================================================");
            System.out.println("           Authored by Qi ZHAO. email: zhaoqi3@mail2.sysu.edu.cn\n"
                    + "Ren's Lab ,Key Laboratory of Biocontrol, School of Life Sciences, Sun Yat-sen University");
            System.out.println(" ===================================================================");
        }  else if (args[0].endsWith("-updateinfor")) {
             System.out.println("\r\nUpdate information\r\n");
           
       
        }else if (args[0].endsWith("-boxplot")) {
            if (args.length == 4) {
                if( args[3].equals("T")){
                    new DrawBoxplot(args[1], args[2],true);
                }else{
                    new DrawBoxplot(args[1], args[2],false);
                }
                    
                   
            } else if (args.length == 3) {
                 if( args[2].equals("T")){
                    new DrawBoxplot(args[1], "./boxplot",true);
                }else{
                    new DrawBoxplot(args[1], "./boxplot",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-correlation")) {
            if (args.length == 6) {
                if( args[5].equals("T")){
                    
                    new CorrelationPlot(args[1], args[2],args[3],args[4],true);
                }else{
                    new CorrelationPlot(args[1], args[2],args[3],args[4],false);
                }
                    
                   
            } else if (args.length == 5) {
                 if( args[4].equals("T")){
                    new CorrelationPlot(args[1], args[2],args[3], "./correlation",true);
                }else{
                    new CorrelationPlot(args[1], args[2],args[3], "./correlation",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        }else if (args[0].endsWith("-pca")) {
            if(args.length==1){
                System.out.println("Primary Component Analysis with Java:   \r\n\t\tCMD :java -jar RplotGallary.jar -pca matrixDataWithCsvFormat c,c,c,T,T,T T T\r\n");
                System.out.println("Extra paramters  \r\n\t\t-isText\t Plot samples'name or not; Should be T/F\r\n");
                System.out.println(" \r\n\t\t-PClist\t Specify two demensions to be as xais and y axis;Should be : PC1,PC2 or PC1,PC3 or PC2,PC3 \r\n");
                System.out.println(" \r\n\t\t-outputpath\t Set outputdir and filename without suffix\r\n");
            }else{
                 DrawPCAanalysisPlot pcaplot=new DrawPCAanalysisPlot(args[1], args[2]);
            if (FunctionClass.getArgsParameter(args, "-isText") != null) {
                    pcaplot.setIsText(FunctionClass.getArgsParameter(args, "-isText"));
                }
            //set PCs to plot : should be PC1,PC2 OR PC1,PC3 OR PC2,PC3
                if (FunctionClass.getArgsParameter(args, "-PClist") != null) {
                    pcaplot.setPClist(FunctionClass.getArgsParameter(args, "-PClist"));
                }
                if (FunctionClass.getArgsParameter(args, "-outputpath") != null) {
                    pcaplot.setOutpath(FunctionClass.getArgsParameter(args, "-outputpath"));
                }
             pcaplot.process();
            }
            
           
            //test parameter:
            //java -jar RplotGallary.jar -pca "C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv" c,c,c,T,T,T T T
           
        }else if (args[0].endsWith("-stack")) {
            if (args.length == 4) {
                if( args[3].equals("T")){
                    new DrawStackplot(args[1], args[2],true);
                }else{
                    new DrawStackplot(args[1], args[2],false);
                }
                    
                   
            } else if (args.length == 3) {
                 if( args[2].equals("T")){
                    new DrawStackplot(args[1], "./stack",true);
                }else{
                    new DrawStackplot(args[1], "./stack",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-barRatio")) {
            if (args.length == 4) {
                if( args[3].equals("T")){
                    new DrawRatioBarplot(args[1], args[2],true);
                }else{
                    new DrawRatioBarplot(args[1], args[2],false);
                }
                    
                   
            } else if (args.length == 3) {
                 if( args[2].equals("T")){
                    new DrawRatioBarplot(args[1], "./barRatio",true);
                }else{
                    new DrawRatioBarplot(args[1], "./barRatio",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        }else if (args[0].endsWith("-heatmap")) {
            
            if(args.length==1){
                System.out.println("Heat map with Java:   \r\n\t\tCMD :java -jar RplotGallary.jar -heatmap matrixDataWithCsvFormat \r\n");
                System.out.println("Extra paramters  \r\n\t\t-out\t User specified output: like \"./heatmap\" \r\n");
                System.out.println(" \r\n\t\t-isKey\t Display colorkey legend or not : T/F \r\n");
                System.out.println(" \r\n\t\t-isText\t Display numbers or not: T/F\r\n");
                System.out.println(" \r\n\t\t-defineColor\t Set heatmap color with strins: like: green,blue,red(comma seperated )\r\n");
            } else {
                DrawHeatmap heatmap = new DrawHeatmap(args[1]);
                if (FunctionClass.getArgsParameter(args, "-out") != null) {
                    heatmap.setOutpath(FunctionClass.getArgsParameter(args, "-out"));
                }
                if (FunctionClass.getArgsParameter(args, "-isKey") != null) {
                    heatmap.setIskey(FunctionClass.getArgsParameter(args, "-isKey"));
                }
                if (FunctionClass.getArgsParameter(args, "-isText") != null) {
                    heatmap.setDisplaynumber(FunctionClass.getArgsParameter(args, "-isText"));
                }
                if (FunctionClass.getArgsParameter(args, "-defineColor") != null) {
                    heatmap.setColorlist(FunctionClass.getArgsParameter(args, "-defineColor"));
                }
                heatmap.process();
            }

        }else{
            System.out.println("command error!");
        }
    }
}
