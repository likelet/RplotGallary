/*
 * RscriptGenerator of stack plot
 */
package rplotgallary.stackPlot;


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
    

    public RscriptGenerator(String datafile, String outpath) throws IOException {
     this.datafile=new File(datafile);
     this.plotpath=outpath;
     this.process();
    }

  

    
    
    
    public void process() throws IOException{
       
        String str="";
        Rscriptpath=File.createTempFile("tempR",".R");
        Rscriptpath.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpath);
       
        //System.out.println(intervalstr+"\r\n"+integerstri);
            
            try {
              
                
                String Rscriptdir=Rscriptpath.getParent();
                plotpath=plotpath+".png";
                String datapath=datafile.getAbsolutePath().replace("\\", "/");
                   plotpath=plotpath.replace("\\", "/");
                //System.out.println(dirOut);
//                str="setwd(\""+Rscriptdir+"\")\r\n";
                str+="library(ggplot2)\r\n";
                str+="library(reshape2)\r\n";
                str+="library(RColorBrewer)\r\n";
                str+="library(scales)\r\n";
                str+="library(grid)\r\n";
               
                str+= "df<-read.csv(\""+datapath+"\",header=T,row.names=1)\r\n";
                
                str+= "stackedDensityP <- function(tb){\n" +
"  require(scales)\n" +
"  melttb <- melt(tb)\n" +
"  colnames(melttb) <- c(\"Samples\", \"Expression\")\n" +
"  #calculating log10(counts+1)\n" +
"  for(x in 1:length(melttb[,1])){\n" +
"    melttb[x,]$Expression <- log10(melttb[x,]$Expression+1)\n" +
"  }\n" +
"  #plotting + color setting\n" +
"  #best choice of color: \"Set3\", \"Spectral\" (both support 10 groups)\n" +
"  p <- ggplot(melttb, aes(x=Expression, y = ..count../sum(..count..), fill=Samples)) + \n" +
"    geom_density(alpha=.35) + \n" +
"    scale_fill_brewer(\"Samples\", palette = \"Spectral\")\n" +
"  #theme\n" +
"  p <- p + \n" +
"    scale_y_continuous( \"Density\", labels = percent_format(), expand = c(0,0))+\n" +
"    scale_x_continuous( \"log10(Normalized Counts +1)\",expand = c(0,0))+\n" +
"    theme(\n" +
"      panel.background = element_rect(fill = \"white\", color = \"black\"),\n" +
"      panel.grid.major = element_blank(),\n" +
"      panel.grid.minor = element_blank(),\n" +
"      axis.text.y = element_text(angle = 00, color = \"black\", size = 15),\n" +
"      axis.title = element_text(face = \"bold\", color = \"black\", size = 15),\n" +
"      legend.title = element_text(face = \"bold\", color = \"black\", size = 15),\n" +
"      legend.text = element_text(color = \"black\", size = 15),\n" +
"      #custom theme\n" +
"      axis.text.x = element_text(angle = 00, color = \"black\", size = 15)\n" +
"    )\n" +
"  print(p)\n" +
"}\r\n";
                str+="png(\""+plotpath+"\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
                str+="stackedDensityP(df)\r\n";
                str+="dev.off()\r\n;";
                
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
         new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","C:\\Users\\Administrator\\Desktop\\fornie");
         
        
    }
}
