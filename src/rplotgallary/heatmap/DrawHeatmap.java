/*
 * To change this license header, choose License Headers in Project Properties.
 
 */
package rplotgallary.heatmap;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import rplotgallary.pub.FunctionClass;
import rplotgallary.pub.ImageFrame;
import rplotgallary.pub.Rexe;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-26 11:06:00
 * @version 1.6.0
 */
public class DrawHeatmap {

    private String colorlist = "green,black,red";
    private String datafile;
    private String outpath="./heatmap";
    private boolean isgui=false;
    private int headnumber = 30;
    private String iskey = "T";
    private String displaynumber = "F";

    public DrawHeatmap(String datafile) {
        this.datafile = datafile;
    }

    
    
    
    public DrawHeatmap(String datafile, String outpath, boolean isgui) {
        try {
            RscriptGenerator rg = new RscriptGenerator(datafile, outpath);
            String rscriptpath = rg.getRscriptpath();
            String plotpath = rg.getPlotpath();
            Rexe rexe = new Rexe(rscriptpath, true);
            if (isgui) {
                Image img = ImageIO.read(new File(plotpath));
                new ImageFrame(img);
            }

//          FunctionClass.removeRoutFile(rscriptpath);
            System.out.println("Your plot is located in " + plotpath);
        } catch (IOException ex) {
            Logger.getLogger(DrawHeatmap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DrawHeatmap(String datafile, int headnumber, String iskey, String displaynumber, String outpath, boolean isgui) {
        try {
            String iskey2 = "TRUE";
            if (iskey.equals("F")) {
                iskey2 = "FALSE";
            }
            boolean displaynumber2 = false;
            if (displaynumber.equals("T")) {
                displaynumber2 = true;
            }
            RscriptGenerator rg = new RscriptGenerator(datafile, headnumber, iskey, displaynumber2, outpath);
            String rscriptpath = rg.getRscriptpath();
            String plotpath = rg.getPlotpath();
            Rexe rexe = new Rexe(rscriptpath, true);
            if (isgui) {
                Image img = ImageIO.read(new File(plotpath));
                new ImageFrame(img);
            }
            System.out.println("Your plot is located in " + plotpath);
        } catch (IOException ex) {
            Logger.getLogger(DrawHeatmap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void process() {
        try {
            String iskey2 = "TRUE";
            if (iskey.equals("F")) {
                iskey2 = "FALSE";
            }
            boolean displaynumber2 = false;
            if (displaynumber.equals("T")) {
                displaynumber2 = true;
            }
            RscriptGenerator rg = new RscriptGenerator(datafile,outpath);
            
            rg.setColorlist(colorlist);
            rg.setDisplaynumber(displaynumber2);
            rg.setIskey(iskey2);
            rg.setHeadnumber(headnumber);
            rg.process();
            String rscriptpath = rg.getRscriptpath();
            String plotpath = rg.getPlotpath();
            Rexe rexe = new Rexe(rscriptpath, true);
            if (isgui) {
                Image img = ImageIO.read(new File(plotpath));
                new ImageFrame(img);
            }
            System.out.println("Your plot is located in " + plotpath);
        } catch (IOException ex) {
            Logger.getLogger(DrawHeatmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setColorlist(String colorlist) {
        this.colorlist = colorlist;
    }

    public void setIsgui(boolean isgui) {
        this.isgui = isgui;
    }

    public void setHeadnumber(int headnumber) {
        this.headnumber = headnumber;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public void setDisplaynumber(String displaynumber) {
        this.displaynumber = displaynumber;
    }

    public void setOutpath(String outpath) {
        this.outpath = outpath;
    }

    
    
    
    public static void main(String[] args) {
//      new DrawHeatmap("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","C:\\Users\\Administrator\\Desktop\\fornie\\heatmap",true);
        DrawHeatmap dh=new DrawHeatmap("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv");
        dh.setColorlist("green,white,red");
        dh.setOutpath("C:\\Users\\Administrator\\Desktop\\fornie\\heatmap");
        dh.isgui=true;
        dh.setDisplaynumber("F");
        dh.setHeadnumber(20);
        dh.process();
    }
}
