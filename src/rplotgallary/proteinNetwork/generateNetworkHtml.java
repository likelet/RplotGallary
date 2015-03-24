/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rplotgallary.proteinNetwork;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import rplotgallary.pub.ImageFrame;
import rplotgallary.pub.Rexe;
//import test.Browser;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-26 11:06:00
 * @version 1.6.0
 */
public class generateNetworkHtml {
 public generateNetworkHtml(String datafile, String outpath,boolean isgui){
     try {
         RscriptGenerator rg=new RscriptGenerator(datafile,  outpath);
         String rscriptpath=rg.getRscriptpath();
         String htmlPath=rg.getHtmlPath();
         Rexe rexe=new Rexe(rscriptpath,true);
          if(isgui){
             
// ..
         

            }
            System.out.println("Your plot is located in \"" + htmlPath + "\"");
        } catch (IOException ex) {
            Logger.getLogger(generateNetworkHtml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
      new generateNetworkHtml("C:\\Users\\Administrator\\Desktop\\fornie\\Count Matrix1.csv","network",true);  
    }
}
