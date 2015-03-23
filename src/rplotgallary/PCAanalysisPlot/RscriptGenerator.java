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

//    private boolean isRow=true;
    private boolean isText = true;
    private String conditionstr = "";//condition group infomation for PCA color option, this parameter'length must be as same as your sample numner
    private String[] plotdim = {"PC1", "PC2"};
//and is comma-delimted

    public RscriptGenerator(String datafile, String conditionlist, boolean isText, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
//        this.isRow = isRow;
        this.isText = isText;
        this.conditionstr = ("c(\"" + conditionlist + "\")").replace(",", "\",\"");
        this.process();
    }

    public RscriptGenerator(String datafile, String conditionlist, boolean isText, String outpath, String PC) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
//        this.isRow = isRow;
        this.isText = isText;
        this.conditionstr = ("c(\"" + conditionlist + "\")").replace(",", "\",\"");
        this.plotdim = PC.split(",");
        this.process();
    }

    public RscriptGenerator(String datafile, String conditionlist, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
//        this.isRow = isRow;
        this.conditionstr = ("c(\"" + conditionlist + "\")").replace(",", "\",\"");
    }

    public File getDatafile() {
        return datafile;
    }

    public void setDatafile(File datafile) {
        this.datafile = datafile;
    }

    public boolean isIsText() {
        return isText;
    }

    public void setIsText(boolean isText) {
        this.isText = isText;
    }

    public String getConditionstr() {
        return conditionstr;
    }

    public void setConditionstr(String conditionstr) {
        this.conditionstr = conditionstr;
    }

    public String[] getPlotdim() {
        return plotdim;
    }

    public void setPlotdim(String plotdim) {
        this.plotdim = plotdim.split(",");
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
                    + "    a<-dataForPCAinitialize(data,conditionlist)\n"
                    + "    pca <-PCA(a[,2:ncol(a)], scale.unit=T, graph=F)\n"
                    + "    ctri<-pca$eig[,2]\n"
                    + "    names(ctri)<-c(\"PC1\",\"PC2\",\"PC3\",\"PC4\",\"PC5\")\n"
                    + "    xlabtemp=paste(\"" + this.plotdim[0] + " (\",round(ctri[\"" + this.plotdim[0] + "\"],2),\"%)\",sep=\"\")\n"
                    + "    ylabtemp=paste(\"" + this.plotdim[1] + " (\",round(ctri[\"" + this.plotdim[1] + "\"],2),\"%)\",sep=\"\")\n"
                    + "    colnames(pca$ind$coord)<-c(\"PC1\",\"PC2\",\"PC3\",\"PC4\",\"PC5\")\n"
                    + "    " + this.plotdim[0] + " <- pca$ind$coord[,\"" + this.plotdim[0] + "\"]\n"
                    + "    " + this.plotdim[1] + " <- pca$ind$coord[,\"" + this.plotdim[1] + "\"]\n"
                    + "    maxX=max(" + this.plotdim[0] + ")*1.5\n"
                    + "    maxY=max(" + this.plotdim[1] + ")*1.5\n"
                    + "    plotdata <- data.frame(Condition=a[,1]," + this.plotdim[0] + "," + this.plotdim[1] + ") \n"
                    + "    plotdata$Condition <- factor(plotdata$Condition)\n"
                    + "    plot <- ggplot(plotdata, aes(" + this.plotdim[0] + "," + this.plotdim[1] + "),environment = environment()) + \n"
                    + "      geom_point(aes(colour = Condition,shape = Condition),size = 5) + \n"
                    
                    + "      theme(panel.border = element_rect(linetype = \"dashed\")) + \n"
                    + "      theme_bw() +\n"
                    + "      ylim(-maxY,maxY)+\n"
                    + "xlim(-maxX,maxX)+\n"
                    + "    scale_y_continuous(ylabtemp)+ scale_x_continuous(xlabtemp)+\n"
                    + "      theme(legend.text = element_text(colour=\"blue\", size = 16, face = \"bold\")) + \n"
                    + "      theme(legend.justification=c(1,0),legend.position=\"top\")+\n"
                    + "      theme(legend.title = element_text(colour=\"black\", size=16, face=\"bold\"))+\n"
                    + "      scale_fill_brewer(palette=\"Spectral\")\n"
                    + "    if(isText){\n"
                    + "      plot<-plot+geom_text(aes(label=rownames(plotdata)), size=5, hjust=0.5, vjust=-0.5)\n"
                    + "    }\n"
                    + "    print(plot)\n"
                    + "}\r\n";
            str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
            if (isText) {
                str += "getPCAplot(df," + this.conditionstr + ",isText=TRUE)\r\n";
            } else {
                str += "getPCAplot(df," + this.conditionstr + ",isText=FALSE)\r\n";
            }
            str += "dev.off()\r\n";

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
