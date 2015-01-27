/*
 * RscriptGenerator of PCA plot
 */
package rplotgallary.PCAanalysisPlot;

import rplotgallary.CorrelationPlot.*;
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

    private boolean isRow ;
    private boolean isText;
    private String conditionstr="";//condition group infomation for PCA color option, this parameter'length must be as same as your sample numner
    //and is comma-delimted

    public RscriptGenerator(String datafile, String conditionlist, boolean isText, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
        this.isRow = isRow;
        this.isText = isText;
        this.conditionstr=("c(\""+conditionlist+"\")").replace(",", "\",\"");
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
            str += "library(FactoMineR)\r\n";
            str += "df<-read.csv(\"" + datapath + "\",header=T,row.names=1)\r\n";

            str += "#convert userinput data and condition list for PCA analysis\n"
                    + "dataForPCAinitialize<-function(data,conditionlist){\n"
                    + "  data<-t(data)\n"
                    + "  data<-data.frame(condition=conditionlist,data)\n"
                    + "  return(data)\n"
                    + "}\n"
                    + "\n"
                    + "#get ggplot2 output result\n"
                    + "getPCAplot <- function(data,conditionlist,isText=FALSE){\n"
                    + "  if(isText){\n"
                    + "    a<-dataForPCAinitialize(data,conditionlist)\n"
                    + "    pca <-PCA(a[,2:ncol(a)], scale.unit=T, graph=F)\n"
                    + "    PC1 <- pca$ind$coord[,1]\n"
                    + "    PC2 <- pca$ind$coord[,2]\n"
                    + "    plotdata <- data.frame(Condition=a[,1],PC1,PC2) \n"
                    + "    plotdata$Condition <- factor(plotdata$Condition)\n"
                    + "    plot <- ggplot(plotdata, aes(PC1, PC2),environment = environment()) + \n"
                    + "      geom_point(aes(colour = Condition,shape = Condition),size = 5) + \n"
                    + "      geom_text(aes(label=rownames(plotdata)), size=5, hjust=0.5, vjust=-0.5)+\n"
                    + "      theme(panel.border = element_rect(linetype = \"dashed\")) + \n"
                    + "      theme_bw() + \n"
                    + "      theme(legend.text = element_text(colour=\"blue\", size = 16, face = \"bold\")) + \n"
                    + "      theme(legend.justification=c(1,0),legend.position=\"top\")+\n"
                    + "      theme(legend.title = element_text(colour=\"black\", size=16, face=\"bold\"))+\n"
                    + "      scale_fill_brewer(palette=\"Spectral\")\n"
                    + "    return(plot)\n"
                    + "  }else{\n"
                    + "  a<-dataForPCAinitialize(data,conditionlist)\n"
                    + "  pca <-PCA(a[,2:ncol(a)], scale.unit=T, graph=F)\n"
                    + "  PC1 <- pca$ind$coord[,1]\n"
                    + "  PC2 <- pca$ind$coord[,2]\n"
                    + "  plotdata <- data.frame(Condition=a[,1],PC1,PC2) \n"
                    + "  plotdata$Condition <- factor(plotdata$Condition)\n"
                    + "  plot <- ggplot(plotdata, aes(PC1, PC2)) + \n"
                    + "         geom_point(aes(colour = Condition,shape = Condition),size = 5) + \n"
                    + "         theme(panel.border = element_rect(linetype = \"dashed\")) + \n"
                    + "         theme_bw() + \n"
                    + "         theme(legend.text = element_text(colour=\"blue\", size = 16, face = \"bold\")) + \n"
                    + "    theme(legend.justification=c(1,0),legend.position=\"top\")+\n"
                    + "         theme(legend.title = element_text(colour=\"black\", size=16, face=\"bold\"))+\n"
                    + "    scale_fill_brewer(palette=\"Spectral\")\n"
                    + "  print(plot)\n"
                    + "  \n"
                    + "  }\n"
                    + " \n"
                    + " \n"
                    + "}\r\n";
            str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
            if(isText){
                str += "getPCAplot(df," + this.conditionstr + ",isText=TRUE)\r\n";
            }else{
                str += "getPCAplot(df," + this.conditionstr + ")\r\n";
            }
            str += "dev.off()\r\n;";

            fw.append(str);
            System.out.println(this.conditionstr);

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
//       new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv", "C:\\Users\\Administrator\\Desktop\\fornie");

    }
}
