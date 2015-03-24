/*
 **  RscriptGenerator of RatioBarplot
 */
package rplotgallary.Barplot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rplotgallary.pub.Console;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-10 18:50:04
 * @version 1.7.0
 */
public class RscriptGenerator {

    private File Rscriptpath;
    private String plotpath;
    private final File datafile;

    public RscriptGenerator(String datafile, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
        this.process();
    }

    public void process() throws IOException {

        String str = "";
        Rscriptpath = File.createTempFile("tempR", ".R");
        Rscriptpath.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpath);

        //System.out.println(intervalstr+"\r\n"+integerstri);
        try {

            String Rscriptdir = Rscriptpath.getParent();
            plotpath = plotpath + ".png";
            String datapath = datafile.getAbsolutePath().replace("\\", "/");
            plotpath = plotpath.replace("\\", "/");
            //System.out.println(dirOut);
//                str="setwd(\""+Rscriptdir+"\")\r\n";
            str += "library(ggplot2)\r\n";
            str += "library(reshape2)\r\n";
            str += "library(RColorBrewer)\r\n";
            str += "library(scales)\r\n";
            str += "library(grid)\r\n";

            str += "df<-read.csv(\"" + datapath + "\",header=T,row.names=1)\r\n";

            str += "stackedBarP <- function(tb){\n"
                    + "  melttb=melt(tb)\n"
                    + "  #prepare data\n"
                    + "  cutcount<-function(x){\n"
                    + "    return(cut(x, breaks=c(0,0.05,1,2,5,10),include.lowest=TRUE))\n"
                    + "  }\n"
                    + "  melttb[,2]= cutcount(melttb[,2])\n"
                    + "  a<-as.character(melttb[,2])\n"
                    + "  a[a==\"[0,0.05]\"]=0\n"
                    + "  a[a==\"(0.05,1]\"]='Normalized Counts > 0'\n"
                    + "  a[a==\"(1,2]\"]='Normalized Counts > 1'\n"
                    + "  a[a==\"(2,5]\"]='Normalized Counts > 2'\n"
                    + "  a[a==\"(5,10]\"]='Normalized Counts > 5'\n"
                    + "  a[is.na(a)]='Normalized Counts > 10'\n"
                    + "  melttb[,2]=a\n"
                    + "  \n"
                    + "  colnames(melttb) <- c(\"Samples\", \"Status\")\n"
                    + "  #format order\n"
                    + "  melttb$Status <- factor(melttb$Status, levels = c(\"Normalized Counts > 0\", \"Normalized Counts > 1\", \"Normalized Counts > 2\", \"Normalized Counts > 5\", \"Normalized Counts > 10\"))\n"
                    + "  #draw basic Stacked Bar Plot\n"
                    + "  p <- ggplot(melttb, aes(Samples)) + \n"
                    + "    geom_bar(aes(fill = Status), position = \"fill\")\n"
                    + "  #format y axis\n"
                    + "  p <- p + \n"
                    + "    scale_y_continuous(\"Sensitivity(%)\", labels = percent, expand = c(0,0)) + #expand: delete bottom and upper margin of 0% and 100%\n"
                    + "    #set colors\n"
                    + "    scale_fill_brewer(palette = \"Spectral\")\n"
                    + "  #format theme\n"
                    + "  p <- p + \n"
                    + "    theme(\n"
                    + "      panel.background = element_rect(fill = \"white\", color = \"black\"),\n"
                    + "      panel.grid.major = element_blank(),\n"
                    + "      panel.grid.minor = element_blank(),\n"
                    + "      axis.text.x = element_text(angle = 90, color = \"black\", size = 15),\n"
                    + "      axis.text.y = element_text(angle = 00, color = \"black\", size = 15),\n"
                    + "      axis.title = element_text(face = \"bold\", color = \"black\", size = 15),\n"
                    + "      legend.title = element_text(face = \"bold\", color = \"black\", size = 15),\n"
                    + "      #custom theme\n"
                    + "      legend.title = element_blank(),\n"
                    + "      legend.text = element_text(color = \"black\", size = 10),\n"
                    + "      legend.position = (\"right\")\n"
                    + "    )\n"
                    + "  print(p)\n"
                    + "}  \r\n";
            //change figure residue
            if(Console.iscale.equalsIgnoreCase("small")){
                str += "png(\"" + plotpath + "\")\r\n";
            
            }else{
                 str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
            }
            str += "stackedBarP(df)\r\n";
            str += "dev.off()\r\n";

            fw.append(str);

        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        fw.close();
    }

    public String getRscriptpath() {
        return Rscriptpath.getAbsolutePath();
    }

    public String getPlotpath() {
        return new File(plotpath).getAbsolutePath();
    }

    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
        //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
        new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv", "C:\\Users\\Administrator\\Desktop\\fornie");

    }
}
