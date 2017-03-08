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

    //global parameters

    public static String iscale = "big";

    public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException {
        long start =   System.nanoTime();
        String version = "1.4.1";
        if (FunctionClass.getArgsParameter(args, "-scale") != null) {
            Console.iscale = FunctionClass.getArgsParameter(args, "-scale");
        }

        if (args.length == 0) {
            System.out.println("RplotGallary, version  " + version + "\r\n");
             System.out.println("Please input args\n Type "
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar ")
                    + ToolsforCMD.print_ansi_CYAN("-h")
                    + " for help\r\n");
//            System.out.println("please input args\n type java -jar RplotGallary.jar -h for help");
        } else if (args[0].endsWith("-h")) {
            System.out.println("Rplot tools, version  " + version + "\r\n");
            System.out.println("please input args\n type java -jar RplotGallary.jar -h for help");
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw boxplot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -boxplot")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile> [outputpath] <isgui(T/F)>"));
//            System.out.println("======Draw boxplot:  \r\n\t\tCMD : java -jar RplotGallary.jar -boxplot datafile [outputpath] isgui(T/F)");
           
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw correlation plot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -boxplot")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile> [outputpath] <isgui(T/F)>"));
            
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw PCA analysis &  plot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -pca")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile> <conditionstr(like:Control,Control,Control,Treated,Treated,Treated)> [parameters]"));
            
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw Stack plot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -boxplot")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile> [outputpath] <isgui(T/F)>"));
            
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw barRatio plot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -barRatio")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile> [outputpath] <isgui(T/F)>"));
            
            System.out.println(ToolsforCMD.print_ansi_YELLOW("======Draw heatmap plot:   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar RplotGallary.jar -heatmap")
                    + ToolsforCMD.print_ansi_CYAN(" <datafile(T/F)>"));
            
            System.out.println("Type -updateinfor to view update information");
            
            
            System.out.println("Funcion with (M) followed indicates that there are more parameters can be set by typing -h");
            System.out.println(" ===================================================================");
            System.out.println("           Authored by Qi ZHAO. email: zhaoqi3@mail2.sysu.edu.cn\n"
                    + "Ren's Lab ,Key Laboratory of Biocontrol, School of Life Sciences, Sun Yat-sen University");
            System.out.println(" ===================================================================");
        } else if (args[0].endsWith("-updateinfor")) {
            System.out.println("\r\nUpdate information");
            System.out.println("\r\n1.4 add PCA plot and Heatmap userdefined color parameters");
            System.out.println("\r\n1.4.1 add -scale parameter to adjust output file size (small/big)");
        } else if (args[0].endsWith("-boxplot")) {
            if (args.length == 4) {
                if (args[3].equals("T")) {
                    new DrawBoxplot(args[1], args[2], true);
                } else {
                    new DrawBoxplot(args[1], args[2], false);
                }

            } else if (args.length == 3) {
                if (args[2].equals("T")) {
                    new DrawBoxplot(args[1], "./boxplot", true);
                } else {
                    new DrawBoxplot(args[1], "./boxplot", false);
                }

            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-correlation")) {
            if (args.length == 6) {
                if (args[5].equals("T")) {

                    new CorrelationPlot(args[1], args[2], args[3], args[4], true);
                } else {
                    new CorrelationPlot(args[1], args[2], args[3], args[4], false);
                }

            } else if (args.length == 5) {
                if (args[4].equals("T")) {
                    new CorrelationPlot(args[1], args[2], args[3], "./correlation", true);
                } else {
                    new CorrelationPlot(args[1], args[2], args[3], "./correlation", false);
                }

            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-pca")) {
            if (args.length == 1) {
                System.out.println("Primary Component Analysis with Java:   \r\n\t\tCMD :java -jar RplotGallary.jar -pca matrixDataWithCsvFormat <conditionlist like:c,c,c,T,T,T T> [parameters]");
                System.out.println("Extra paramters  \r\n\t\t-isText\t Plot samples'name or not; Should be T/F");
                System.out.println(" \r\n\t\t-PClist\t Specify two demensions to be as xais and y axis;Should be : PC1,PC2 or PC1,PC3 or PC2,PC3 ");
                System.out.println(" \r\n\t\t-outputpath\t Set outputdir and filename without suffix");
                System.out.println(" \r\n\t\t-scale\t set residue of output figure: should be small or big");
            } else {
                DrawPCAanalysisPlot pcaplot = new DrawPCAanalysisPlot(args[1], args[2]);
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
        } else if (args[0].endsWith("-stack")) {
            if (args.length == 4) {
                if (args[3].equals("T")) {
                    new DrawStackplot(args[1], args[2], true);
                } else {
                    new DrawStackplot(args[1], args[2], false);
                }

            } else if (args.length == 3) {
                if (args[2].equals("T")) {
                    new DrawStackplot(args[1], "./stack", true);
                } else {
                    new DrawStackplot(args[1], "./stack", false);
                }

            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-barRatio")) {
            if (args.length == 4) {
                if (args[3].equals("T")) {
                    new DrawRatioBarplot(args[1], args[2], true);
                } else {
                    new DrawRatioBarplot(args[1], args[2], false);
                }

            } else if (args.length == 3) {
                if (args[2].equals("T")) {
                    new DrawRatioBarplot(args[1], "./barRatio", true);
                } else {
                    new DrawRatioBarplot(args[1], "./barRatio", false);
                }

            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-heatmap")) {

            if (args.length == 1) {
                System.out.println("Heat map with Java:   \r\n\t\tCMD :java -jar RplotGallary.jar -heatmap matrixDataWithCsvFormat ");
                System.out.println("Extra paramters  \r\n\t\t-out\t User specified output: like \"./heatmap\" ");
                System.out.println(" \r\n\t\t-isKey\t Display colorkey legend or not : T/F ");
                System.out.println(" \r\n\t\t-isText\t Display numbers or not: T/F");
                System.out.println(" \r\n\t\t-defineColor\t Set heatmap color with strins: like: green,blue,red(comma seperated )");
                System.out.println(" \r\n\t\t-headN\t Set number of rows in your data to be plot: default 30");
                System.out.println(" \r\n\t\t-scale\t set residue of output figure: should be small or big");
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
                if (FunctionClass.getArgsParameter(args, "-headN") != null) {
                    heatmap.setHeadnumber(Integer.parseInt(FunctionClass.getArgsParameter(args, "-headN")));
                }

                heatmap.process();
            }

        } else {
            System.out.println("command error!");
        }
    }
}
