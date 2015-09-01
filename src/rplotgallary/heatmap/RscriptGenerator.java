/*
 **  RscriptGenerator of RatioBarplot
 */
package rplotgallary.heatmap;

import rplotgallary.Barplot.*;
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
    private String iskey="TRUE";
    private boolean displaynumber=false;
    private int headnumber=30;
    private String[] colorlist={"red", "black", "green"};

    public RscriptGenerator(String datafile, int headnumber,String iskey, boolean displaynumber,String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
        this.iskey=iskey;
        this.displaynumber=displaynumber;
        this.headnumber=headnumber;
        this.process();
    }
    public RscriptGenerator(String datafile,String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.plotpath = outpath;
        this.process();
    }

    public void process() throws IOException {

        String str = "";
        Rscriptpath = File.createTempFile("tempR", ".R");
//        Rscriptpath.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpath);
        String colorstr=" colorRampPalette(c(\""+colorlist[0]+"\",\""+colorlist[1]+"\",\""+colorlist[2]+"\"))(100)";

        //System.out.println(intervalstr+"\r\n"+integerstri);
        try {

            String Rscriptdir = Rscriptpath.getParent();
            plotpath = plotpath + ".png";
            String datapath = datafile.getAbsolutePath().replace("\\", "/");
            plotpath = plotpath.replace("\\", "/");
            //System.out.println(dirOut);
//                str="setwd(\""+Rscriptdir+"\")\r\n";
            str += "library(pheatmap)\r\n";
            str += "library(gplots)\r\n";

            str += "df<-read.csv(\"" + datapath + "\",header=T,row.names=1)\r\n";
            str += "df<-head(df,n="+this.headnumber+")\r\n";
             //change figure residue
            if(Console.iscale.equalsIgnoreCase("small")){
                str += "png(\"" + plotpath + "\")\r\n";
            
            }else{
                 str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
            }
            
            if(this.displaynumber){
            str += "pheatmap(df, trace=\"none\",color="+colorstr+",border_color=\"white\",margin=c(13, 13),cluster_cols = FALSE,legend="+this.iskey+",display_numbers = TRUE,number_format =\"%.1e\")\r\n"; 
            }else{
                str += "pheatmap(df, trace=\"none\",color="+colorstr+",border_color=\"white\",margin=c(13, 13),cluster_cols = FALSE,legend="+this.iskey+")\r\n"; 
           
            }
            str += "dev.off()\r\n";

            fw.append(str);

        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        fw.close();
    }

    public String getRscriptpath() {
//        System.out.println(Rscriptpath.getAbsolutePath());
        return Rscriptpath.getAbsolutePath();
    }

    public String getPlotpath() {
        return new File(plotpath).getAbsolutePath();
    }

    public void setColorlist(String colorlist) {
        this.colorlist = colorlist.split(",");
    }

    public void setPlotpath(String plotpath) {
        this.plotpath = plotpath;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public void setDisplaynumber(boolean displaynumber) {
        this.displaynumber = displaynumber;
    }

    public void setHeadnumber(int headnumber) {
        this.headnumber = headnumber;
    }
    
   

    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
        //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
        new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv", "C:\\Users\\Administrator\\Desktop\\fornie");

    }
}
