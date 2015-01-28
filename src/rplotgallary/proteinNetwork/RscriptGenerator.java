/*
 * RscriptGenerator of Boxplot
 */
package rplotgallary.proteinNetwork;

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
    private String htmlpath;
    private File datafile;
    private int genenumber=50;
    

    public RscriptGenerator(String datafile, String outpath) throws IOException {
        this.datafile = new File(datafile);
        this.htmlpath = outpath;
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
            htmlpath = htmlpath + ".html";
            String datapath = datafile.getAbsolutePath().replace("\\", "/");
            htmlpath = htmlpath.replace("\\", "/");
            //System.out.println(dirOut);
//                str="setwd(\""+Rscriptdir+"\")\r\n";

            str += "df<-read.csv(\"" + datapath + "\",header=T,row.names=1)\r\n";

            str += "#perform co-expression analysis with WGCNA\n"
                    + "library(\"ProCoNA\")\n"
                    + "#render network with js\n"
                    + "library(\"networkD3\")\n"
                    + "#set threads allowed by WGCNA\n"
                    + "allowWGCNAThreads(4)\n"
                    + "#read matrix data with n samples and m genes\n"
                    + "#df subtracted mean of each column and divided stand deviations\n"
                    + "dfnorm<-scale(df)\n"
                    + "#extract several("+this.genenumber+" for example) node\n"
                    + "dffilter<-dfnorm[1:50,]\n"
                    + "#transposed data frame to make sure each row is sample and column is protein or peptide\n"
                    + "dffilterT<-t(dffilter)\n"
                    + "\n"
                    + "#network analysis  by ProCoNA\n"
                    + "#esimate beta value\n"
                    + "beta <- pickSoftThreshold(dffilterT, networkType=\"signed\", RsquaredCut=0.8)\n"
                    + "if(is.na(beta$powerEstimate)){\n"
                    + "beta$powerEstimate=10\n"
                    + "}\n"
                    + "#build protein/peptide network\n"
                    + "peptideNetwork <- buildProconaNetwork(networkName=\"my network\", pepdat=dffilterT,networkType=\"signed\",pow=beta$powerEstimate,pearson=FALSE,toPermTestPermutes=1000)\n"
                    + "#generate network\n"
                    + " vis = exportNetworkToVisANT(peptideNetwork@TOM,weighted= TRUE,threshold =0)\n"
                    + " #get node\n"
                    + " node = unique(c(as.character(vis[,1]),as.character(vis[,2])))\n"
                    + " #index node in edge table(require!)\n"
                    + " vis[,1]=match(vis[,1],node)-1\n"
                    + " vis[,2]=match(vis[,2],node)-1\n"
                    + " #construct node frame\n"
                    + " node<-data.frame(name=node,group=sample(1:4,length(node),replace = TRUE))\n"
                    + " #construct edge dataframe\n"
                    + " edge<-vis[,c(1,2,5)]\n"
                    + " #generate network\n"
                    + " network<-forceNetwork(Links = edge, Nodes = node, Source = \"from\",\n"
                    + "             Target = \"to\", Value = \"weight\", NodeID = \"name\",\n"
                    + "             Group = \"group\", opacity = 1)\n"
                    + "#save network\n"
                    + "saveNetwork(network,\""+htmlpath+"\")\r\n";

            fw.append(str);

        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        fw.close();
    }

    public String getRscriptpath() {
        return Rscriptpath.getAbsolutePath();
    }

    public String getHtmlPath() {
        System.out.println(Rscriptpath.getAbsolutePath());
        return new File(htmlpath).getAbsolutePath();
    }

    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
        //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
        new RscriptGenerator("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv", "C:\\Users\\Administrator\\Desktop\\fornie");

    }
}
