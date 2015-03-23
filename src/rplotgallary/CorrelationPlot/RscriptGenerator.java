/*
 * RscriptGenerator of Correlation plot
 */
package rplotgallary.CorrelationPlot;

import rplotgallary.Boxplot.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-10 18:50:04
 * @version 1.7.0
 */
public class RscriptGenerator {

    private File Rscriptpath;
    private String plotpath;
    private File datafile;
    
    
    private String samplename1="";
    private String samplename2="";

    public RscriptGenerator(String datafile,String samplename1,String samplename2, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
        this.samplename1=samplename1;
        this.samplename2=samplename2;
        this.process();
        
    }

    public void process() throws IOException {

        String str = "";
        Rscriptpath = File.createTempFile("tempR", ".R");
//        Rscriptpath.deleteOnExit();
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

            str += "scatterP <- function(tb, xcol, ycol, isFitted = TRUE, scLabelx = 1, scLabely = 4){\n"
                    + "  #get sample names\n"
                    + "  #xcol, ycol should be character variants as colnames of columns to be plotted\n"
                    + "  #prepare data\n"
                    + "  logtb <- data.frame(matrix(NA, ncol = 2, nrow = length(tb[,1])))\n"
                    + "  for(i in 1:length(tb[,1])){\n"
                    + "    logtb[i,1] <- log10(tb[i, xcol] + 1)\n"
                    + "    logtb[i,2] <- log10(tb[i, ycol] + 1)\n"
                    + "  }\n"
                    + "  colnames(logtb) <- c(xcol, ycol)\n"
                    + "  #prepare cord. of Spearman Correlation notes\n"
                    + "  scLabelx <- as.character(scLabelx)\n"
                    + "  scLabely <- as.character(scLabely)\n"
                    + "  \n"
                    + "  #calculation: spearman correlation\n"
                    + "  spearmanCor <- cor(logtb[,xcol], logtb[,ycol], method=\"spearman\")\n"
                    + "  #plotting: scatter plot + regression line\n"
                    + "  p <- ggplot(logtb, aes_string(x = xcol, y = ycol)) + \n"
                    + "    geom_point(color = \"black\", size = 1) + \n"
                    + "    scale_x_continuous(paste(\"log10 (Normalized Counts + 1) of\", xcol)) + \n"
                    + "    scale_y_continuous(paste(\"log10 (Normalized Counts + 1) of\", ycol))\n"
                    + "  \n"
                    + "  #insertion: regression line\n"
                    + "  if(isFitted == TRUE)\n"
                    + "    p <- p + \n"
                    + "    geom_smooth(method = \"lm\", se = FALSE, color = \"red\", size = 0.75)\n"
                    + "  \n"
                    + "  #insertion: Spearman Correlation\n"
                    + "  \n"
                    + "  scList <- as.data.frame.list(spearmanCor)\n"
                    + "  eq <- substitute(italic(\"Spearman Correlation\") == sc, \n"
                    + "                   list(sc = format(scList, digits = 3)))\n"
                    + "  \n"
                    + "  \n"
                    + "  #theme: background, grid and axis setting\n"
                    + "  p <- p + \n"
                    + "    theme(\n"
                    + "      panel.background = element_rect(fill = \"white\", color = \"black\"),\n"
                    + "      panel.grid.major = element_blank(),\n"
                    + "      panel.grid.minor = element_blank(),\n"
                    + "      axis.text.y = element_text(angle = 00, color = \"black\", size = 15),\n"
                    + "      axis.title = element_text(face = \"bold\", color = \"black\", size = 15),\n"
                    + "      legend.title = element_text(face = \"bold\", color = \"black\", size = 15),\n"
                    + "      legend.text = element_text(color = \"black\", size = 15),\n"
                    + "      #custom theme\n"
                    + "      axis.text.x = element_text(angle = 00, color = \"black\", size = 15)\n"
                    + "    ) \n"
                    + "  \n"
                    + "  print(p)\n"
                    + "  \n"
                    + "}\r\n";
            str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
            str += "scatterP(df,\""+this.samplename1+"\",\""+this.samplename2+"\")\r\n";
            str += "dev.off()\r\n";

            fw.append(str);
            System.out.println(this.samplename1);

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
//        return Rscriptpath.getAbsolutePath();
    }

    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
        //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
//        new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv", "C:\\Users\\Administrator\\Desktop\\fornie");

    }
}
