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
 * @date 2014-3-6 10:32:12
 * @version 1.6.0
 */
public class Console {
    public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException {
        if(args.length==0){
            System.out.println("Java based Data Analysis tool, version 1.2\r\n");
            System.out.println("please input args\n type java -jar dataAnalsisTools.jar -h for help\r\n");
        }else if(args[0].endsWith("-h")){
            System.out.println("Java based Data Analysis tool, version 1.1\r\n");  
            System.out.println("please input args\n type java -jar dataAnalsisTools.jar -h for help\r\n");
            System.out.println("======Draw boxplot:  \r\n\t\tCMD : java -jar RplotGallary.jar -boxplot datafile [outputpath] isgui(T/F)\r\n");
           System.out.println("======Draw correlation plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -correlation datafile samplename1 samplename2 [outputpath] isgui(T/F)\r\n");
             System.out.println("======Draw PCA analysis & plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -pca datafile conditionstr(like:Control,Control,Control,Treated,Treated,Treated) isText(T/F) [outputpath] isgui(T/F)\r\n");
            System.out.println("======Draw Stack plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -stack datafile [outputpath] isgui(T/F)\r\n");
             System.out.println("======Draw barRatio plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -barRatio datafile [outputpath] isgui(T/F)\r\n");
            System.out.println("======Draw barRatio plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -heatmap datafile [outputpath] isgui(T/F)\r\n");
            System.out.println("======Draw heatmap plot:  \r\n\t\tCMD : java -jar RplotGallary.jar -heatmap datafile [outputpath] isgui(T/F)\r\n");
            System.out.println(" heatmap with more parameters \r\n\t\tCMD : java -jar RplotGallary.jar -heatmap datafile genenumber(default:30) isShowKey(T/F) isdisplayNumbers(T/F) [outputpath] isgui(T/F)\r\n");
           System.out.println("Type -updateinfor to view update information");
        }  else if (args[0].endsWith("-updateinfor")) {
             System.out.println("\r\nUpdate information\r\n");
            System.out.println("\r\n1.1. Modified \"Extract expression data from cufflinks outputfile\", addheader information");
             System.out.println("\r\n1.2. Add go mapping based on genelist function");
       
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
            //test parameter:
            //java -jar RplotGallary.jar -pca "C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv" c,c,c,T,T,T T T
            if (args.length == 6) {
                if( args[5].equals("T")){
                    
                    new DrawPCAanalysisPlot(args[1], args[2],args[3],args[4],true);
                }else{
                    new DrawPCAanalysisPlot(args[1], args[2],args[3],args[4],false);
                }
                    
                   
            } else if (args.length == 5) {
                 if( args[4].equals("T")){
                    new DrawPCAanalysisPlot(args[1], args[2],args[3], "./pca",true);
                }else{
                    new DrawPCAanalysisPlot(args[1], args[2],args[3], "./pca",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        }else if (args[0].endsWith("-stack")) {
            if (args.length == 4) {
                if( args[3].equals("T")){
                    new DrawStackplot(args[1], args[2],true);
                }else{
                    new DrawStackplot(args[1], args[2],false);
                }
                    
                   
            } else if (args.length == 3) {
                 if( args[2].equals("T")){
                    new DrawStackplot(args[1], "./boxplot",true);
                }else{
                    new DrawStackplot(args[1], "./boxplot",false);
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
                    new DrawRatioBarplot(args[1], "./boxplot",true);
                }else{
                    new DrawRatioBarplot(args[1], "./boxplot",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        }else if (args[0].endsWith("-heatmap")) {
            if (args.length == 4) {
                if( args[3].equals("T")){
                    new DrawHeatmap(args[1], args[2],true);
                }else{
                    new DrawHeatmap(args[1], args[2],false);
                }
                    
                   
            } else if (args.length == 3) {
                 if( args[2].equals("T")){
                    new DrawHeatmap(args[1], "./boxplot",true);
                }else{
                    new DrawHeatmap(args[1], "./boxplot",false);
                }
              
            } if (args.length == 7) {
                if( args[3].equals("T")){
                    new DrawHeatmap(args[1], Integer.parseInt(args[2]),args[3],args[4],args[5],true);
                }else{
                    new DrawHeatmap(args[1], Integer.parseInt(args[2]),args[3],args[4],args[5],false);
                }
                    
                   
            } else if (args.length == 6) {
                 if( args[2].equals("T")){
                    new DrawHeatmap(args[1], Integer.parseInt(args[2]),args[3],args[4], "./boxplot",true);
                }else{
                    new DrawHeatmap(args[1], Integer.parseInt(args[2]),args[3],args[4], "./boxplot",false);
                }
              
            } else {
                System.out.println("args error!");
            }
        }else{
            System.out.println("command error!");
        }
    }
}